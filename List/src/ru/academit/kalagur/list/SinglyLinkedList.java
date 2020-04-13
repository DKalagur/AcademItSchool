package ru.academit.kalagur.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getFirstElement() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст. Невозможно получить первый элемент");
        }

        return head.getData();
    }

    public T getElement(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Список не содержит элемент по указанному индексу");
        }

        ListItem<T> p = findChosenElement(index);

        return p.getData();
    }

    public T setElement(int index, T data) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Список не содержит элемент по указанному индексу");
        }

        ListItem<T> p = findChosenElement(index);
        T temp = p.getData();
        p.setData(data);

        return temp;
    }

    public T removeElement(int index) {
        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Список не содержит элемент по указанному индексу");
        }

        if (index == 0) {
            return removeFirstElement();
        }

        ListItem<T> prev = findChosenElement(index - 1);
        ListItem<T> p = prev.getNext();

        T temp = p.getData();
        prev.setNext(p.getNext());
        --count;

        return temp;
    }

    public void insertElement(int index, T data) {
        if (index > count || index < 0) {
            throw new IndexOutOfBoundsException(" Невозможно вставить элемент по указанному индексу. Длина списка меньше введенного значения");
        }

        if (index == 0) {
            insertFirstElement(data);
            return;
        }

        ListItem<T> prev = findChosenElement(index - 1);
        ListItem<T> element = new ListItem<>(data, prev.getNext());
        prev.setNext(element);
        ++count;
    }

    public boolean remove(T data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(data, head.getData())) {
            removeFirstElement();

            return true;
        }

        for (ListItem<T> prev = head, p = head.getNext(); p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(data, p.getData())) {
                prev.setNext(p.getNext());
                --count;

                return true;
            }
        }

        return false;
    }

    public void insertFirstElement(T data) {
        head = new ListItem<>(data, head);
        ++count;
    }

    public T removeFirstElement() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст. Невозможно удалить первый элемент");
        }

        T temp = head.getData();
        head = head.getNext();
        --count;

        return temp;
    }

    public void turn() {
        ListItem<T> currentElement = head;
        ListItem<T> prev = null;

        while (currentElement != null) {
            ListItem<T> temp = currentElement.getNext();
            currentElement.setNext(prev);
            prev = currentElement;
            currentElement = temp;
        }

        head = prev;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copiedList = new SinglyLinkedList<>();

        if (head == null) {
            return copiedList;
        }

        copiedList.head = new ListItem<>(head.getData(), null);
        ListItem<T> currentElement = copiedList.head;

        for (ListItem<T> p = head.getNext(); p != null; p = p.getNext()) {
            ListItem<T> copiedListItem = new ListItem<>(p.getData(), null);
            currentElement.setNext(copiedListItem);
            currentElement = copiedListItem;
        }

        copiedList.count = getSize();

        return copiedList;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        ListItem<T> p = head;

        for (int i = 0; i < count - 1; ++i) {
            sb.append(p.getData());
            sb.append(",");
            p = p.getNext();
        }

        sb.append(p.getData());
        sb.append("}");

        return sb.toString();
    }

    private ListItem<T> findChosenElement(int index) {
        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        return p;
    }
}