package com.jk.dao;

import com.jk.model.Carousel;
import com.jk.model.Student;
import com.jk.model.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface TeacDao {
    List<Teacher> queryTeac(Map map);

    @Update("update t_teacher t set t.status=2 where teacherId =#{id}")
    void refuse(Integer id);

    @Update("update t_teacher t set t.status=1 where teacherId = #{id}")
    void pass(Integer id);

    List<Teacher> queryTeacShow(Map map);

    List<Student> queryStudent(Map map);

    @Select("select * from t_student where account =#{account}")
    Student queryStuName(String account);

    List<Student> queryStudentShow(Map map);

    @Select("select * from t_student where account = #{account}")
    Student register(String account);

    @Insert("insert into t_student(account,password,nickname) values(#{account},#{password},#{nickname})")
    void addStudent(Student student);

    @Select("select * from t_carousel")
    List<Carousel> queryLun();

    @Insert("insert into t_carousel(tname,state) values(#{tname},0)")
    void addCarousel(Carousel carousel);

    @Select("select * from t_carousel where state = 1")
    List<Carousel> queryCarousel();

    @Update("update t_carousel t set t.state=1 where id = #{id}")
    void putawayOne(Integer id);

    @Update("update t_carousel t set t.state=0 where id = #{id}")
    void ishot(Integer id);

    @Delete("delete from t_carousel where id = #{id}")
    void deleByLunId(String id);

}
