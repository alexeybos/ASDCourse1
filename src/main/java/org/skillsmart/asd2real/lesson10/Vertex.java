package org.skillsmart.asd2real.lesson10;

import java.util.*;
import java.util.function.Function;

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
        Function<Integer, Boolean> checkVTo = (index) -> {
            if (m_adjacency[index][VTo] == 1) {
                path.push(vertex[VTo]);
                return true;
            }
            return false;
        };
        dfsFromVertex(VFrom, path, checkVTo);
        return new ArrayList<>(path);
    }

    private boolean dfsFromVertex(Integer vInd, Stack<Vertex> savedPath, Function<Integer, Boolean> processNodeAndExit) {
        if (vertex[vInd] == null || vertex[vInd].Hit) return false;
        vertex[vInd].Hit = true;
        if (savedPath != null) savedPath.push(vertex[vInd]);
        if (processNodeAndExit.apply(vInd)) return true;
        for (int i = 0; i < vertex.length; i++) {
            boolean endProcess = false;
            if (m_adjacency[vInd][i] == 1 && !vertex[i].Hit) endProcess = dfsFromVertex(i, savedPath, processNodeAndExit);
            if (endProcess) return true;
        }
        if (savedPath != null) savedPath.pop();
        return false;
    }

    //Сложность: time - O(n), память O(1)
    public boolean isConnected() {
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0) return false;
        Function<Integer, Boolean> noop = (i) -> false;
        dfsFromVertex(0, null, noop);
        for (Vertex value : vertex) {
            if (value != null && !value.Hit) return false;
        }
        return true;
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
}
