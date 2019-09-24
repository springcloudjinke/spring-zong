package com.jk.service;

import com.jk.model.Carousel;
import com.jk.model.Student;
import com.jk.model.User;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface TeacServiceApi {
    @RequestMapping(value = "/queryTeac")
    PageUtil queryTeac(@RequestBody  ParameUtil parameUtil);

    @RequestMapping(value = "/refuse")
    void refuse(@RequestParam("id") Integer id);

    @RequestMapping(value = "/pass")
    void pass(@RequestParam("id") Integer id);

    @RequestMapping(value = "/queryTeacShow")
    PageUtil queryTeacShow(@RequestBody  ParameUtil parameUtil);

    @RequestMapping(value = "/queryStudent")
    PageUtil queryStudent(@RequestBody ParameUtil parameUtil);

    @RequestMapping(value = "/queryStuName")
    Student queryStuName(@RequestParam("account") String account);

    @RequestMapping(value = "/queryStudentShow")
    PageUtil queryStudentShow(@RequestBody ParameUtil parameUtil);

    @RequestMapping(value = "/register")
    Student register(@RequestParam("account") String account);

    @RequestMapping(value = "/addStudent")
    void addStudent(@RequestBody Student student);

    @RequestMapping(value = "/queryLun")
    PageUtil queryLun(@RequestBody ParameUtil parameUtil);

    @RequestMapping(value = "/addCarousel")
    void addCarousel(@RequestBody Carousel carousel);

    @RequestMapping(value = "/queryCarousel")
    List<Carousel> queryCarousel();

    @RequestMapping(value = "/putawayOne")
    void putawayOne(@RequestParam("id") Integer id);

    @RequestMapping(value = "/ishot")
    void ishot(@RequestParam("id") Integer id);

    @RequestMapping(value = "/deleByLunId")
    void deleByLunId(@RequestParam("id") String id);


}
