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
        int[] pathByParents = bfsFromVertex(VFrom, VTo);
        return makeResultPath(pathByParents, VTo);
    }

    private int[] bfsFromVertex(Integer VFrom, int VTo) {
        Queue<Integer> vertexesQ = new LinkedList<>();
        int[] lastParents = new int[max_vertex];
        Arrays.fill(lastParents, -1);
        vertexesQ.add(VFrom);
        for (; !vertexesQ.isEmpty();) {
            int vInd = vertexesQ.remove();
            if (m_adjacency[vInd][VTo] == 1) {
                lastParents[VTo] = vInd;
                return lastParents;
            }
            vertex[vInd].Hit = true;
            for (int i = 0; i < vertex.length; i++) {
                if (m_adjacency[vInd][i] == 1 && !vertex[i].Hit) {
                    vertex[i].Hit = true;
                    vertexesQ.add(i);
                    lastParents[i] = vInd;
                }
            }
        }
        return null;
    }

    private ArrayList<Vertex> makeResultPath(int[] pathByParents, int VTo) {
        ArrayList<Vertex> result = new ArrayList<>();
        if (pathByParents == null) return result;
        result.add(vertex[VTo]);
        for (int i = VTo; pathByParents[i] != -1; i = pathByParents[i]) {
            result.add(vertex[pathByParents[i]]);
        }
        return new ArrayList<>(result.reversed());
    }

    //TODO additional tasks

    // Возвращает массив: индекс начального узла; индекс конечного узла; расстояние (количество ребер) между ними
    //Сложность: time - O(n^2); память - O(n)
    public ArrayList<Integer> getMostDistantVertexAndDistance() {
        ArrayList<Integer> result = new ArrayList<>(Arrays.asList(0, 0, 0));
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0 || vertex[0] == null) return new ArrayList<>();
        int[] farVertex = {0};
        int[] remoteness = {0};
        BiFunction<Integer, Integer,  Boolean> count = (i, level) -> {
            farVertex[0] = i;
            remoteness[0] = level;
            return false;
        };
        //находим наиболее удаленный от вершины [0]
        BFS(0, count);
        result.set(0, farVertex[0]);
        //находим наиболее удаленный от найденного
        markVertexUnHitAndCount();
        BFS(farVertex[0], count);
        result.set(1, farVertex[0]);
        result.set(2, remoteness[0]);

        return result;
    }

    private int[] BFS(int VFrom, BiFunction<Integer, Integer, Boolean> processVertexAndExit) {
        Queue<Integer> vertexesQ = new LinkedList<>();
        int[] lastParents = new int[max_vertex];
        Arrays.fill(lastParents, -1);
        vertexesQ.add(VFrom);
        int level = 0;
        for (; !vertexesQ.isEmpty();) {
            int levelSize = vertexesQ.size();
            for (int l = 0; l < levelSize; l++) {
                int vInd = vertexesQ.remove();
                boolean terminate = processVertexAndExit.apply(vInd, level);
                if (terminate) return lastParents;
                vertex[vInd].Hit = true;
                for (int i = 0; i < vertex.length; i++) {
                    if (m_adjacency[vInd][i] == 1 && !vertex[i].Hit) {
                        vertex[i].Hit = true;
                        vertexesQ.add(i);
                        lastParents[i] = vInd;
                    }
                }
            }
            level++;
        }
        return lastParents;
    }

    //Сложность: time - O(n^2) ; память - O(n)
    public ArrayList<ArrayList<Integer>> getCycles() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0) return result;
        Map<ArrayList<Integer>, ArrayList<Integer>> resultArrOfCycles = new HashMap<>();
        for (int i = 0; i < max_vertex; i++) {
            getCyclesFromVertex(i, resultArrOfCycles);
        }
        return new ArrayList<>(resultArrOfCycles.values());
    }

    private void getCyclesFromVertex(int VFrom, Map<ArrayList<Integer>, ArrayList<Integer>> resultArrOfCycles) {
        Queue<Integer> vertexesQ = new LinkedList<>();
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0) return;
        int[] lastParents = new int[max_vertex];
        Arrays.fill(lastParents, -1);

        vertexesQ.add(VFrom);
        vertex[VFrom].Hit = true;
        for (; !vertexesQ.isEmpty();) {
            int vInd = vertexesQ.remove();
            for (int i = 0; i < vertex.length; i++) {
                if (m_adjacency[vInd][i] == 1 && vertex[i] != null && vertex[i].Hit && lastParents[vInd] != i) {
                    //имеем цикл++
                    ArrayList<Integer> route = makeResultPathInCycle(lastParents, vInd, i);
                    addTraceToCycles(resultArrOfCycles, route);
                }
                if (m_adjacency[vInd][i] == 1 && vertex[i] != null && !vertex[i].Hit) {
                    vertex[i].Hit = true;
                    vertexesQ.add(i);
                    lastParents[i] = vInd;
                }
            }
        }
    }

    private ArrayList<Integer> makeResultPathInCycle(int[] pathByParents, int VLast, int vEndCycle) {
        ArrayList<Integer> pathFromEndV = new ArrayList<>();
        if (pathByParents == null) return pathFromEndV;
        if (VLast == vEndCycle) return new ArrayList<>(Arrays.asList(VLast, vEndCycle));

        pathFromEndV.add(VLast);
        for (int i = VLast; pathByParents[i] != -1; i = pathByParents[i]) {
            pathFromEndV.add(pathByParents[i]);
            if (pathByParents[vEndCycle] == pathByParents[i]) {
                ArrayList<Integer> result = new ArrayList<>(pathFromEndV.reversed());
                result.add(vEndCycle);
                return result;
            }
        }
        ArrayList<Integer> pathFromEndCycleV = new ArrayList<>();
        pathFromEndCycleV.add(vEndCycle);
        for (int i = vEndCycle; pathByParents[i] != -1 && pathByParents[i] != pathFromEndV.getLast(); i = pathByParents[i]) {
            pathFromEndCycleV.add(pathByParents[i]);
        }
        ArrayList<Integer> result = new ArrayList<>(pathFromEndV.reversed());
        result.addAll(pathFromEndCycleV);
        return result;
    }

    private void addTraceToCycles(Map<ArrayList<Integer>, ArrayList<Integer>> resultArrOfCycles, ArrayList<Integer> cycleToAdd) {
        ArrayList<Integer> nativeList = new ArrayList<>(cycleToAdd);
        Collections.sort(cycleToAdd);
        resultArrOfCycles.put(cycleToAdd, nativeList);
    }
}