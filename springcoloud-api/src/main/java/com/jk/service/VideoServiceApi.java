package com.jk.service;

import com.jk.model.Video;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VideoServiceApi {

    //查询
    @RequestMapping(value = "/queryTeacher",method = RequestMethod.POST)
    PageUtil queryTeacher(@RequestBody ParameUtil parameUtil);

    //新增goods表
    @RequestMapping(value = "/addVideo",method = RequestMethod.POST)
    void addVideo(@RequestBody final Video video);

    @RequestMapping(value = "/queryVideo",method = RequestMethod.POST)
    List<Video> queryVideo(@RequestParam("id") Integer id);
}
