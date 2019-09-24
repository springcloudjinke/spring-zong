package com.jk.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jk.model.Tree;


/**
 * <pre>项目名称：book_wh
 * 类名称：TreeNoteUtil
 * 类描述：
 * 创建人：岳跃威
 *
 * 励志语录:逆水行舟不进则退
 *
 * 创建时间：2019年7月2日 下午5:03:01
 * 修改人：岳跃威  1476181116@qq.com
 * 修改时间：2019年7月2日 下午5:03:01
 * 修改备注：
 * @version </pre>
 */
public class TreeNoteUtil implements Serializable {


    /**
     * 获取父节点菜单
     *
     * @param treesList 所有树菜单集合
     * @return
     */
    public final static List<Tree> getFatherNode(List<Tree> treesList) {
        List<Tree> newTrees = new ArrayList<Tree>();
        for (Tree mt : treesList) {
            if (mt.getPid() == null || "".equals(mt.getPid()) || mt.getPid() == 0) {//如果pId为空，则该节点为父节点
                //递归获取父节点下的子节点
                mt.setNodes(getChildrenNode(mt.getId().toString(), treesList));
                newTrees.add(mt);
            }
        }
        return newTrees;
    }

    /**
     * 递归获取子节点下的子节点
     *
     * @param pId       父节点的ID
     * @param treesList 所有菜单树集合
     * @return
     */
    private final static List<Tree> getChildrenNode(String pId, List<Tree> treesList) {
        List<Tree> newTrees = new ArrayList<Tree>();
        for (Tree mt : treesList) {
            if (mt.getPid() == null || mt.getPid() == 0) continue;
            if (mt.getPid() == Integer.valueOf(pId)) {
                //递归获取子节点下的子节点，即设置树控件中的children
                mt.setNodes(getChildrenNode(mt.getId().toString(), treesList));
                //设置树控件attributes属性的数据
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("url", mt.getUrl());
                mt.setAttributes(map);
                newTrees.add(mt);
            }
        }
        return newTrees;
    }
}
