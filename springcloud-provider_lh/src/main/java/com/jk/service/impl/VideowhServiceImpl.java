package com.jk.service.impl;


import com.jk.mapper.VideowhDao;
import com.jk.model.*;
import com.jk.service.VideowhServiceApi;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * ClassName: VideowhServiceImpl
 * describe:
 *
 * @author wanghang
 * @date 2019/09/18 9:30
 */
@RestController
public class VideowhServiceImpl implements VideowhServiceApi {

    @Autowired
    private VideowhDao videowhDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    //首页
    @Override
    @RequestMapping(value = "/queryvideowh")
    public List<Teacher> queryvideowh() {
        return videowhDao.queryvideowh();
    }

    //ship   页面
    @Override
    @RequestMapping(value = "/queryTeacher")
    public List<Teacher> queryTeacher(@RequestParam("teacherId") Integer teacherId) {
        return videowhDao.queryTeacher(teacherId);
    }

    //查询 每个老师的视频
    @Override
    @RequestMapping(value = "/queryvideowhById")
    public List<Teacher> queryvideowhById(@RequestParam("teacherId") Integer teacherId) {
        return videowhDao.queryvideowhById(teacherId);
    }
//新增老师
    @Override
    @RequestMapping("addTeacher")
    public void addTeacher(@RequestBody Teacher teacher) {

        videowhDao.addTeacher(teacher);

    }



    //新增会员
    @Override
    @RequestMapping("addHuiYuan")
    public void addHuiYuan(@RequestParam("id") Integer id, @RequestParam("day")  Integer day,@RequestParam("member")Integer member) {

if(member==2){
    videowhDao.addHuiYuan(id,day);
}else{
    videowhDao.addHuiYuan2(id,day);
}

    }



    @Override
    //会员到期
    @RequestMapping("queryHuiYuan")
    public void updateHuiYuan(@RequestParam("id")Integer id) {
        videowhDao.updateHuiYuan(id);
    }

    //个人中心 -- 个人资料修改
    @Override
    @RequestMapping("updData")
    public void updData(@RequestBody Student student) {

        videowhDao.updData(student);

    }

    //个人中心--查询我的课程 mongodb
    @Override
    @RequestMapping("queryMyCourse")
    public PageUtil queryMyCourse(@RequestBody ParameUtil param) {
        Criteria c = new Criteria();
        Query query = new Query();
        query.addCriteria(c);
        long count = mongoTemplate.count(query, Uvideo.class, "uvideo");
        PageUtil pageUtil = new PageUtil((int) count, param.getPageNumber(), param.getPageSize());
        Integer skip = pageUtil.getFirstResultCount();
        query.skip(skip);
        query.limit(param.getPageSize());
        List<Uvideo> list = mongoTemplate.find(query, Uvideo.class, "uvideo");
        pageUtil.setList(list);
        return pageUtil;
    }

  @Override
  @RequestMapping("queryMember")
    public Integer queryMember(@RequestParam("id")  Integer id) {
        return videowhDao.queryMember(id);
    }
}
