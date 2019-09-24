package com.jk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.dao.TeacDao;
import com.jk.model.Carousel;
import com.jk.model.Student;
import com.jk.model.Teacher;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TeacServiceImpl implements TeacServiceApi {

    @Autowired
    private TeacDao teacDao;

    //查询审核讲师
    @Override
    public PageUtil queryTeac(ParameUtil parameUtil) {
        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());
        Map map = new HashMap<>();
        map.put("teacherName",parameUtil.getTeacherName());
        List<Teacher> list = teacDao.queryTeac(map);
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());
        page.setList(list);
        return page;

    }

    //拒绝审核
    @Override
    public void refuse(Integer id) {
        teacDao.refuse(id);
    }

    //审核通过
    @Override
    public void pass(Integer id) {
        teacDao.pass(id);
    }

    //查询讲师
    @Override
    public PageUtil queryTeacShow(ParameUtil parameUtil) {
        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());
        Map map = new HashMap<>();
        map.put("teacherName",parameUtil.getTeacherName());
        List<Teacher> list = teacDao.queryTeacShow(map);
        PageInfo<Teacher> pageInfo = new PageInfo<>(list);
        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());
        page.setList(list);
        return page;
    }
    //查询学生
    @Override
    public PageUtil queryStudent(ParameUtil parameUtil) {
        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());
        Map map = new HashMap<>();
        map.put("account",parameUtil.getAccount());
        List<Student> list = teacDao.queryStudent(map);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());
        page.setList(list);
        return page;
    }
    //前登陆
    @Override
    public Student queryStuName(String account) {
        return teacDao.queryStuName(account);
    }

    //查询学生会员信息
    @Override
    public PageUtil queryStudentShow(ParameUtil parameUtil) {

        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());
        Map map = new HashMap<>();
        map.put("account",parameUtil.getAccount());
        List<Student> list = teacDao.queryStudentShow(map);
        PageInfo<Student> pageInfo = new PageInfo<>(list);
        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());
        page.setList(list);
        return page;
    }

    //账号验重
    @Override
    public Student register(String account) {
        return teacDao.register(account);
    }

    //注册学生
    @Override
    public void addStudent(Student student) {
     teacDao.addStudent(student);

    }

    //查询轮播图
    @Override
    public PageUtil queryLun(ParameUtil parameUtil) {
        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());
        List<Carousel> list = teacDao.queryLun();
        PageInfo<Carousel> pageInfo = new PageInfo<>(list);
        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());
        page.setList(list);
        return page;
    }

    //新增轮播图
    @Override
    public void addCarousel(Carousel carousel) {
        teacDao.addCarousel(carousel);
    }

    //前台展示轮播图
    @Override
    public List<Carousel> queryCarousel() {
        return teacDao.queryCarousel();
    }

    //设为轮播
    @Override
    public void putawayOne(Integer id) {
        teacDao.putawayOne(id);
    }

    //取消轮播
    @Override
    public void ishot(Integer id) {
        teacDao.ishot(id);
    }

    //批量删除
    @Override
    public void deleByLunId(String id) {
        teacDao.deleByLunId(id);
    }


}
