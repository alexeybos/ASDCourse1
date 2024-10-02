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
    private int _count;

    public OrderedList(boolean asc)
    {
        head = null;
        tail = null;
        _ascending = asc;
        _count = 0;
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
            int result = ((String) v1).trim().compareTo(((String) v2).trim());
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
        _count += 1;
        Node<T> item = new Node<>(value);
        if (head == null) {
            this.head = item;
            this.tail = item;
            this.head.next = null;
            this.head.prev = null;
            return;
        }
        if ((_ascending && compare(this.head.value, value) >= 0) ||
                (!_ascending && compare(value, this.head.value) >= 0)) { //new head value
            item.next = this.head;
            this.head.prev = item;
            this.head = item;
            return;
        }
        if ((_ascending && compare(value, this.tail.value) >= 0) ||
                (!_ascending && compare(this.tail.value, value) >= 0)) { //new tail value
            item.prev = this.tail;
            this.tail.next = item;
            this.tail = item;
            return;
        }
        Node<T> iNode = this.head;
        while ((_ascending && compare(value, iNode.value) > 0) || (!_ascending && compare(value, iNode.value) < 0)) {
            iNode = iNode.next;
        }
        item.next = iNode;
        iNode.prev.next = item;
        item.prev = iNode.prev;
        iNode.prev = item;
    }

    public Node<T> find(T val)
    {
        Node<T> node = this.head;
        for (int i = 0; i < this._count; i++) {
            int compareResult = compare(node.value, val);
            if (compareResult == 0) {
                return node;
            }
            if ((_ascending && compareResult > 0) || (!_ascending && compareResult < 0)) {
                return null;
            }
            node = node.next;
        }
        return null;
    }

    public void delete(T val)
    {
        if (_count == 0) {
            return;
        }
        Node<T> node = find(val);
        if (node != null) {
            if (node.prev == null) {
                this.head = node.next;
            } else {
                node.prev.next = node.next;
            }
            if (node.next == null) {
                this.tail = node.prev;
            } else {
                node.next.prev = node.prev;
            }
            _count -= 1;
        }

    }

    public void clear(boolean asc)
    {
        _ascending = asc;
        _count = 0;
        this.head = null;
        this.tail = null;
    }

    public int count()
    {
        return _count;
    }

    public void deleteDuplicates() {
        for (Node<T> iNode = this.head; iNode.next != null; ) {
            if (compare(iNode.value, iNode.next.value) == 0) {
                _count -= 1;
                iNode.next = iNode.next.next;
                if (iNode.next != null) {
                    iNode.next.prev = iNode;
                } else { //это новый tail
                    this.tail = iNode;
                }
            } else {
                iNode = iNode.next;
            }
        }
    }

    public boolean isAscending() {
        return _ascending;
    }

    public boolean isSubListExist(OrderedList<T> subList) {
        if (this._count < subList._count) {
            return false;
        }
        Node<T> subListNode = subList.head;
        Node<T> mainListElement = find(subListNode.value);
        if (mainListElement == null) {
            return false;
        }
        for (; subListNode != null && mainListElement != null; subListNode = subListNode.next, mainListElement = mainListElement.next) {
            if (compare(mainListElement.value, subListNode.value) != 0) {
                return false;
            }
        }
        return true;
    }

    public T getMostCommonValue() {
        if (_count == 0) {
            return null;
        }
        int cnt = 1;
        int maxCnt = cnt;
        T countValue = head.value;
        T maxValue = head.value;
        for (Node<T> iNode = head.next; iNode != null; iNode = iNode.next) {
            if (compare(countValue, iNode.value) == 0) {
                cnt++;
            } else {
                if (maxCnt < cnt) {
                    maxValue = countValue;
                    maxCnt = cnt;
                }
                countValue = iNode.value;
                cnt = 1;
            }
        }
        if (maxCnt < cnt) {
            return countValue;
        }
        return maxValue;
    }

    public int getIndex(T value) {
        //сразу отсечем "легкие" варианты:
        if (_count == 0) {
            return -1;
        }
        int compareHeadResult = compareByAsc(value, head.value);
        if (compareHeadResult == 0) {
            return 0;
        }
        int compareTailResult = compareByAsc(tail.value, value);
        if (compareTailResult == 0) {
            return _count - 1;
        }
        if (compareHeadResult < 0 || compareTailResult < 0) {
            return -1;
        }
        //собственно поиск индекса. O(log(n)) сравнений, O(n) смещений
        Node<T> iNode = head;
        Node<T> leftNode = head;
        int leftIndex = 0;
        int rightIndex = _count - 1;
        int cnt = (rightIndex - leftIndex) / 2;
        for (; leftIndex < rightIndex; ) {
            int i = 0;
            for (; i < cnt; i++) {
                iNode = iNode.next;
            }
            int compareResult = compareByAsc(value, iNode.value);
            if (compareResult == 0) {
                return i + leftIndex;
            }
            if (compareResult < 0) {
                rightIndex = i + leftIndex;
                iNode = leftNode;
            } else {
                leftIndex = i + leftIndex;
                leftNode = iNode;
            }
            cnt = (rightIndex - leftIndex) / 2;
        }
        return -1;
    }

    /**
     * Возвращает взаимное положение элементов в зависимости от _ascending на "линии" списка
     * -1 - первый параметр лежит левее второго (т.е. он меньше при _ascending = true, больше при _ascending = false)
     * 0 - равны
     * 1 - первый параметр лежит правее второго (т.е. он больше при _ascending = true, меньше при _ascending = false)
     */
    private int compareByAsc(T firstValue, T secondValue) {
        if (_ascending) {
            return compare(firstValue, secondValue);
        }
        return compare(secondValue, firstValue);
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



