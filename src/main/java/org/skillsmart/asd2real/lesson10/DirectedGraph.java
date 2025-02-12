package org.skillsmart.asd2real.lesson10;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DirectedGraph {
    Vertex[] vertex;
    int [][] m_adjacency;
    int max_vertex;

    public DirectedGraph(int size) {
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
    }

    public void RemoveEdge(int v1, int v2)
    {
        m_adjacency[v1][v2] = 0;
    }

    //сложность - time: O(V + E) где V - количество вершин, Е - количество ребер; память O(n)
    public boolean hasCycles() {
        //0 - white, 1 - grey, 2 - black
        int[] color = new int[max_vertex];
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

    //Сложность: time - O(V*E), где V - количество вершин, Е - количество ребер; память O(n)
    public ArrayList<Vertex> getLongestPath() {
        int vCnt = 0;
        for (Vertex value : vertex) {
            if (value != null) vCnt++;
        }
        if (vCnt == 0) return new ArrayList<>();
        markVertexUnHit();
        Map<Integer, ArrayList<Vertex>> paths = new HashMap<>();
        int[] maxLength = {0};
        for (int i = 0; i < vertex.length; i++) {
            Stack<Vertex> pathFromV = new Stack<>();
            markVertexUnHit();
            if (vertex[i] != null) dfsFromVertex(i, pathFromV, paths, maxLength);
        }
        return paths.get(maxLength[0]);
    }

    private void dfsFromVertex(Integer vInd, Stack<Vertex> savedPath, Map<Integer, ArrayList<Vertex>> paths, int [] maxLength) {
        if (vertex[vInd] == null || vertex[vInd].Hit) return;
        vertex[vInd].Hit = true;
        savedPath.push(vertex[vInd]);
        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[vInd][i] == 1 && !vertex[i].Hit) dfsFromVertex(i, savedPath, paths, maxLength);
        }
        if (savedPath.size() > maxLength[0]) {
            maxLength[0] = savedPath.size();
            paths.put(maxLength[0], new ArrayList<>(savedPath));
        }
        savedPath.pop();
    }

    private void markVertexUnHit() {
        for (Vertex value : vertex) {
            if (value != null) {
                value.Hit = false;
            }
        }
    }

}
