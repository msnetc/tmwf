package com.taimeitech.pass.service.workflow;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;

import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorkFlowExt {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private HistoryService historyService;

    /**
     * 查询流程当前节点的下一步节点。用于流程提示时的提示。
     * @param process
     * @param sourceFlowElement
     * @param nodeMap
     * @param listVar
     * @throws Exception
     */
    private void iteratorNextNodes(org.activiti.bpmn.model.Process process, FlowNode sourceFlowElement, Map<String,  FlowNode> nodeMap,List<HistoricVariableInstance> listVar)
            throws Exception {
        List<SequenceFlow> list = sourceFlowElement.getOutgoingFlows();
        for (SequenceFlow sf : list) {
            sourceFlowElement = ( FlowNode) process.getFlowElement( sf.getTargetRef());
            if(StringUtils.isNotEmpty(sf.getConditionExpression() )){
                ExpressionFactory factory = new ExpressionFactoryImpl();
                SimpleContext context = new SimpleContext();
                for(HistoricVariableInstance var :listVar){
                    context.setVariable(var.getVariableName(), factory.createValueExpression(var.getValue(), var.getValue().getClass()));
                }
                ValueExpression e = factory.createValueExpression(context, sf.getConditionExpression(), boolean.class);
                if((Boolean)e.getValue(context)){
                    nodeMap.put(sourceFlowElement.getId(), sourceFlowElement);
                    break;
                }
            }
            if (sourceFlowElement instanceof org.activiti.bpmn.model.UserTask) {
                nodeMap.put(sourceFlowElement.getId(), sourceFlowElement);
                break;
            }else if (sourceFlowElement instanceof org.activiti.bpmn.model.ExclusiveGateway) {
                iteratorNextNodes(process, sourceFlowElement, nodeMap,listVar);
            }
        }
    }

}
