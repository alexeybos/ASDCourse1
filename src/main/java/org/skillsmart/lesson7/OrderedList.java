package org.skillsmart.lesson7;

import java.util.*;


class Node<T>
{
    public T value;
    public Node<T> next, prev;

    public Node(T _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T>
{
    public Node<T> head, tail;
    private boolean _ascending;

    public OrderedList(boolean asc)
    {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2)
    {
        if (v1 instanceof Number && v2 instanceof Number) {
            if (((Number) v1).doubleValue() < ((Number) v2).doubleValue()) {
                return -1;
            }
            if (((Number) v1).doubleValue() > ((Number) v2).doubleValue()) {
                return 1;
            }
        }
        if (v1 instanceof String && v2 instanceof String) {
            int result = ((String) v1).compareTo((String) v2);
            if (result < 0) {
                return -1;
            }
            if (result > 0) {
                return 1;
            }
        }
        return 0;
    }

    public void add(T value)
    {
        // автоматическая вставка value
        // в нужную позицию
    }

    public Node<T> find(T val)
    {
        return null; // здесь будет ваш код
    }

    public void delete(T val)
    {
        // здесь будет ваш код
    }

    public void clear(boolean asc)
    {
        _ascending = asc;
        // здесь будет ваш код
    }

    public int count()
    {
        return 0; // здесь будет ваш код подсчёта количества элементов в списке
    }

    ArrayList<Node<T>> getAll()
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while(node != null)
        {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}



