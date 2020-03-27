package ru.akademit.kalagur.arrayList;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private int capacity;
    private E[] items;
    private int length = 0;

    public MyArrayList() {
        capacity = 11;
        items = (E[]) new Object[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < length; ++i) {
            if (items[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator<E>(items);
    }

    public class MyIterator<E> implements Iterator {
        private E[] objects;
        private int index = 0;

        private MyIterator(E[] objects) {
            this.objects = objects;
        }

        @Override
        public boolean hasNext() {
            return objects[index] != null;
        }

        @Override
        public E next() {
            return objects[index++];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (length > a.length) {
            return (T[]) Arrays.copyOf(items, length, a.getClass());
        }

        T[] temp = (T[]) Arrays.copyOf((T[]) items, a.length, a.getClass());

        if (a.length > length + 1) {
            System.arraycopy(a, length + 1, temp, length + 1, a.length - length - 1);
        }

        return temp;
    }

    @Override
    public boolean add(E e) {
        if (length >= capacity) {
            increaseCapacity();
        }

        items[length] = e;
        ++length;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < length; ++i) {
            if (items[i].equals(o)) {

                System.arraycopy(items, i + 1, items, i, length - 1 - i);
                items[length] = null;
                --length;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        E[] itemsCopy = Arrays.copyOf(items, length);

        for (Object cItem : c) {
            boolean isFound = false;

            for (int i = 0; i < length; ++i) {
                if (cItem.equals(itemsCopy[i])) {
                    itemsCopy[i] = null;
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

        for (E cItem : c) {
            add(cItem);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > length) {
            throw new IndexOutOfBoundsException("Недопустимый индекс. Список содержит меньше элементов");
        }

        if (index == length) {
            addAll(c);
        }

        for (E cItem : c) {
            add(index, cItem);
            index++;
        }

        return true;
    }

    @Override //возвращает true если удален хотя бы один элемент
    public boolean removeAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        boolean isRemoved = false;

        while (iterator.hasNext()) {
            Object temp = iterator.next();
            for (int i = 0; i < length; ++i) {
                if (temp.equals(items[i])) {
                    remove(i);
                    isRemoved = true;
                }
            }
        }

        return isRemoved;
    }

    @Override //возвращает true если список изменился
    public boolean retainAll(Collection<?> c) {
        Iterator<?> iterator = c.iterator();
        boolean[] isContains = new boolean[length];
        boolean isChanged = false;

        while (iterator.hasNext()) {
            Object temp = iterator.next();
            for (int i = 0; i < length; ++i) {
                if (items[i].equals(temp)) {
                    isContains[i] = true;
                    isChanged = true;
                }
            }
        }

        if (!isChanged) {
            return false;
        }

        for (int i = 0; i < length; ++i) {
            if (!isContains[i]) {
                items[i] = null;
            }
        }

        for (int i = 0; i < length; ++i) {
            if (items[i] == null) {
                remove(i);
                --i;
            }
        }

        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < length; ++i) {
            items[i] = null;
        }
        length = 0;
    }

    @Override
    public E get(int index) {
        return items[index];
    }

    @Override
    public E set(int index, E element) {
        E tmp = items[index];
        items[index] = element;

        return tmp;
    }

    @Override
    public void add(int index, E element) {
        if (length >= capacity) {
            increaseCapacity();
        }

        E prev = items[index];
        items[index] = element;

        for (int i = index + 1; i < length; ++i) {
            E current = items[i];
            items[i] = prev;
            prev = current;
        }

        items[length] = prev;
        ++length;
    }

    @Override
    public E remove(int index) {
        if (index >= length) {
            throw new IllegalArgumentException("Недопустимый индекс. Список содержит меньше элементов");
        }

        E tmp = items[index];
        System.arraycopy(items, index + 1, items, index, length - 1 - index);

        items[length] = null;
        --length;

        return tmp;
    }


    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < length; ++i) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = length - 1; i >= 0; --i) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override //не надо переопределять
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override //не надо переопределять
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override //не надо переопределять
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override 
    public String toString() {
        if (length == 0) {
            return "{}";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("{");

            for (int i = 0; i < length - 1; ++i) {
                sb.append(items[i].toString());
                sb.append(", ");
            }

            sb.append(items[length - 1].toString());
            sb.append("}");

            return sb.toString();
        }
    }

    public void trimToSize() {
        capacity = length;
    }

    public void ensureCapacity(int minCapacity) {
        if (capacity < minCapacity) {
            capacity = minCapacity;
        }
    }

    public int getCapacity() {
        return capacity;
    }

    private void increaseCapacity() {
        capacity = length * 2;
    }
}
