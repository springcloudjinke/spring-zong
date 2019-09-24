package com.jk.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jk.dao.UserDao;
import com.jk.model.Role;
import com.jk.model.RoleTree;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.util.PageUtil;
import com.jk.util.ParameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserServiceImpl implements UserServiceApi {

    @Autowired
    private UserDao userDao;


    //验证用户
    @Override
    public User queryUserName(String userName) {
        return userDao.queryUserName(userName);
    }

    //查询树
    @Override
    public List<Tree> getTreeAll(Integer id) {
        return userDao.getTreeAll(id);
    }

    //查询账号
    @Override
    public PageUtil queryZhanghao(ParameUtil parameUtil) {

        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());
        Map map = new HashMap<>();
        map.put("userName",parameUtil.getUserName());
        List<User> list = userDao.queryZhanghao(map);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());
        page.setList(list);
        return page;

    }

    //查询管理员
    @Override
    public PageUtil queryRole(ParameUtil parameUtil) {
        PageHelper.startPage(parameUtil.getPageNumber(),parameUtil.getPageSize());
        List<Role> list = userDao.queryRole();
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        PageUtil page= new PageUtil((int)pageInfo.getTotal(),parameUtil.getPageNumber(), parameUtil.getPageSize());
        page.setList(list);
        return page;

    }

    //回显查询树 选中
    @Override
    public List<Tree> querytreebyrid(Integer id, int pid) {
        Map<Object, Object> json = new HashMap<>();
        List <Tree> list =queryOrgAll3(pid);
        List <Tree> list2= queryOrgAll2(id,pid);
        Map map=new HashMap();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if(list.get(i).getId() == list2.get(j).getId()){
                    //list.get(i).getId();
                    json.put("checked", true);
                    list.get(i).setState(json);
                }
            }
            if(list.size()>0){
                for (int s = 0; s < list.size(); s++) {
                    List<Tree> list3 = querytreebyrid(id,list.get(s).getId());
                    list.get(s).setNodes(list3);
                }
            }
        }
        return list;
    }

    //查询树
    @Override
    public List<Tree> queryTreeById(Integer id) {
        //查出  角色 id  所对应的 菜单 的id
        List<String> list =  userDao.queryTreeById(id);

        // 查询所有 的菜单
        List<Tree>  listTwo = userDao.queryTreeAll();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listTwo.size(); j++) {
                // 用  原来 选中的 菜单id  和所有的  菜单id  对比 ，对比成功 就选中
                if(list.get(i).equals(listTwo.get(j).getId().toString())){
                    listTwo.get(j).setChecked(true);
                }
            }
        }
        return listTwo;
    }

    //修改树 先删除 在新增
    @Override
    public void UpdateTree(Integer[] ids, Integer roleid) {
        //先 删除 原有的 关联
        int i  = userDao.deletePerById(roleid);
        //再建立  新的 关联
        if(i >= 0){
            for (int j = 0; j < ids.length; j++) {
                RoleTree rpm = new RoleTree();
                rpm.setTreeid(ids[j]);
                rpm.setRoleid(roleid);
                i = userDao.insert(rpm);
            }
        }
    }

    //查询角色菜单
    @Override
    public List<Role> editrole(Integer id) {
        //查出  角色 id  所对应的 菜单 的id
        List<String> list =  userDao.queryrolebyid(id);
        // 查询所有 的菜单
        List<Role>  listTwo = userDao.queryRoleAll();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < listTwo.size(); j++) {
                // 用  原来 选中的 菜单id  和所有的  菜单id  对比 ，对比成功 就选中
                if(list.get(i).equals(listTwo.get(j).getId().toString())){
                    listTwo.get(j).setChecked("true");
                }
            }
        }
        return listTwo;
    }

    //查询角色对应的菜单
    @Override
    public List<String> queryRoleById(Integer id) {
        List<String> list =  userDao.queryrolebyid(id);
        return list;
    }

    //修改角色
    @Override
    public void updateRoleCount(int i, Integer ids) {
        System.out.println(i);
        userDao.updateRoleCount1(i);
        userDao.updateRoleCount2(ids);
    }

    //修改角色
    @Override
    public void updateRole2(Integer ids, Integer id) {
        userDao.updateRole2(ids,id);
    }

    //调上面的 querytreebyrid 方法 根据pid查询子节点
    private List<Tree> queryOrgAll2(Integer id, int pid) {
        // 根据pid查询子节点
        List<Tree> orgs = userDao.queryOrgAll2(id,pid);
        // 如果查询到子节点集合
        if(orgs != null && orgs.size()>0){
            // 循环集合，将每个机构对象的id作为pid 继续查询子节点集合
            for (int i = 0; i < orgs.size(); i++) {
                List<Tree> orgs2 = queryOrgAll2(id,orgs.get(i).getId());
                // 将查询的子节点集合放到该结构对象的children属性中
                orgs.get(i).setNodes(orgs2);
            }
        }
        return orgs;
    }

    //调上面的 querytreebyrid 方法 根据pid查询子节点
    private List<Tree> queryOrgAll3(int pid) {
        // 根据pid查询子节点
        List<Tree> orgs = userDao.queryOrgAll3(pid);
        // 如果查询到子节点集合
        if(orgs != null && orgs.size()>0){
            // 循环集合，将每个机构对象的id作为pid 继续查询子节点集合
            for (int i = 0; i < orgs.size(); i++) {
                List<Tree> orgs2 = queryOrgAll3(orgs.get(i).getId());
                // 将查询的子节点集合放到该结构对象的children属性中
                orgs.get(i).setNodes(orgs2);
            }
        }
        return orgs;

    }



}
