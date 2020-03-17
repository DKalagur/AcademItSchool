package ru.academit.kalagur.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getSize() {
        return count;
    }

    public T getFirstElement() {
        return head.getData();
    }

    public T getElement(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Список не содержит элемент по указанному индексу");
        }

        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        return p.getData();
    }

    public T changeElement(int index, T data) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Список не содержит элемент по указанному индексу");
        }

        ListItem<T> p = head;

        for (int i = 0; i < index; ++i) {
            p = p.getNext();
        }

        T temp = p.getData();
        p.setData(data);

        return temp;
    }

    public T removeElement(int index) {
        if (index >= count || index < 0) {
            throw new IllegalArgumentException("Список не содержит элемент по указанному индексу");
        }

        if (index == 0) {
            T temp = head.getData();
            removeFirstElement();

            return temp;
        }

        ListItem<T> p = head;
        ListItem<T> prev = null;

        for (int i = 0; i < index; ++i) {
            prev = p;
            p = p.getNext();
        }

        T temp = p.getData();
        prev.setNext(p.getNext());
        --count;

        return temp;
    }

    public void insertElement(int index, T data) {
        if (index > count || index < 0) {
            throw new IllegalArgumentException("Список не содержит элемент по указанному индексу");
        }

        if (index == 0) {
            insertFirstElement(data);
            return;
        }

        ListItem<T> p = head;
        ListItem<T> prev = null;

        for (int i = 0; i < index; ++i) {
            prev = p;
            p = p.getNext();
        }

        ListItem<T> element = new ListItem<>(data, prev.getNext());
        prev.setNext(element);
        ++count;
    }

    public boolean removeValue(T data) {
        if (head.getData().equals(data)) {
            removeFirstElement();

            return true;
        }

        for (ListItem<T> prev = head, p = head.getNext(); p != null; prev = p, p = p.getNext()) {
            if (p.getData().equals(data)) {

                prev.setNext(p.getNext());
                --count;

                return true;
            }
        }
        return false;
    }


    public void insertFirstElement(T data) {
        /*ListItem p = new ListItem(data, head);
        head = p;*/
        head = new ListItem<>(data, head);
        ++count;
    }

    public T removeFirstElement() {
        T temp = head.getData();
        head = head.getNext();
        --count;

        return temp;
    }

    public SinglyLinkedList<T> ternList() {
        SinglyLinkedList<T> invertedList = new SinglyLinkedList<>();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            invertedList.insertFirstElement(p.getData());
        }

        return invertedList;
    }

    public SinglyLinkedList<T> copyList() {
        SinglyLinkedList<T> copied1List = ternList();

        return copied1List.ternList();
    }

    @Override
    public String toString() {
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
}