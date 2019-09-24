package com.jk.mapper;

import com.jk.model.Goods;
import com.jk.model.Teacher;
import com.jk.model.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface VideoMapper {

    //查
    @Select("SELECT * FROM t_teacher")
    List<Teacher> queryTeacher(Map map);

/*    //增
    @Insert("insert into t_goods(teacherName,picture,school,videoGenus) values(#{teacherName},#{picture},#{school},#{videoGenus})")
    void addVideo(Goods goods);*/

    //增video
    @Insert("insert into t_video(videoName,videoUrl,courseIntroduction,introduction,tid) values(#{videoName},#{videoUrl},#{courseIntroduction},#{introduction},#{tid})")
    void addVideo(Video video);

    @Select("select * from t_video where tid = #{id}")
    List<Video> queryVideo(Integer id);
}
