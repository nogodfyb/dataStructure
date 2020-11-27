package com.company;

import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {

    /**
     * 首节点只是辅助性节点;不负责存储数据
     */
    private Node<E> first;
    /**
     * 尾节点同样只是辅助性节点;不负责存储数据
     */
    private Node<E> last;
    private int size;
    private int modCount=0;
    private static class Node<E> {

        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public E data;
        public Node<E> prev;
        public Node<E> next;
    }

    public void clear(){
        doClear();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return  size==0;
    }

    public boolean add(E element){
        add(size(),element);
        return true;
    }

    public void add(int index, E element) {
        addBefore(getNode(index,0,size()),element);
    }

    public MyLinkedList() {
        doClear();
    }

    private void doClear() {
        first=new Node<E>(null,null,null);
        last=new Node<E>(null,first,null);
        first.next=last;
        size=0;
        modCount++;
    }

    /**
     * 在指定节点前添加一个节点
     * @param p
     * @param element
     */
    private void addBefore(Node<E> p,E element ) {
        //新节点的前驱是节点p的前驱;后继是节点p
        Node<E> newNode = new Node<>(element, p.prev, p);
        //节点p的前驱节点的后继指向新节点
        newNode.prev.next=newNode;
        //节点p的前驱是新节点
        p.prev=newNode;
        size++;
        modCount++;
    }

    /**
     * 删除指定节点
     * @param p
     * @return
     */
    private E remove(Node<E> p){
        //节点p的后继节点的前驱指向节点p的前驱节点
        p.next.prev=p.prev;
        //节点p的前驱节点的后继指向节点p的后继节点
        p.prev.next=p.next;
        size--;
        modCount++;
        return p.data;
    }

    /**
     * 得到指定索引位置的节点
     * @param index
     * @return
     */
    private Node<E> getNode(int index){
        return  getNode(index,0,size()-1);
    }

    private Node<E> getNode(int index,int lower,int upper){
        Node<E> p;
        if (index<lower||index>upper)
            throw new IndexOutOfBoundsException();
        if (index<size()/2){
            p=first.next;
            for (int i = 0; i < index; i++) {
                p=p.next;
            }
        }else {
            p=last;
            for (int i = size(); i >index ; i--) {
                p=p.prev;
            }
        }
        return p;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
