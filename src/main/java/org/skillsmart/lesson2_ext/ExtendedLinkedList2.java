package org.skillsmart.lesson2_ext;

/**
 * Ограничения текущего решения:
 * у node = getHead() поле prev != null
 * у node = getTail() поле next != null
 */
public class ExtendedLinkedList2 {
    private final DummyNode _head;
    private final DummyNode _tail;

    public ExtendedLinkedList2()
    {
        _head = new DummyNode();
        _tail = new DummyNode(_head);
    }

    public Node getHead() {
        if (_head.next == _tail) {
            return null;
        }
        return _head.next;
    }

    public Node getTail() {
        if (_tail.prev == _head) {
            return null;
        }
        return _tail.prev;
    }

    public void addInTail(Node _item)
    {
        _item.prev = _tail.prev;
        _tail.prev.next = _item;
        _tail.prev = _item;
        _item.next = _tail;
    }

    public void addToHead(Node _nodeToInsert)
    {
        _nodeToInsert.next = _head.next;
        _nodeToInsert.prev = _head;
        _head.next.prev = _nodeToInsert;
        _head.next = _nodeToInsert;

    }

    public Node find(int _value)
    {
        for (Node iNode = _head.next; iNode != _tail; iNode = iNode.next) {
            if (iNode.value == _value) {
                return iNode;
            }
        }
        return null;
    }

    public boolean remove(int _value)
    {
        for (Node iNode = _head.next; iNode != _tail; iNode = iNode.next) {
            if (iNode.value == _value) {
                iNode.prev.next = iNode.next;
                iNode.next.prev = iNode.prev;
                return true;
            }
        }
        return false;
    }

    public void clear()
    {
        _head.next = _tail;
        _tail.prev = _head;
    }

    public int count()
    {
        int cnt = 0;
        for (Node iNode = _head.next; iNode != _tail; iNode = iNode.next) {
            cnt += 1;
        }
        return cnt;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert)
    {
        if (_nodeAfter == null) {
            addToHead(_nodeToInsert);
            return;
        }
        for (Node iNode = _head.next; iNode != _tail; iNode = iNode.next) {
            if (iNode == _nodeAfter) {
                _nodeToInsert.prev = iNode;
                _nodeToInsert.next = iNode.next;
                iNode.next.prev = _nodeToInsert;
                iNode.next = _nodeToInsert;
                return;
            }
        }
    }
}

class Node
{
    public Integer value;
    public Node next;
    public Node prev;

    public Node(Integer _value)
    {
        value = _value;
        next = null;
        prev = null;
    }
}

class DummyNode extends Node
{
    DummyNode() {
        super(null);
        prev = null;
        next = null;
    }
    DummyNode(Node _prev) {
        super(null);
        prev = _prev;
        _prev.next = this;
    }
}