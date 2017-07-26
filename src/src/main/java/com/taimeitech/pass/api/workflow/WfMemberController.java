package com.taimeitech.pass.api.workflow;


import com.taimeitech.pass.entity.member.*;
import com.taimeitech.pass.entity.member.GetGroups;
import com.taimeitech.pass.entity.member.GetGroupsResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WfMemberController {
    @ApiOperation(value = "批量删除group")
    @RequestMapping(value = "member/DeleteGroups", method = RequestMethod.POST)
    public DeleteGroupsResponse Post(@ApiParam("data") @RequestBody DeleteGroups data) {
        return new DeleteGroupsResponse();
    }

    @ApiOperation(value = "保存group列表")
    @RequestMapping(value = "member/SaveGroups", method = RequestMethod.POST)
    public SaveGroupsResponse Post(@ApiParam("data") @RequestBody SaveGroups data) {
        return new SaveGroupsResponse();
    }

    @ApiOperation(value = "保存user列表")
    @RequestMapping(value = "member/SaveUsers", method = RequestMethod.POST)
    public SaveUsersResponse Post(@ApiParam("data") @RequestBody SaveUsers data) {
        return new SaveUsersResponse();
    }

    @ApiOperation(value = "批量删除user")
    @RequestMapping(value = "member/DeleteUsers", method = RequestMethod.POST)
    public DeleteUsersResponse Post(@ApiParam("data") @RequestBody DeleteUsers data) {
        return new DeleteUsersResponse();
    }

    @ApiOperation(value = "保存user group关联关系")
    @RequestMapping(value = "member/SaveUserGroupRelationsByUser", method = RequestMethod.POST)
    public SaveUserGroupRelationsByUserResponse  Post(@ApiParam("data") @RequestBody SaveUserGroupRelationsByUser data) {
        SaveUserGroupRelationsByUserResponse response= new SaveUserGroupRelationsByUserResponse();
        response.setSuccess(true);
        return   response;
    }

    @ApiOperation(value = "保存user group关联关系")
    @RequestMapping(value = "member/SaveUserGroupRelationsByGroup", method = RequestMethod.POST)
    public SaveUserGroupRelationsByGroupResponse  Post(@ApiParam("data") @RequestBody SaveUserGroupRelationsByGroup data) {
        SaveUserGroupRelationsByGroupResponse response= new SaveUserGroupRelationsByGroupResponse();
        response.setSuccess(true);
        return   response;
    }

    @ApiOperation(value = "查询group")
    @RequestMapping(value = "member/GetGroups", method = RequestMethod.POST)
    public GetGroupsResponse Post(@ApiParam("data") @RequestBody GetGroups data) {
        WfGroup group = new WfGroup();
        group.setAppId("fims");
        group.setName("123Name");
        group.setId("123");
        WfGroup group1 = new WfGroup();
        group1.setAppId("fims");
        group1.setName("345Name");
        group1.setId("345");
        List<WfGroup> responseData = new ArrayList<WfGroup>();
        responseData.add(group);
        responseData.add(group1);
        GetGroupsResponse response=new GetGroupsResponse();
        response.setData(responseData);
        return response;
    }

}