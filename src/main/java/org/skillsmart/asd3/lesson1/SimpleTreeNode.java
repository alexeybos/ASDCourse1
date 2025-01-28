package org.skillsmart.asd3.lesson1;

import java.util.*;

public class SimpleTreeNode<T>
{
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null
    public int level;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent)
    {
        NodeValue = val;
        Parent = parent;
        Children = null;
        level = 0;
    }
}

class SimpleTree<T>
{
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root)
    {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild)
    {
        // ваш код добавления нового дочернего узла существующему ParentNode
        if (ParentNode == null && Root == null) {
            Root = NewChild;
        }
        if (ParentNode != null) {
            NewChild.Parent = ParentNode;
            if (ParentNode.Children == null) ParentNode.Children = new ArrayList<>();
            ParentNode.Children.add(NewChild);
        }
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete)
    {
        // ваш код удаления существующего узла NodeToDelete
        NodeToDelete.Parent.Children.remove(NodeToDelete);
        if (NodeToDelete.Parent.Children.isEmpty()) {
            NodeToDelete.Parent.Children = null;
        }
        NodeToDelete.Parent = null;

    }

    public List<SimpleTreeNode<T>> GetAllNodes()
    {
        // ваш код выдачи всех узлов дерева в определённом порядке
        if (Root == null) return null;
        List<SimpleTreeNode<T>> allNodesList = new ArrayList<>();
        getChildren(Root, allNodesList);
        return allNodesList;
    }

    private void getChildren(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> nodesList) {
        nodesList.add(node);
        if (node.Children == null) return;
        for (SimpleTreeNode<T> child: node.Children) {
            getChildren(child, nodesList);
        }
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val)
    {
        // ваш код поиска узлов по значению
        //TODO тут не понятно, что ожидается при неудаче - пока возвращает пустой список. Возможно нужен null
        if (Root == null) return null;
        List<SimpleTreeNode<T>> nodesFound = new ArrayList<>();
        findInChildren(Root, nodesFound, val);
        return nodesFound;
    }

    private void findInChildren(SimpleTreeNode<T> node, List<SimpleTreeNode<T>> foundNodes, T val) {
        if (node.NodeValue == val) foundNodes.add(node);
        if (node.Children == null) return;
        for (SimpleTreeNode<T> child: node.Children) {
            findInChildren(child, foundNodes, val);
        }
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent)
    {
        // ваш код перемещения узла вместе с его поддеревом --
        // в качестве дочернего для узла NewParent
        DeleteNode(OriginalNode);
        AddChild(NewParent, OriginalNode);
    }

    public int Count()
    {
        // количество всех узлов в дереве
        return leafOrNodeCount(Root, 1);
    }

    public int LeafCount()
    {
        // количество листьев в дереве
        return leafOrNodeCount(Root, 0);
    }

    private int leafOrNodeCount(SimpleTreeNode<T> node, int startCountFrom) {
        if (node == null) return 0;
        if (node.Children == null) return 1;
        int cnt = startCountFrom;
        for (int i = 0; i < node.Children.size(); i++) {
            cnt += leafOrNodeCount(node.Children.get(i), startCountFrom);
        }
        return cnt;
    }

    //TODO Дополнительные задания

    public void setLevels() {
        if (this.Root == null) return;
        setChildrenLevels(this.Root, 0);
    }

    //решение O(n) - time, пространственная сложность по стеку вызовов: О(h) - высота дерева (в худшем случае O(n))
    private void setChildrenLevels(SimpleTreeNode<T> node, int curLevel) {
        node.level = curLevel;
        if (node.Children == null) return;
        for (int i = 0; i < node.Children.size(); i++) {
            setChildrenLevels(node.Children.get(i), curLevel + 1);
        }
    }

    //Поддержка уровней без просмотра всего дерева:
    //0. поле level в классе
    //1. Метод AddChild: новому узлу прописывается level+1
    //2. Метод MoveNode: ну тут рекурсивно обновить level у всего переносимого поддерева согласно mewParent.level + 1
}



