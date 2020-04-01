package ru.akademit.kalagur.hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyHashTable<E> implements Collection<E> {
    private int capacity = 16;
    private ArrayList<E>[] table = new ArrayList[capacity];
    private int length = 0;

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        table = new ArrayList[capacity];
    }

    public MyHashTable() {
    }

    public MyHashTable(Collection<? extends E> col) {
        addAll(col);
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return (length == 0);
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode() % capacity);

        if (table[index] == null) {
            return false;
        } else {
            return table[index].contains(o);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>();
    }

    public class MyIterator<E> implements Iterator {
        private E[] objects;
        private int index = 0;

        private MyIterator() {
            this.objects = (E[]) toArray();
        }

        @Override
        public boolean hasNext() {
            return !(index == objects.length);
        }

        @Override
        public E next() {
            return objects[index++];
        }
    }

    @Override
    public Object[] toArray() {
        int index = 0;
        Object[] array = new Object[length];

        for (ArrayList<E> tableItem : table) {
            if (tableItem == null) {
                continue;
            }

            tableItem.toArray();
            System.arraycopy(tableItem.toArray(), 0, array, index, tableItem.toArray().length);
            index += tableItem.toArray().length;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] array = toArray();
        if (length > a.length) {
            return (T[]) Arrays.copyOf(array, length, a.getClass());
        }

        T[] temp = (T[]) Arrays.copyOf(array, a.length, a.getClass());
        System.arraycopy(a, length + 1, temp, length + 1, a.length - length - 1);

        return temp;
    }

    @Override
    public boolean add(E e) {
        int index = Math.abs(e.hashCode() % capacity);

        if (table[index] == null) {
            table[index] = new ArrayList<>();
            table[index].add(e);
            length++;

            return true;
        }

        if (!table[index].contains(e)) {
            table[index].add(e);
            length++;

            return true;
        }

        return false;

    }

    @Override
    public boolean remove(Object o) {
        if (length == 0) {
            throw new NullPointerException("Невозможно удалить элемент из списка, т.к. список пуст");
        }

        if (!contains(o)) {
            return false;
        }

        int index = Math.abs(o.hashCode() % capacity);
        table[index].remove(o);
        length--;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("Недопустимый аргумент (передана пустая коллекция)");
        }

        Object[] array = toArray();

        for (Object cItem : c) {
            boolean isFound = false;

            for (int i = 0; i < array.length; ++i) {
                if (cItem.equals(array[i])) {
                    array[i] = null;
                    isFound = true;
                    break;
                }
            }

            if (!isFound) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) {
            throw new IllegalArgumentException("Недопустимый аргумент (передана пустая коллекция)");
        }

        for (E cItem : c) {
            add(cItem);
        }

        return true;
    }

    @Override //возвращает true если удален хотя бы один элемент
    public boolean removeAll(Collection<?> c) {
        if (length == 0) {
            throw new NullPointerException("Невозможно удалить элементы из списка, т.к. список пуст");
        }

        if (c == null) {
            throw new IllegalArgumentException("Недопустимый аргумент (передана пустая коллекция)");
        }

        boolean isChanged = false;

        for (Object cItem : c) {
            if (contains(cItem)) {
                int index = Math.abs(cItem.hashCode() % capacity);
                table[index].remove(cItem);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ArrayList<Object> list = new ArrayList<>(Arrays.asList(toArray()));

        for (Object cItem : c) {
            if (contains(cItem)) {
                list.remove(cItem);
            }
        }

        if (list.isEmpty()) {
            return false;
        }

        removeAll(list);

        return true;
    }

    @Override
    public void clear() {
        for (ArrayList<E> tableItem : table) {
            if (tableItem != null) {
                tableItem.clear();
            }
        }

        length = 0;
    }

    public String toString() {
        if (length == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < capacity - 1; i++) {
            if (table[i] == null) {
                sb.append("[]");
            } else {
                sb.append(table[i].toString());
            }
            sb.append(", ");
        }

        if (table[capacity - 1] == null) {
            sb.append("[]");
        } else {
            sb.append(table[capacity - 1].toString());
        }

        sb.append("}");
        return sb.toString();
    }

    public int getCapacity() {
        return capacity;
    }
}
