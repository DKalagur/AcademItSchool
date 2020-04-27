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
        if (capacity < 0) {
            throw new IllegalArgumentException("Емкость списка должна быть больше 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new arrayListIterator();
    }

    public class arrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int currentModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (currentModCount != modCount) {
                throw new ConcurrentModificationException("Список был изменен за время обхода списка итератором");
            }

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            ++currentIndex;

            return items[currentIndex];
        }
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        ++modCount;

        if (size >= items.length) {
            increaseCapacity(size);
        }

        items[size] = e;
        ++size;

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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        ++modCount;
        checkIndex(index, 0);

        if (c.isEmpty()) {
            return false;
        }

        if (size + c.size() > items.length) {
            increaseCapacity(Math.max(c.size(), size));
        }

        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        for (E cItem : c) {
            items[i] = cItem;
            ++i;
        }

        size += c.size();

        return true;
    }

    @Override //возвращает true если удален хотя бы один элемент
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object cItem : c) {
            while (contains(cItem)) {
                remove(cItem);
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
                remove(i);
                isRemoved = true;
                --i;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        ++modCount;

        for (int i = 0; i < size; ++i) {
            items[i] = null;
        }

        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index, 1);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index, 1);

        E tmp = items[index];
        items[index] = element;

        return tmp;
    }

    @Override
    public void add(int index, E element) {
        ++modCount;
        checkIndex(index, 0);

        if (size >= items.length) {
            increaseCapacity(size);
        }

        if (index == size) {
            add(element);
            return;
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;
        ++size;
    }

    @Override
    public E remove(int index) {
        ++modCount;
        checkIndex(index, 1);

        E tmp = items[index];

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }

        items[size - 1] = null;
        --size;

        return tmp;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; ++i) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; --i) {
            if (Objects.equals(items[i], o)) {
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
            sb.append(items[i]).append(", ");
        }

        sb.append(items[size - 1]).append("}");

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

    private void increaseCapacity(int currentLength) {
        items = Arrays.copyOf(items, (currentLength + 1) * 2);
    }

    private void checkIndex(int index, int number) {
        switch (number) {
            case 0:
                if (index < 0 || index > size) {
                    throw new IndexOutOfBoundsException("Недопустимый индекс. Индекс " + index + " выходит за границы списка: (0, " + (size - 1) + ")+1");
                }
                break;
            case 1:
                if (index < 0 || index >= size) {
                    throw new IndexOutOfBoundsException("Недопустимый индекс. Индекс " + index + " выходит за границы списка: (0, " + (size - 1) + ")");
                }
        }
    }
}