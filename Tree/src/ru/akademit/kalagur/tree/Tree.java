package ru.akademit.kalagur.tree;

import java.util.LinkedList;
import java.util.Comparator;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int count;
    private boolean isComparatorReceived;
    private Comparator<T> externalComparator;

    public Tree(T data) {
        root = new TreeNode<>(data);
        ++count;
    }

    public Tree() {
    }

    public Tree(T data, Comparator<T> comparator) {
        externalComparator = comparator;
        root = new TreeNode<>(data);
        isComparatorReceived = true;
        ++count;
    }

    private int compare(TreeNode<T> currentNode, T data) {
        if (!isComparatorReceived) {
            return currentNode.compareTo(data);
        } else {
            return externalComparator.compare(currentNode.getData(), data);
        }
    }

    public void addNode(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            ++count;

            return;
        }

        TreeNode<T> currentNode = root;

        for (; ; ) {
            if (compare(currentNode, data) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    break;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    break;
                }
            }
        }
        ++count;
    }

    public boolean contains(T data) {
        TreeNode<T> currentNode = root;

        for (; ; ) {
            if (compare(currentNode, data) == 0) {
                return true;
            }

            if (compare(currentNode, data) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }
    }

    public boolean removeNode(T data) {
        TreeNode<T> currentNode = root;
        TreeNode<T> prev = null;
        boolean isLeftChild = true;

        while (compare(currentNode, data) != 0) {
            if (compare(currentNode, data) > 0) {
                if (currentNode.getLeft() != null) {
                    prev = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    prev = currentNode;
                    isLeftChild = false;
                    currentNode = currentNode.getRight();
                } else {
                    return false;
                }
            }
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (isLeftChild) {
                prev.setLeft(null);
            } else {
                prev.setRight(null);
            }
        } else if (currentNode.getLeft() == null) {
            if (isLeftChild) {
                prev.setLeft(currentNode.getRight());
            } else {
                prev.setRight(currentNode.getRight());
            }
        } else if (currentNode.getRight() == null) {
            if (isLeftChild) {
                prev.setLeft(currentNode.getLeft());
            } else {
                prev.setRight(currentNode.getLeft());
            }
        } else {
            TreeNode<T> prevMinNode = currentNode;
            TreeNode<T> minNode = currentNode.getRight();

            for (; ; ) {
                if (minNode.getLeft() != null) {
                    prevMinNode = minNode;
                    minNode = minNode.getLeft();
                } else {
                    break;
                }
            }

            if (minNode.getRight() != null) {
                prevMinNode.setLeft(minNode.getRight());
            }

            prev.setRight(minNode);
            minNode.setLeft(currentNode.getLeft());
            minNode.setRight(currentNode.getRight());
        }
        --count;

        return true;
    }

    public void iterateInWidth() {
        LinkedList<TreeNode<T>> queue = new LinkedList<>();
        TreeNode<T> currentNode;

        queue.add(root);

        while (!queue.isEmpty()) {
            currentNode = queue.remove();
            System.out.print(currentNode.getData() + ", ");

            if (currentNode.getLeft() != null) {
                queue.addLast(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.addLast(currentNode.getRight());
            }
        }
    }

    public void iterateInDepthRecursion() {
        visit(root);
    }

    private void visit(TreeNode<T> node) {
        System.out.print(node.getData() + ", ");

        if (node.getLeft() != null) {
            visit(node.getLeft());
        }

        if (node.getRight() != null) {
            visit(node.getRight());
        }
    }

    public void iterateInDepth() {
        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        TreeNode<T> currentNode;
        stack.add(root);

        while (!stack.isEmpty()) {
            currentNode = stack.removeLast();
            System.out.print(currentNode.getData() + ", ");

            if (currentNode.getRight() != null) {
                stack.addLast(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.addLast(currentNode.getLeft());
            }
        }
    }

    public int size() {
        return count;
    }
}