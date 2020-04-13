package ru.akademit.kalagur.arrayList;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private E[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException("Емкость списка должна быть больше 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        int MAX_VALUE = 1000000;

        if (size < MAX_VALUE) {
            return size;
        }

        return MAX_VALUE;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int index = indexOf(o);

        return index != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new arrayListIterator();
    }

    public class arrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен за время обхода списка итератором");
            }

            ++currentIndex;

            if (currentIndex > items.length) {
                throw new NoSuchElementException("Коллекция закончилась");
            }

            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length <= size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        a[size] = null;

        return a;
    }

    @Override
    public boolean add(E e) {
        if (size + 1 > items.length) {
            increaseCapacity();
        }

        items[size] = e;
        ++size;
        ++modCount;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object cItem : c) {
            if (!contains(cItem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        while (size + c.size() > items.length) {
            increaseCapacity();
        }

        int i = size;
        for (E cItem : c) {
            items[i] = cItem;
            ++i;
        }

        size = size + c.size();
        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }

        if (c.isEmpty()) {
            return false;
        }

        while (size + c.size() > items.length) {
            increaseCapacity();
        }

        if (index == size) {
            addAll(c);
        } else {
            System.arraycopy(items, index, items, index + c.size(), size - index);

            int i = index;

            for (E cItem : c) {
                items[i] = cItem;
                ++i;
            }

            size = size + c.size();
            ++modCount;
        }

        return true;
    }

    @Override //возвращает true если удален хотя бы один элемент
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object cItem : c) {
            int index = indexOf(cItem);

            if (index != -1) {
                remove(index);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override //возвращает true если список изменился
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        for (int i = 0; i < size; ++i) {
            if (!c.contains(items[i])) {
                remove(items[i]);
                isRemoved = true;
                --i;
            }
        }

        return (isRemoved);
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }

        E tmp = items[index];
        items[index] = element;

        return tmp;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Недопустимый индекс");
        }

        if (size >= items.length) {
            increaseCapacity();
        }

        if (index == size) {
            add(element);
        } else {
            System.arraycopy(items, index, items, index + 1, size - index);
            items[index] = element;
            ++size;
            ++modCount;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Недопустимый индекс. Список содержит меньше элементов");
        }

        E tmp = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }

        --size;
        ++modCount;

        return tmp;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (o != null) {
                if (items[i].equals(o)) {
                    return i;
                }
            }

            if (items[i] == null) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
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
        if (size == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (int i = 0; i < size - 1; ++i) {
            sb.append(items[i]);
            sb.append(", ");
        }

        sb.append(items[size - 1]);
        sb.append("}");

        return sb.toString();
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    public void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            items = Arrays.copyOf(items, minCapacity);
        }
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }
}
