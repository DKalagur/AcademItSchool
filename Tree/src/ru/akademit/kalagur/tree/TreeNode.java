package ru.akademit.kalagur.tree;

public class TreeNode<T> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode(T data, TreeNode<T> left, TreeNode<T> right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    /*public TreeNode(T data) {
        left = null;
        right = null;
        this.data = data;
    }*/


    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public void setData(T data) {
        this.data = data;
    }
/*
    @Override
    public int compareTo(T o) {
        return data.compareTo(o);
    }
*/
}
