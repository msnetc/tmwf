package com.taimeitech.pass.service.member;
import   com.taimeitech.pass.entity.member.UserRolesResult;

public interface UserService {
      UserRolesResult GetUsers(String talentId, String softWareId, String roleId);
}
