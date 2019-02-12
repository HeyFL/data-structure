package org.chris.demo.tree;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author caizq
 * @date 2018/11/29
 * @since v1.0.0
 */
@Slf4j
@Data
public class TreeNode<T> implements Serializable {
    private static final long serialVersionUID = -7228370775359768546L;
    private String nodeName;
    private T obj;
    private TreeNode parentNode;
    private List<TreeNode<T>> childList;
    private int parentId;
    private int selfId;

    public TreeNode() {
        initChildList();
    }

    public TreeNode(TreeNode parentNode) {
        this.getParentNode();
        initChildList();
    }

    public boolean isLeaf() {
        if (childList == null) {
            return true;
        } else {
            if (childList.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 插入一个child节点到当前节点中
     *
     * @param treeNode
     */
    public void addChildNode(TreeNode treeNode) {
        initChildList();
        childList.add(treeNode);
    }

    public void initChildList() {
        if (childList == null)
            childList = new ArrayList<>();
    }

    public boolean isValidTree() {
        return true;
    }

    /**
     * 返回当前节点的父辈节点集合
     *
     * @return
     */
    public List<TreeNode> getElders() {
        List<TreeNode> elderList = new ArrayList<>();
        TreeNode parentNode = this.getParentNode();
        if (parentNode == null) {
            return elderList;
        } else {
            elderList.add(parentNode);
            elderList.addAll(parentNode.getElders());
            return elderList;
        }
    }


    /**
     * 返回当前节点的孩子集合
     *
     * @return
     */
    public List<TreeNode<T>> getChildList() {
        return childList;
    }

    public void setChildList(List<TreeNode<T>> childList) {
        this.childList = childList;
    }

    /**
     * 删除节点和它下面的晚辈
     */
    public void deleteNode() {
        TreeNode parentNode = this.getParentNode();
        if (parentNode != null) {
            parentNode.deleteChildNode(this.getSelfId());
        }
    }

    /**
     * 删除当前节点的某个子节点
     *
     * @param childId
     */
    public void deleteChildNode(int childId) {
        List<TreeNode<T>> childList = this.getChildList();
        int childNumber = childList.size();
        for (int i = 0; i < childNumber; i++) {
            TreeNode child = childList.get(i);
            if (child.getSelfId() == childId) {
                childList.remove(i);
                return;
            }
        }
    }

    /**
     * 动态的插入一个新的节点到当前树中
     *
     * @param treeNode
     * @return
     */
    public boolean insertJuniorNode(TreeNode treeNode) {
        int juniorParentId = treeNode.getParentId();
        if (this.parentId == juniorParentId) {
            addChildNode(treeNode);
            return true;
        } else {
            List<TreeNode<T>> childList = this.getChildList();
            int childNumber = childList.size();

            for (int i = 0; i < childNumber; i++) {
                TreeNode childNode = childList.get(i);
                if (childNode.insertJuniorNode(treeNode)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 找到一颗树中某个节点
     *
     * @param id
     * @return
     */
    public TreeNode findTreeNodeById(int id) {
        if (this.selfId == id) {
            return this;
        }
        if (childList.isEmpty() || childList == null) {
            return null;
        } else {
            int childNumber = childList.size();
            for (int i = 0; i < childNumber; i++) {
                TreeNode child = childList.get(i);
                TreeNode resultNode = child.findTreeNodeById(id);
                if (resultNode != null) {
                    return resultNode;
                }
            }
            return null;
        }
    }

    /**
     * 遍历一棵树，层次遍历
     */
    public List<T> traverse() {
        List<T> result = new LinkedList<>();
        if (selfId < 0) {
            return result;
        }
        result.add(getObj());
        if (childList == null || childList.isEmpty()) {
            return result;
        }
        int childNumber = childList.size();
        for (int i = 0; i < childNumber; i++) {
            TreeNode child = childList.get(i);
            result.addAll(child.traverse());
        }
        return result;
    }


}