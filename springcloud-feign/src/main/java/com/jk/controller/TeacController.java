package com.jk.controller;

import com.jk.model.Carousel;
import com.jk.model.Goods;
import com.jk.model.Student;
import com.jk.model.User;
import com.jk.service.TeacService;
import com.jk.util.DataGridResult;
import com.jk.util.OSSClientUtil;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("teac")
public class TeacController {

    @Autowired
    private TeacService teacService;

    //查询审核讲师
    @RequestMapping("queryTeac")
    @ResponseBody
    public DataGridResult queryTeac(@RequestBody ParameUtil parameUtil) {
        PageUtil pageUtil = teacService.queryTeac(parameUtil);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());

        return result;
    }


    //审核拒绝
    @RequestMapping("refuse")
    @ResponseBody
    public void refuse(Integer id){
        teacService.refuse(id);
    }
    //审核通过
    @RequestMapping("pass")
    @ResponseBody
    public void pass(Integer id){
        teacService.pass(id);
    }

    //查询讲师
    @RequestMapping("queryTeacShow")
    @ResponseBody
    public DataGridResult queryTeacShow(@RequestBody ParameUtil parameUtil) {
        PageUtil pageUtil = teacService.queryTeacShow(parameUtil);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());

        return result;
    }

    //查询学生
    @RequestMapping("queryStudent")
    @ResponseBody
    public DataGridResult queryStudent(@RequestBody ParameUtil parameUtil) {
        PageUtil pageUtil = teacService.queryStudent(parameUtil);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());

        return result;
    }


    //前登陆
    @RequestMapping("loginStu")
    @ResponseBody
    public String login(Student student, HttpServletRequest request) {
        //验证账号
        Student loginUser = teacService.queryStuName(student.getAccount());

        if (loginUser == null) {
            return "userError";
        }
        //验证密码
        if (!loginUser.getPassword().equals(student.getPassword())) {
            return "pwError";
        }
        //登录成功
        request.getSession().setAttribute("luser", loginUser);
        return "success";
    }


    //查询会员学生
    @RequestMapping("queryStudentShow")
    @ResponseBody
    public DataGridResult queryStudentShow(@RequestBody ParameUtil parameUtil) {
        PageUtil pageUtil = teacService.queryStudentShow(parameUtil);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());
        return result;
    }

    //前台注册查重
    @RequestMapping("checkAccount")
    @ResponseBody
    public String loginUser(Student student, String code, HttpServletRequest request, String account) {


        Student student1 = teacService.register(account);

        if (student1 != null) {
            return "userError";
        }

        return "success";
    }


    //注册
    @RequestMapping("addStudent")
    public String  addUser(Student student) {
        teacService.addStudent(student);
          return "html/yyw/loginStu";
    }

    //轮播图图片
    @RequestMapping("addloadImg")
    @ResponseBody
    public String uploadImg(MultipartFile imgg)throws IOException {
        if (imgg == null || imgg.getSize() <= 0) {
            throw new IOException("file不能为空");
        }
        OSSClientUtil ossClient=new OSSClientUtil();
        String name = ossClient.uploadImg2Oss(imgg);
        String imgUrl = ossClient.getImgUrl(name);
        String[] split = imgUrl.split("\\?");
        //System.out.println(split[0]);
        return split[0];
    }


    //查询轮播图
    @RequestMapping("queryLun")
    @ResponseBody
    public DataGridResult queryLun(@RequestBody ParameUtil parameUtil) {
        PageUtil pageUtil = teacService.queryLun(parameUtil);
        DataGridResult result = new DataGridResult();
        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());
        return result;
    }

    //查询轮播图
    @RequestMapping("queryCarousel")
    @ResponseBody
    public List<Carousel> queryCarousel(HttpServletRequest request) {
        List<Carousel> list = teacService.queryCarousel();
        request.getSession().setAttribute("list",list);
        // model.addAttribute("list",list);
        return list;
    }


    //新增轮播图
    @RequestMapping("addCarousel")
    @ResponseBody
    public void addCarousel(Carousel carousel){
        teacService.addCarousel(carousel);
    }

    //设为轮播
    @RequestMapping("putawayOne")
    @ResponseBody
    public void putawayOne(Integer id){
        teacService.putawayOne(id);
    }

    //取消轮播
    @RequestMapping("ishot")
    @ResponseBody
    public void ishot(Integer id){
        teacService.ishot(id);
    }

    //批量删除
    @RequestMapping("deleByLunId")
    @ResponseBody
    public void deleById(String ids){
        String[] split = ids.split(",");
        for (String id:split) {
            teacService.deleByLunId(id);
        }
    }


}
