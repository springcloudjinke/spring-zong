package com.jk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.mapper.VideoMapper;
import com.jk.model.Teacher;
import com.jk.model.Video;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VideoServiceImpl implements VideoServiceApi {

    @Autowired
    private VideoMapper videoMapper;


    //查询 goods表
    @Override
    public PageUtil queryTeacher(ParameUtil parameUtil) {

        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());

        Map map = new HashMap<>();

       /* map.put("name",parameUtil.getName());*/
        /*            System.err.println(parameUtil.getName());*/
        List<Teacher> list = videoMapper.queryTeacher(map);

        PageInfo<Teacher> pageInfo = new PageInfo<Teacher>(list);

        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());

        page.setList(list);

        return page;
    }

    //新增goods
    @Override
    public void addVideo(Video video) {
       // System.out.println(goods.getTeacherName());
        videoMapper.addVideo(video);
    }

    //查 video
    @Override
    public List<Video> queryVideo(Integer id) {
        return videoMapper.queryVideo(id);
    }


}
