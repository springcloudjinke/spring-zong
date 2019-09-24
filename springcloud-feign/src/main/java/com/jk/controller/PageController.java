/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: PageController
 * Author:   李辉
 * Date:     2019/9/17 19:34
 * Description: a
 * History:
 * <author>          <time>          <version>          <desc>
 * 李辉           修改时间           版本号              描述
 */
package com.jk.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈a〉
 *
 * @author 李辉
 * @create 2019/9/17
 * @since 1.0.0
 */
@Controller
@RequestMapping("page")
public class PageController {

    //主页面
    @RequestMapping("main")
    public String main(){
        return  "main";
    }


    //主页面
    @RequestMapping("index")
    public String index(){
        return  "index";
    }
//ruzhu

    @RequestMapping("ruzhu")
    public String ruzhu(){
        return  "ruzhu";
    }

    //视频
    @RequestMapping("ship")
    public ModelAndView ship(Integer teacherId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("ship");
        mv.addObject("teacherId",teacherId);
        return  mv;
    }

    //登陆
    @RequestMapping("loginUser")
    public String loginUser() {
        return "html/yyw/loginUser";
    }

    //树
    @RequestMapping("index1")
    public String index1() {
        return "html/yyw/index";
    }

    //dhy  页面video
    @RequestMapping("toVideo")
    public String toVideo(){
        return "html/dhy/showTeacher";
    }

    //前登陆
    @RequestMapping("loginStu")
    public String loginStu() {
        return "html/yyw/loginStu";
    }

    //账号管理
    @RequestMapping("tozhangHao")
    public String toZhanghao() {
        return "html/yyw/zhangHao";
    }

    //账号管理
    @RequestMapping("togangWei")
    public String togangWei() {
        return "html/yyw/gangWei";
    }

    //查询审核讲师信息
    @RequestMapping("toTeacList")
    public String toTeacList() {
        return "html/yyw/teacList";
    }

    //查询讲师信息
    @RequestMapping("toTeacShow")
    public String toTeacShow() {
        return "html/yyw/teacShow";
    }

    //查询学生信息
    @RequestMapping("toStuList")
    public String toStuList() {
        return "html/yyw/stuList";
    }

    //注销
    @RequestMapping("zhuxiao")
    public String zhuxiao (HttpServletRequest request){
        request.getSession().removeAttribute("luser");//清空session信息
        return "main";
    }

    //查询学生会员信息
    @RequestMapping("toStuShow")
    public String toStuShow() {
        return "html/yyw/stuShow";
    }

    //前台注册
    @RequestMapping("zhuCe")
    public String zhuCe() {
        return "html/yyw/zhuCe";
    }

    //个人中心
    //个人中心页面
    @RequestMapping("persion")
    public String persion() {
        return "persion";
    }

    //充值中心

    @RequestMapping("recharge")
    public String recharge() {
        return "recharge";
    }


    //查询轮播图
    @RequestMapping("querycarousel")
    public String querycarousel() {
        return "html/yyw/lunShow";
    }


    //查询轮播图
    @RequestMapping("addCarousel")
    public String addCarousel() {
        return "html/yyw/carousel";
    }
    //客服
    @RequestMapping("kefu")
    public String kefu() {
        return "kefu";

    }

    //个人中心--我的课程
    @RequestMapping("mycourse")
    public String mycourse(){
        return "mycourse";

    }
}