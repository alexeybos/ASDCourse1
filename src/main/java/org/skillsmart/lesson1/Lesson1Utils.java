package org.skillsmart.lesson1;

public class Lesson1Utils {

    public LinkedList sumEqualLengthLists1(LinkedList _firstList, LinkedList _secondList) {
        LinkedList resultList = new LinkedList();
        if (_firstList == null || _secondList == null) {
            return resultList;
        }
        if (_firstList.count() == _secondList.count()) {
            Node nodeFromFirstList = _firstList.head;
            Node nodeFromSecondList = _secondList.head;
            while (nodeFromFirstList != null) {
                resultList.addInTail(new Node(nodeFromFirstList.value + nodeFromSecondList.value));
                nodeFromFirstList = nodeFromFirstList.next;
                nodeFromSecondList = nodeFromSecondList.next;
            }
        }
        return resultList;
    }

    public LinkedList sumEqualLengthLists2(LinkedList _firstList, LinkedList _secondList) {
        LinkedList resultList = new LinkedList();
        if (_firstList == null || _secondList == null) {
            return resultList;
        }
        Node nodeFromFirstList = _firstList.head;
        Node nodeFromSecondList = _secondList.head;
        while (nodeFromFirstList != null) {
            if (nodeFromSecondList == null) {
                return resultList;
            }
            nodeFromSecondList = nodeFromSecondList.next;
            nodeFromFirstList = nodeFromFirstList.next;
        }
        if (nodeFromSecondList == null) {
            nodeFromFirstList = _firstList.head;
            nodeFromSecondList = _secondList.head;
            while (nodeFromFirstList != null) {
                resultList.addInTail(new Node(nodeFromFirstList.value + nodeFromSecondList.value));
                nodeFromFirstList = nodeFromFirstList.next;
                nodeFromSecondList = nodeFromSecondList.next;
            }
        }
        return resultList;
    }

    public LinkedList sumEqualLengthLists3(LinkedList _firstList, LinkedList _secondList) {
        LinkedList resultList = new LinkedList();
        if (_firstList == null || _secondList == null) {
            return resultList;
        }
        Node nodeFromFirstList = _firstList.head;
        Node nodeFromSecondList = _secondList.head;
        while (nodeFromFirstList != null) {
            if (nodeFromSecondList == null) {
                return new LinkedList();
            }
            resultList.addInTail(new Node(nodeFromFirstList.value + nodeFromSecondList.value));
            nodeFromFirstList = nodeFromFirstList.next;
            nodeFromSecondList = nodeFromSecondList.next;
        }
        if (nodeFromSecondList != null) {
            return new LinkedList();
        }
        return resultList;
    }
}
