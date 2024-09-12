package org.skillsmart.lesson2_onedummy;

/**
 * Ограничения текущего решения:
 * у node = getHead() поле prev != null
 * у node = getTail() поле next != null
 */
public class ExtendedLinkedList2 {
    private final DummyNode _dummyNode;
    
    public ExtendedLinkedList2()
    {
        _dummyNode = new DummyNode();
    }

    public Node getHead() {
        if (_dummyNode.next instanceof DummyNode) {
            return null;
        }
        return _dummyNode.next;
    }

    public Node getTail() {
        if (_dummyNode.prev instanceof DummyNode) {
            return null;
        }
        return _dummyNode.prev;
    }

    public void addInTail(Node _item)
    {
        _item.prev = _dummyNode.prev;
        _dummyNode.prev.next = _item;
        _dummyNode.prev = _item;
        _item.next = _dummyNode;
    }

    public void addToHead(Node _nodeToInsert)
    {
        _nodeToInsert.next = _dummyNode.next;
        _nodeToInsert.prev = _dummyNode;
        _dummyNode.next.prev = _nodeToInsert;
        _dummyNode.next = _nodeToInsert;

    }

    public Node find(int _value)
    {
        for (Node iNode = _dummyNode.next; !(iNode instanceof DummyNode); iNode = iNode.next) {
            if (iNode.value == _value) {
                return iNode;
            }
        }
        return null;
    }

    public boolean remove(int _value)
    {
        for (Node iNode = _dummyNode.next; !(iNode instanceof DummyNode); iNode = iNode.next) {
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
        _dummyNode.next = _dummyNode;
        _dummyNode.prev = _dummyNode;
    }

    public int count()
    {
        int cnt = 0;
        for (Node iNode = _dummyNode.next; !(iNode instanceof DummyNode); iNode = iNode.next) {
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
        for (Node iNode = _dummyNode.next; !(iNode instanceof DummyNode); iNode = iNode.next) {
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
    public DummyNode() {
        super(null);
        this.next = this;
        this.prev = this;
    }
}

