package com.jk.service;

import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserServiceApi {

    @RequestMapping(value = "/queryUserName", method = RequestMethod.POST)
    User queryUserName(@RequestParam("userName") String userName);

    @RequestMapping(value = "/getTreeAll")
    List<Tree> getTreeAll(@RequestParam("id") Integer id);

    @RequestMapping(value = "/queryZhanghao")
    PageUtil queryZhanghao(@RequestBody ParameUtil parameUtil);

    @RequestMapping(value = "/queryRole")
    PageUtil queryRole(@RequestBody ParameUtil parameUtil);

    @RequestMapping(value = "/querytreebyrid")
    List<Tree> querytreebyrid(@RequestParam("id") Integer id, @RequestParam("i") int i);

    @RequestMapping(value = "/queryTreeById")
    List<Tree> queryTreeById(@RequestParam("id") Integer id);

    @RequestMapping(value = "/UpdateTree")
    void UpdateTree(@RequestParam("ids") Integer[] ids,@RequestParam("roleid") Integer roleid);

    @RequestMapping(value = "/editrole")
    List<Role> editrole(@RequestParam("id") Integer id);

    @RequestMapping(value = "/queryRoleById")
    List<String> queryRoleById(@RequestParam("id") Integer id);

    @RequestMapping(value = "/updateRoleCount")
    void updateRoleCount(@RequestParam("i") int i,@RequestParam("ids") Integer ids);

    @RequestMapping(value = "/updateRole2")
    void updateRole2(@RequestParam("updateRole2") Integer ids, @RequestParam("id") Integer id);
}
