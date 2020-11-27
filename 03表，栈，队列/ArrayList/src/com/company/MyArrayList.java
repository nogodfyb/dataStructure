package com.company;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<E> implements Iterable<E> {
    /**
     * 默认初始容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * MyArrayList的大小尺寸;也就是集合的元素个数
     */
    private int size;

    /**
     * 数据存储区域
     */
    private E[] elementData;

    public MyArrayList() {
        doClear();
    }

    /**
     * 清空集合
     */
    public void clear() {
        doClear();
    }

    /**
     * 清空集合;并将容量置为默认大小
     */
    private void doClear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    /**
     * 调整集合的容量
     *
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        E[] olaData = elementData;
        elementData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            elementData[i] = olaData[i];
        }
    }

    /**
     * 返回当前集合元素个数
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空集合
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 调整集合的容量为当前集合的实际尺寸大小
     */
    public void trimToSize() {
        ensureCapacity(size());
    }

    /**
     * 根据索引位置返回集合中的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException("非法索引位置!");
        return elementData[index];
    }

    /**
     * 在指定索引位置上设置内容
     *
     * @param index
     * @param element
     * @return
     */
    public E set(int index, E element) {
        if (index < 0 || index >= size())
            throw new ArrayIndexOutOfBoundsException("非法索引位置!");
        E oldValue = elementData[index];
        elementData[index] = element;
        return oldValue;
    }

    /**
     * 在指定位置上添加元素
     *
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("非法范围!");
        }
        //当前存储数据的区域已满,需要扩容
        if (elementData.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = size(); i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = element;
        size++;
    }

    /**
     * 在末尾添加元素
     * @param element
     */
    public boolean add(E element){
        add(size(),element);
        return  true;
    }

    /**
     * 移除指定索引位置上的元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size())
            throw new IllegalArgumentException("非法范围!");
        E oldElement=elementData[index];
        for (int i = index; i <size()-1 ; i++) {
            elementData[i]=elementData[i+1];
        }
        size--;
        return oldElement;
    }

    @Override
    public String toString() {
        return "MyArrayList{" +
                "size=" + size +
                ", elementData=" + Arrays.toString(elementData) +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<E>{

        private int current=0;

        @Override
        public boolean hasNext() {
            return current<size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("迭代器范围越界!");
            }
            return elementData[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
