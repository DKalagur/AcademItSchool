package ru.akademit.kalagur.tree;

import java.util.LinkedList;
import java.util.Comparator;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int count;
    private Comparator<T> comparator;

    public Tree() {
    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree(T data, Comparator<T> comparator) {
        this.comparator = comparator;
        root = new TreeNode<>(data, null, null);
        ++count;
    }

    private int compare(TreeNode<T> currentNode, T data) {
        if (comparator != null) {
            return comparator.compare(currentNode.getData(), data);
        }

        Comparable<T> temp = (Comparable<T>) currentNode.getData();

        return temp.compareTo(data);
    }

    public void addNode(T data) {
        if (root == null) {
            root = new TreeNode<>(data, null, null);
            ++count;

            return;
        }

        TreeNode<T> currentNode = root;

        for (; ; ) {
            if (compare(currentNode, data) > 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data, null, null));
                    break;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
                } else {
                    currentNode.setRight(new TreeNode<>(data, null, null));
                    break;
                }
            }
        }
        ++count;
    }

    public boolean contains(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> currentNode = find(data)[1];

        return currentNode != null;
    }

    public boolean removeNode(T data) {
        if (root == null) {
            return false;
        }

        boolean isLeftChild = false;
        boolean isRoot = false;

        TreeNode<T>[] temp = find(data);
        TreeNode<T> prev = temp[0];
        TreeNode<T> currentNode = temp[1];

        if (currentNode == null) {
            return false;
        }

        // преверяем: удаляемый элемент является левым ребенком, правым ребенком или корнем
        if (prev == null) {
            isRoot = true;
        } else if (prev.getLeft() == currentNode) {
            isLeftChild = true;
        }

        // у удаляемого элемента нет детей
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (isRoot) {
                root = null;
            } else if (isLeftChild) {
                prev.setLeft(null);
            } else {
                prev.setRight(null);
            }
        // у удаляемого элемента только правый ребенок
        } else if (currentNode.getLeft() == null) {
            if (isRoot) {
                root = currentNode.getRight();
            } else if (isLeftChild) {
                prev.setLeft(currentNode.getRight());
            } else {
                prev.setRight(currentNode.getRight());
            }
        // у удаляемого элемента только левый ребенок
        } else if (currentNode.getRight() == null) {
            if (isRoot) {
                root = currentNode.getLeft();
            } else if (isLeftChild) {
                prev.setLeft(currentNode.getLeft());
            } else {
                prev.setRight(currentNode.getLeft());
            }
        // у удаляемого элемента 2 ребенка
        } else {
            TreeNode<T> prevMinNode = currentNode;
            TreeNode<T> minNode = currentNode.getRight();

            boolean existLeftChildRightSubtree = false; // если в правом поддереве нет левых детей-алгоритм проще

            // ищем самого левого ребенока
            while (minNode.getLeft() != null) {
                prevMinNode = minNode;
                minNode = minNode.getLeft();
                existLeftChildRightSubtree = true;
            }

            if (!existLeftChildRightSubtree) {
                prev.setRight(minNode);
                minNode.setLeft(prevMinNode.getLeft());
            } else {
                if (minNode.getRight() != null) {
                    prevMinNode.setLeft(minNode.getRight());
                } else {
                    prevMinNode.setLeft(null);
                }

                if (isRoot) {
                    root = minNode;
                } else if (isLeftChild) {
                    prev.setLeft(minNode);
                } else {
                    prev.setRight(minNode);
                }

                minNode.setLeft(currentNode.getLeft());
                minNode.setRight(currentNode.getRight());
            }
        }
        --count;

        return true;
    }

    public void iterateInWidth(Consumer<T> printer) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();

            printer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.addLast(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.addLast(currentNode.getRight());
            }
        }
    }

    public void iterateInDepthRecursion(Consumer<T> printer) {
        visit(root, printer);
    }

    private void visit(TreeNode<T> node, Consumer<T> printer) {
        if (root == null) {
            return;
        }

        printer.accept(node.getData());

        if (node.getLeft() != null) {
            visit(node.getLeft(), printer);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), printer);
        }
    }

    public void iterateInDepth(Consumer<T> printer) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode<T>> stack = new LinkedList<>();
        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.removeLast();
            printer.accept(currentNode.getData());

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

    private TreeNode<T>[] find(T data) {
        TreeNode<T> currentNode = root;
        TreeNode<T> prev = null;

        for (; ; ) {
            int compareResult = compare(currentNode, data);

            if (compareResult == 0) {
                return new TreeNode[]{prev, currentNode};
            }

            if (compareResult > 0) {
                if (currentNode.getLeft() != null) {
                    prev = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return new TreeNode[]{null, null};
                }
            } else {
                if (currentNode.getRight() != null) {
                    prev = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return new TreeNode[]{null, null};
                }
            }
        }
    }
}