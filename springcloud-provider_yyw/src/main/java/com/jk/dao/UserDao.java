package com.jk.dao;

import com.jk.model.Role;
import com.jk.model.RoleTree;
import com.jk.model.Tree;
import com.jk.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserDao {
    User queryUserName(String userName);

    List<Tree> getTreeAll(Integer id);

    List<User> queryZhanghao(Map map);

    @Select("select * from t_role")
    List<Role> queryRole();

    List<Tree> queryOrgAll2(@Param("id") Integer id, @Param("pid") int pid);

    List<Tree> queryOrgAll3(@Param("pid") int pid);

    @Select("select tree_id from role_tree where roleid = #{id}")
    List<String> queryTreeById(Integer id);

    @Select("select * from tree")
    List<Tree> queryTreeAll();

    @Delete("delete from role_tree where roleid = #{roleid}")
    int deletePerById(Integer roleid);

    int insert(RoleTree rpm);

    @Select("select roleid from user_role where userid = #{id}")
    List<String> queryrolebyid(Integer id);

    @Select("select * from t_role")
    List<Role> queryRoleAll();

    @Update("update t_role set count = count-1 where id = #{i}")
    void updateRoleCount1(int i);

    @Update("update t_role set count = count+1 where id = #{ids}")
    void updateRoleCount2(Integer ids);

    @Update("update user_role set roleid=#{ids} where userid=#{id}")
    void updateRole2(@Param("ids") Integer ids, @Param("id") Integer id);
}
