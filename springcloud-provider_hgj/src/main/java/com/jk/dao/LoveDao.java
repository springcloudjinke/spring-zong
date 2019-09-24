package com.jk.dao;

import com.jk.model.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface LoveDao {

    @Select("select videoId from t_video tv,t_video_stu tvs where tv.vid=tvs.videoId and tvs.luserId = #{value}")
    List<Integer> findLoveByUserId(Integer id);

    @Insert("insert into t_video_stu(videoId,luserId) value(#{id},#{vid1})")
    void saveLoveByUserId(@Param("id") Integer id,@Param("vid1") Integer vid1);

    @Update("update t_video set status=status+1")
    void updateVideo();

    @Select("select * from t_video tv,t_teacher tt where tv.tid=tt.teacherId and videoUrl= #{value}")
    List<Video> queryUrlByVid(String videoUrl);
}
