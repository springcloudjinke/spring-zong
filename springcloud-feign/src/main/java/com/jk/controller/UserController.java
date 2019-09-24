package com.jk.controller;

import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.util.DataGridResult;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import com.jk.util.TreeNoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    //登陆
    @RequestMapping("loginUser")
    @ResponseBody
    public String login(User user, HttpServletRequest request) {
        //验证账号
        User loginUser = userService.queryUserName(user.getUserName());

        if (loginUser == null) {
            return "userError";
        }
        //验证密码
        if (!loginUser.getPassword().equals(user.getPassword())) {
            return "pwError";
        }
        //登录成功
        request.getSession().setAttribute("user", loginUser);
        return "success";
    }

//查询树
    @RequestMapping("getAllTree")
    @ResponseBody
    public List<Tree> getTreeAll(HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");

        List<Tree> list = userService.getTreeAll(user.getId());
        //自己调自己
        list = TreeNoteUtil.getFatherNode(list);

        return list;
    }

    //查询账号
    @RequestMapping("queryZhanghao")
    @ResponseBody
    public DataGridResult queryZhanghao(@RequestBody ParameUtil parameUtil) {
        PageUtil pageUtil = userService.queryZhanghao(parameUtil);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());

        return result;
    }

    //查询角色
    @RequestMapping("queryRole")
    @ResponseBody
    public DataGridResult queryRole(@RequestBody ParameUtil parameUtil) {
        PageUtil pageUtil = userService.queryRole(parameUtil);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());

        return result;
    }

    //权限
    @RequestMapping("queryTree")
    @ResponseBody
    public List<Tree> queryTree(Integer id) {

        List<Tree> list = userService.queryTreeById(id);
        list = TreeNoteUtil.getFatherNode(list);
        return list;
    }


    //根据 角色id跳转到回显页面
    @RequestMapping("yywTree")
    public String yywTree(Integer id, Model model) {
        model.addAttribute("id", id);
        return "html/yyw/yywtree";
    }

    //根据 角色id查询对应权限
    @RequestMapping("cxByrIdTree")
    @ResponseBody
    public List<Tree> queryByrIdTree(Integer id) {
        List<Tree> querytreebyrid = userService.querytreebyrid(id, 0);
        return querytreebyrid;
    }

     //绑定权限
    @RequestMapping("updatetree")
    @ResponseBody
    public void updatetree(Integer[] ids,Integer roleid){
        userService.UpdateTree(ids,roleid);
    }



    //角色权限
    @RequestMapping("queryRoleById")
    public String  queryRoleById(Integer id, Model model,HttpServletRequest request){
        List<Role> list = userService.editrole(id);
        List<String> list1 = userService.queryRoleById(id);
        request.getSession().setAttribute("id",list1.get(0));
        model.addAttribute("id",id);
        model.addAttribute("list",list);
        return "html/yyw/dhyrole";
    }


    //角色权限
    @RequestMapping("updateRole2")
    @ResponseBody
    public void updateRole(Integer ids,Integer id,HttpServletRequest request) {
        String roleid = (String)request.getSession().getAttribute("id");
        int i = Integer.parseInt(roleid);
        userService.updateRoleCount(i,ids);
        userService.updateRole2(ids,id);
    }




}
