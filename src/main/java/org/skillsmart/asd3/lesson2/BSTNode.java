package org.skillsmart.asd3.lesson2;


class BSTNode<T>
{
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent)
    {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T>
{
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() { Node = null; }

    public BSTFind(BSTNode<T> node, boolean nodeHasKey, boolean toLeft) {
        Node = node;
        NodeHasKey = nodeHasKey;
        ToLeft = toLeft;
    }
}

class BST<T>
{
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node)
    {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key)
    {
        // ищем в дереве узел и сопутствующую информацию по ключу
        if (Root == null) return new BSTFind<>(null, false, false);
        return FindNodeByKey(key, Root, Root, false);
    }

    private BSTFind<T> FindNodeByKey(int key, BSTNode<T> node) {
        if (node == null) return null;
        BSTFind<T> foundNode;
        if (node.NodeKey == key) {
            return new BSTFind<>(node, true, false); //закончили поиск
        }
        if (node.NodeKey > key) {
            foundNode = FindNodeByKey(key, node.LeftChild);
            if (foundNode == null) {
                return new BSTFind<T>(node, false, true);
            }
        } else {
            foundNode = FindNodeByKey(key, node.RightChild);
            if (foundNode == null) {
                return new BSTFind<T>(node, false, false);
            }
        }
        return foundNode;
    }

    private BSTFind<T> FindNodeByKey(int key, BSTNode<T> node, BSTNode<T> prevNode, boolean toLeft) {
        if (node == null) return new BSTFind<>(prevNode, false, toLeft); //закончили поиск;
        BSTFind<T> foundNode;
        if (node.NodeKey == key) {
            return new BSTFind<>(node, true, false); //закончили поиск
        }
        if (node.NodeKey > key) {
            foundNode = FindNodeByKey(key, node.LeftChild, node, true);
        } else {
            foundNode = FindNodeByKey(key, node.RightChild, node, false);
        }
        return foundNode;
    }

    public boolean AddKeyValue(int key, T val)
    {
        // добавляем ключ-значение в дерево
        BSTFind<T> nodeToAdd = FindNodeByKey(key);
        if (nodeToAdd.NodeHasKey) return false; // если ключ уже есть
        if (nodeToAdd.ToLeft) {
            nodeToAdd.Node.LeftChild = new BSTNode<>(key, val, nodeToAdd.Node);
        } else {
            nodeToAdd.Node.RightChild = new BSTNode<>(key, val, nodeToAdd.Node);
        }
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax)
    {
        // ищем максимальный/минимальный ключ в поддереве
        if (Root == null) return null;
        if (FindMax) return findMax(FromNode);
        return findMin(FromNode);
    }

    private BSTNode<T> findMax(BSTNode<T> node) {
        if (node.RightChild == null) return node;
        return findMax(node.RightChild);
    }

    private BSTNode<T> findMin(BSTNode<T> node) {
        if (node.LeftChild == null) return node;
        return findMin(node.LeftChild);
    }

    public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> foundNode = FindNodeByKey(key); // удаляем узел по ключу
        if (!foundNode.NodeHasKey) return false;
        BSTNode<T> node = foundNode.Node;
        if (node == Root) {
            Root = null;
            return true;
        }
        //удаляем лист. только обнуление у родителя _определяем_ какого потомка
        if (node.RightChild == null && node.LeftChild == null) {
            replaceLinkInParent(node, null);
            return true;
        }
        //есть только левый (меняем на него). У родителя _определяем_ потомка, меняем ссылку на тек.левый
        //(2) у тек.левый меняяем родителя с тек на тек.родитель
        if (node.RightChild == null) {
            node.LeftChild.Parent = node.Parent;
            replaceLinkInParent(node, node.LeftChild);
            return true;
        }
        //только правый (меняем на него). У родителя _определяем_ потомка, меняем ссылку на тек.правый
        //        //(2) у тек.правый меняяем родителя с тек на тек.родитель
        if (node.LeftChild == null) {
            node.RightChild.Parent = node.Parent;
            replaceLinkInParent(node, node.RightChild);
            return true;
        }
        //есть оба
        BSTNode<T> deepNode = node.RightChild;
        /*while (deepNode.LeftChild != null) {
            deepNode = deepNode.LeftChild;
        }*/
        for (; deepNode.LeftChild != null;) {
            deepNode = deepNode.LeftChild;
        }
        //есть правый потомок
        //меняем тек на найд и найд.прав делаем вместо найд
        BSTNode<T> tempNode = new BSTNode<>(deepNode.NodeKey, deepNode.NodeValue, deepNode.Parent);
        if (deepNode.RightChild != null) {
            node.NodeValue = deepNode.NodeValue;
            node.NodeKey = deepNode.NodeKey;
            deepNode.NodeKey = deepNode.RightChild.NodeKey;
            deepNode.NodeValue = deepNode.RightChild.NodeValue;
            deepNode.RightChild = null;
            return true;
        }
        //имеем лист. Меняем текущий на найденный лист: у тек.род _определяем_кем был потомок и меняем ссылку на найденный лист

        /*TODO А вот так выглядит честная замена удаляемого узла на лист! Надо обновить 7 ссылок
          TODO (3 на самом узле, 3 внешние ссылки на бывший узел) + ссылка от нового Parent на сам узел
        replaceLinkInParent(node, deepNode);
        replaceLinkInParent(deepNode, null);
        deepNode.Parent = node.Parent;
        deepNode.RightChild = node.RightChild;
        deepNode.LeftChild = node.LeftChild;
        node.LeftChild.Parent = deepNode;
        node.RightChild.Parent = deepNode;*/

        node.NodeValue = deepNode.NodeValue;
        node.NodeKey = deepNode.NodeKey;
        replaceLinkInParent(deepNode, null);
        return true;
    }

    private void replaceLinkInParent(BSTNode<T> child, BSTNode<T> newChild) {
        if (child.Parent.LeftChild == child) {
            child.Parent.LeftChild = newChild;
            return;
        }
        child.Parent.RightChild = newChild;
    }

    /*public boolean DeleteNodeByKey(int key)
    {
        BSTFind<T> foundNode = FindNodeByKey(key); // удаляем узел по ключу
        if (!foundNode.NodeHasKey) return false;
        BSTNode<T> node = foundNode.Node;
        if (node == Root) {
            Root = null;
            return true;
        }
        //алгоритм замены
        //определяем, какой он потомок (левый или правый)
        //у родителя меняем соответствующую ссылку на новый
        //у нового меняем ссылку на родителя текущего
        //у нового, если он был левым потомком, то правый потомок ставим правого текущего
        //у нового, если он был правым потомком, то левый потомок ставим левого текущего

        //удаляем лист. только обнуление у родителя _определяем_ какого потомка
        if (node.RightChild == null && node.LeftChild == null) {
            if (node.Parent.LeftChild == node) {
                node.Parent.LeftChild = null;
                return true;
            }
            node.Parent.RightChild = null;
            return true;
        }
        //есть только левый (меняем на него). У родителя _определяем_ потомка, меняем ссылку на тек.левый
        //(2) у тек.левый меняяем родителя с тек на тек.родитель
        if (node.RightChild == null) {
            node.LeftChild.Parent = node.Parent;
            if (node.Parent.LeftChild == node) {
                node.Parent.LeftChild = null;
                return true;
            }
            //reassignNode(node, node.LeftChild);
            node.Parent.RightChild = null;
            return true;
        }
        //только правый (меняем на него). У родителя _определяем_ потомка, меняем ссылку на тек.правый
        //        //(2) у тек.правый меняяем родителя с тек на тек.родитель
        if (node.LeftChild == null) {
            node.RightChild.Parent = node.Parent;
            if (node.Parent.LeftChild == node) {
                node.Parent.LeftChild = null;
                return true;
            }
            node.Parent.RightChild = null;
            //reassignNode(node, node.RightChild);
            return true;
        }

        //есть оба
        BSTNode<T> deepNode = node.RightChild;
        while (deepNode.LeftChild != null) {
            deepNode = deepNode.LeftChild;
        }
        //есть правый потомок
        //меняем тек на найд и найд.прав делаем вместо найд
        //


        if (deepNode.RightChild != null) {
            if (node.Parent.LeftChild == node) {
                node.Parent.LeftChild = deepNode;
            } else node.Parent.RightChild = deepNode;
            deepNode.LeftChild = node.LeftChild;
            deepNode.RightChild = node.RightChild;
            deepNode.Parent.LeftChild = deepNode.RightChild;
            deepNode.RightChild.Parent = deepNode.Parent;
            deepNode.Parent = node.Parent;
            return true;
        }
        //имеем лист. Меняем текущий на найденный лист: у тек.род _определяем_кем был потомок и меняем ссылку на найденный лист
        //(2) у найдлист.род ставим тек.род и
        // у найдлист.левый ставим тек.левый
        // у найдлист.правый ставим тек.правый
        // у найдлист.род.левый ставим null
        // у тек.левый.род и у тек.правый.род ставим найдлист
        //reassignNode(node, deepNode);
        if (node.Parent.LeftChild == node) {
            node.Parent.LeftChild = deepNode;
        } else node.Parent.RightChild = deepNode;
        if (deepNode != node.RightChild) deepNode.RightChild = node.RightChild;
        deepNode.LeftChild = node.LeftChild;
        deepNode.Parent.LeftChild = null;
        deepNode.Parent = node.Parent;
        node.RightChild.Parent = deepNode;
        node.LeftChild.Parent = deepNode;

        return true;
    }*/

    private void reassignNode(BSTNode<T> oldNode, BSTNode<T> newNode) {
        if (oldNode.Parent.NodeKey > newNode.NodeKey) {
            oldNode.Parent.LeftChild = newNode;
            oldNode.Parent = null;
            if (oldNode.LeftChild == newNode) {
                newNode.RightChild = oldNode.RightChild;
            } else {
                newNode.LeftChild = oldNode.LeftChild;
            }
            return;
        }
        oldNode.Parent.RightChild = newNode;
        oldNode.Parent = null;
        if (oldNode.LeftChild == newNode) {
            newNode.RightChild = oldNode.RightChild;
        } else {
            newNode.LeftChild = oldNode.LeftChild;
        }
    }

    public int Count()
    {
        return childrenCount(Root); // количество узлов в дереве
    }

    private int childrenCount(BSTNode<T> node) {
        if (node == null) return 0;
        return 1 + childrenCount(node.LeftChild) + childrenCount(node.RightChild);
    }

    //TODO доп. задания

    public boolean isEqual(BST<T> tree) {
        if (Count() != tree.Count()) return false;// но тут уже перебор всего дерева. не уверен, что облегчает в принципе
        //далее идем параллельно
        return isNodeEqual(Root, tree.Root);
        //if (isValuesEqual(Root, tree.Root))
    }

    private boolean isNodeEqual(BSTNode<T> tree1, BSTNode<T> tree2) {
        if (tree1 == null && tree2 == null) return true;
        if (!isValuesEqual(tree1, tree2)) return false;
        boolean equality = isNodeEqual(tree1.LeftChild, tree2.LeftChild);
        return equality && isNodeEqual(tree1.RightChild, tree2.RightChild);
    }

    private boolean isValuesEqual(BSTNode<T> firstNode, BSTNode<T> secondNode) {
        if (firstNode == null || secondNode == null) return false;
        return firstNode.NodeKey == secondNode.NodeKey && firstNode.NodeValue == secondNode.NodeValue;
    }
}


