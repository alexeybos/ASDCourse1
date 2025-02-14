package org.skillsmart.asd2real.lesson11;

import java.util.*;
import java.util.function.BiFunction;

class Vertex
{
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph
{
    Vertex [] vertex;
    int [][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size)
    {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value)
    {
        int emptySlot = 0;
        for (; vertex[emptySlot] != null; emptySlot++) {
        }
        vertex[emptySlot] = new Vertex(value);
    }

    public void RemoveVertex(int v)
    {
        vertex[v] = null;
        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2)
    {
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
    {
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0 || vertex[VFrom] == null || vertex[VTo] == null) return new ArrayList<>();

        Stack<Vertex> path = new Stack<>();
        dfsFromVertex(VFrom, path, VTo);
        return new ArrayList<>(path);
    }

    private boolean dfsFromVertex(Integer vInd, Stack<Vertex> savedPath, int VTo) {
        if (vertex[vInd] == null || vertex[vInd].Hit) return false;
        vertex[vInd].Hit = true;
        savedPath.push(vertex[vInd]);
        if (m_adjacency[vInd][VTo] == 1) {
            savedPath.push(vertex[VTo]);
            return true;
        }
        for (int i = 0; i < vertex.length; i++) {
            boolean endProcess = false;
            if (m_adjacency[vInd][i] == 1 && !vertex[i].Hit) endProcess = dfsFromVertex(i, savedPath, VTo);
            if (endProcess) return true;
        }
        savedPath.pop();
        return false;
    }

    private int markVertexUnHitAndCount() {
        int vCnt = 0;
        for (Vertex value : vertex) {
            if (value != null) {
                vCnt++;
                value.Hit = false;
            }
        }
        return vCnt;
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo)
    {
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0 || vertex[VFrom] == null || vertex[VTo] == null) return new ArrayList<>();
        Map<Integer, Vertex> path = bfsFromVertex(VFrom, VTo);
        ArrayList<Vertex> result = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            result.add(path.get(i));
        }
        return result;
    }

    private Map<Integer, Vertex> bfsFromVertex(Integer VFrom, int VTo) {
        Queue<Integer> vertexesQ = new LinkedList<>();
        Map<Integer, Vertex> route = new HashMap<>();
        int[] lastParents = new int[max_vertex];
        vertexesQ.add(VFrom);
        int level = 0;
        for (; !vertexesQ.isEmpty();) {
            int levelSize = vertexesQ.size();
            for (int l = 0; l < levelSize; l++) {
                int vInd = vertexesQ.remove();
                route.put(level, vertex[vInd]);
                lastParents[]
                if (m_adjacency[vInd][VTo] == 1) {
                    route.put(level + 1, vertex[VTo]);
                    return route;
                }
                vertex[vInd].Hit = true;
                for (int i = 0; i < vertex.length; i++) {
                    if (m_adjacency[vInd][i] == 1 && !vertex[i].Hit) {
                        vertex[i].Hit = true;
                        vertexesQ.add(i);
                    }
                }
            }
            level++;
        }
        return new HashMap<>();
    }

    //TODO additional tasks

    // Возвращает массив: индекс начального узла; индекс конечного узла; расстояние (количество ребер) между ними
    //Сложность: time - O(n); память - O(n)
    public ArrayList<Integer> getMostDistantVertexAndDistance() {
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 0, 0));
        //здесь считаем, что корень дерева лежит в vertex[0], но в принципе ничего не мешает работать с любой вершиной как корнем
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0 || vertex[0] == null) return new ArrayList<>();
        BiFunction<Integer, Integer,  Boolean> count = (i, r) -> {
            result.set(1, i);
            result.set(2, r);
            return false;
        };
        BFS(0, count);
        return result;
    }

    private void BFS(int VFrom, BiFunction<Integer, Integer, Boolean> processVertexAndExit) {
        Queue<Integer> vertexesQ = new LinkedList<>();
        vertexesQ.add(VFrom);
        int remoteness = 0;
        for (; !vertexesQ.isEmpty();) {
            int levelSize = vertexesQ.size();
            for (int l = 0; l < levelSize; l++) {
                int vInd = vertexesQ.remove();
                boolean terminate = processVertexAndExit.apply(vInd, remoteness);
                if (terminate) return;
                vertex[vInd].Hit = true;
                for (int i = 0; i < vertex.length; i++) {
                    if (m_adjacency[vInd][i] == 1 && !vertex[i].Hit) {
                        vertex[i].Hit = true;
                        vertexesQ.add(i);
                    }
                }
            }
            remoteness++;
        }
    }

    public ArrayList<ArrayList<Integer>> getCyclesByBiFunc() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0) return result;
        int[] color = new int[max_vertex];
        int currentRemoteness = 0;
        BiFunction<Integer, Integer, Boolean> checkCycle = (i, r) -> {

            return false;
        };

        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] != null && color[i] == 0) {
                BFS(i, checkCycle);
            }
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> getCycles() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0) return result;
        int[] VFrom = {0};
        Map<Integer, Integer> route = new HashMap<>();
        BiFunction<Integer, Integer, Boolean> checkCycle = (i, r) -> {
            route.put(r, i);
            if (m_adjacency[i][VFrom[0]] == 0) return false;
            if (route.get(r-1) == VFrom[0]) return false;
            ArrayList<Integer> fullPath = new ArrayList<>();
            for (int j = 0; j < route.size(); j++) {
                fullPath.add(route.get(j));
            }
            result.add(fullPath);
            return false;
        };
        for (int i = 0; i < vertex.length; i++) {
            markVertexUnHitAndCount();
            VFrom[0] = i;
            BFS(i, checkCycle);
        }
        return result;
    }

    public boolean hasCycles() {
        //0 - white, 1 - grey, 2 - black
        int[] color = new int[max_vertex];
        ArrayList<Integer> cycle = new ArrayList<>();
        Queue<Integer> vertexesQ = new LinkedList<>();
        vertexesQ.add(0);
        for (; !vertexesQ.isEmpty();) {
            int vInd = vertexesQ.remove();
            vertex[vInd].Hit = true;
            color[vInd] = 1;
            for (int i = 0; i < vertex.length; i++) {
                if (m_adjacency[vInd][i] == 1 && color[i] == 1) {
                    //найден цикл!
                }
                if (m_adjacency[vInd][i] == 1 && color[i] == 0) {
                    color[i] = 1;
                    vertexesQ.add(i);
                }
            }
            color[vInd] = 2;
        }
        for (int i = 0; i < max_vertex; i++) {
            if (color[i] == 0 && hasCycles(color, i)) return true;
        }
        return false;
    }

    private boolean hasCycles(int[] color, int v) {
        if (color[v] == 2) return false;
        if (color[v] == 1) return true;
        color[v] = 1;
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[v][i] == 1 && hasCycles(color, i)) return true;
        }
        color[v] = 2;
        return false;
    }

}


