package org.skillsmart.asd2real.lesson8;


public class DirectedGraph {
    Vertex [] vertex;
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
            if (color[i] == 0 && dfs(color, i)) return true;
        }
        return false;
    }

    private boolean dfs(int[] color, int v) {
        if (color[v] == 2) return false;
        if (color[v] == 1) return true;
        color[v] = 1;
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[v][i] == 1 && dfs(color, i)) return true;
        }
        color[v] = 2;
        return false;
    }

}
