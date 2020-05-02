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

    public TreeNode(T data) {
        this.data = data;
    }

    TreeNode<T> getLeft() {
        return left;
    }

    void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    TreeNode<T> getRight() {
        return right;
    }

    void setRight(TreeNode<T> right) {
        this.right = right;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }
}