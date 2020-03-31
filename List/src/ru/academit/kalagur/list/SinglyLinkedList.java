package ru.academit.kalagur.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getFirstElement() {
        if (count == 0) {
            throw new NullPointerException("Список пуст. Невозможно получить первый элемент");
        }

        return head.getData();
    }

    public T getElement(int index) {
        if (count == 0) {
            throw new NullPointerException("Список пуст. Невозможно получить элемент по индексу");
        }

        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Список не содержит элемент по указанному индексу");
        }

        ListItem<T> p = findChosenElement(index);

        return p.getData();
    }

    public T setElement(int index, T data) {
        if (count == 0) {
            throw new NullPointerException("Список пуст. Невозможно установить значение элемента по индексу");
        }

        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Список не содержит элемент по указанному индексу");
        }

        ListItem<T> p = findChosenElement(index);
        T temp = p.getData();
        p.setData(data);

        return temp;
    }

    public T removeElement(int index) {
        if (count == 0) {
            throw new NullPointerException("Список пуст. Невозможно удалить элемент по индексу");
        }

        if (index >= count || index < 0) {
            throw new IndexOutOfBoundsException("Список не содержит элемент по указанному индексу");
        }

        if (index == 0) {
            return removeFirstElement();
        }

        ListItem<T> prev = findChosenAndPreviousElement(index)[0];
        ListItem<T> p = findChosenAndPreviousElement(index)[1];

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

        ListItem<T> prev = findChosenAndPreviousElement(index)[0];
        ListItem<T> element = new ListItem<>(data, prev.getNext());
        prev.setNext(element);
        ++count;
    }

    public boolean remove(T data) {
        if (count == 0) {
            throw new NullPointerException("Список пуст. Невозможно удалить элемент");
        }

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
        head = new ListItem<>(data, head);
        ++count;
    }

    public T removeFirstElement() {
        if (count == 0) {
            throw new NullPointerException("Список пуст. Невозможно удалить первый элемент");
        }

        T temp = head.getData();

        if (count == 1) {
            head = null;
        } else {
            head = head.getNext();
        }

        --count;

        return temp;
    }

    public void tern() {
        if (count == 0) {
            throw new NullPointerException("Список пуст. Невозможно развернуть список");
        }

        if (count == 1) {
            return;
        }

        int index = 0;
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            ++index;
            insertFirstElement(removeElement(index));
        }
    }

    public SinglyLinkedList<T> getTurn() {
        SinglyLinkedList<T> invertedList = new SinglyLinkedList<>();
        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            invertedList.insertFirstElement(p.getData());
        }

        return invertedList;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copiedList = getTurn();
        copiedList.tern();
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

    private ListItem<T>[] findChosenAndPreviousElement(int index) {
        ListItem<T> p = head;
        ListItem<T> prev = null;

        for (int i = 0; i < index; ++i) {
            prev = p;
            p = p.getNext();
        }

        return new ListItem[]{prev, p};
    }
}