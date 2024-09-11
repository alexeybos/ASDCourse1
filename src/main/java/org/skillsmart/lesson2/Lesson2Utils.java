package org.skillsmart.lesson2;

public class Lesson2Utils {

    public LinkedList2 mergeListsToSortedList(LinkedList2 _list1, LinkedList2 _list2) {
        _list1 = mergeSort(_list1);
        _list2 = mergeSort(_list2);
        return merge(_list1, _list2);
    }

    private LinkedList2 mergeSort(LinkedList2 list) {
        int size = list.count();
        if (size < 2) {
            return list;
        }
        LinkedList2 leftList = new LinkedList2();
        LinkedList2 rightList = new LinkedList2();
        Node node = list.head;
        for (int i = 0; i < size / 2; i++) {
            leftList.addInTail(new Node(node.value));
            node = node.next;
        }
        for (Node iNode = node; iNode != null; iNode = iNode.next) {
            rightList.addInTail(new Node(iNode.value));
        }

        leftList = mergeSort(leftList);
        rightList = mergeSort(rightList);

        return merge(leftList, rightList);

    }

    private LinkedList2 merge(LinkedList2 _list1, LinkedList2 _list2) {
        LinkedList2 resultList = new LinkedList2();
        Node node1 = _list1.head;
        Node node2 = _list2.head;
        //здесь мне показалось, что конструкция while более подходящая и я ее оставил
        while (node1 != null && node2 != null) {
            if (node1.value <= node2.value) {
                resultList.addInTail(new Node(node1.value));
                node1 = node1.next;
            } else {
                resultList.addInTail(new Node(node2.value));
                node2 = node2.next;
            }
        }
        for (Node iNode = node1; iNode != null; iNode = iNode.next) {
            resultList.addInTail(new Node(iNode.value));
        }
        for (Node iNode = node2; iNode != null; iNode = iNode.next) {
            resultList.addInTail(new Node(iNode.value));
        }

        return resultList;
    }
}
