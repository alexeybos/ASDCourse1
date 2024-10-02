package org.skillsmart.lesson7;

public class Lesson7Utils {

    /**
     * Для упрощения предполагаем, что списки упорядочены однонаправленно
     * 1. Берем первые элементы списка.
     * 2. Сравниваем элементы
     * 3. Наименьший (наибольший) элемент кладем в результирующий список.
     *    В этом случае элемент всегда будет tail, т.е. будет вставляться сразу, без дополнительного цикла по списку.
     *    В списке, из которого взяли элемент переходим в next. Идем на шаг 2.
     * 4. Когда заканчивается один из списков, перекладываем в результирующий список оставшиеся элементы из второго списка
     */
    public OrderedList<Integer> mergeOrderedLists(OrderedList<Integer> _list1, OrderedList<Integer> _list2) {
        boolean asc = _list1.isAscending();
        OrderedList<Integer> resultList = new OrderedList<>(asc);
        Node<Integer> leftNode = _list1.head;
        Node<Integer> rightNode = _list2.head;
        for (; leftNode != null && rightNode != null; ) {
            if ((asc && _list1.compare(leftNode.value, rightNode.value) < 0) ||
                    (!asc && _list1.compare(leftNode.value, rightNode.value) > 0)) {
                resultList.add(leftNode.value);
                leftNode = leftNode.next;
            } else {
                resultList.add(rightNode.value);
                rightNode = rightNode.next;
            }
        }
        Node<Integer> node;
        if (leftNode == null) {
            node = rightNode;
        } else {
            node = leftNode;
        }
        for (; node != null; node = node.next) {
            resultList.add(node.value);
        }

        return resultList;
    }
}
