package org.skillsmart.asd2real.lesson12;

import java.util.*;

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

    public ArrayList<Vertex> WeakVertices()
    {
        int vCnt = markVertexUnHitAndCount();
        ArrayList<Vertex> result = new ArrayList<>();
        if (vCnt == 0) return result;
        for (int curVert = 0; curVert < vertex.length; curVert++) {
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < vertex.length; j++) {
                if (m_adjacency[curVert][j] == 1) {
                    neighbors.add(j);
                }
            }
            for (int j = 0; j < neighbors.size() - 1; j++) {
                for (int k = j + 1; k < neighbors.size(); k++) {
                    int n1 = neighbors.get(j);
                    int n2 = neighbors.get(k);
                    if (m_adjacency[n1][n2] == 1) {
                        vertex[curVert].Hit = true;
                        vertex[n1].Hit = true;
                        vertex[n2].Hit = true;
                    }
                }
            }
        }
        for (int i = 0; i < max_vertex; i++) {
            if (!vertex[i].Hit) result.add(vertex[i]);
        }
        return result;
    }

    //Сложность time - O(n^3); память - O(n)
    public int getTrianglesCnt() {
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0) return 0;
        return getTriangles().size() / 3;
    }

    public ArrayList<Vertex> getTriangles() {
        ArrayList<Vertex> triangles = new ArrayList<>();
        for (int curVert = 0; curVert < vertex.length; curVert++) {
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < vertex.length; j++) {
                if (m_adjacency[curVert][j] == 1) {
                    neighbors.add(j);
                }
            }
            for (int j = 0; j < neighbors.size() - 1; j++) {
                for (int k = j + 1; k < neighbors.size(); k++) {
                    int n1 = neighbors.get(j);
                    int n2 = neighbors.get(k);
                    if (m_adjacency[n1][n2] == 1 && (!vertex[curVert].Hit || !vertex[n1].Hit || !vertex[n2].Hit)) {
                        vertex[curVert].Hit = true;
                        vertex[n1].Hit = true;
                        vertex[n2].Hit = true;
                        triangles.addAll(Arrays.asList(vertex[curVert], vertex[n1], vertex[n2]));
                    }
                }
            }
        }
        return triangles;
    }

    public int getSize() {
        return max_vertex;
    }

    public Vertex getVertex(int i) {
        return vertex[i];
    }

    //Сложность time - O(n^3); память - O(n)
    public ArrayList<Vertex> WeakVerticesByPublic() {
        ArrayList<Vertex> triangles = getTriangles();
        ArrayList<Vertex> result = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            Vertex v = getVertex(i);
            //if (v != null && !triangles.contains(v)) result.add(v);
            if (v != null && !triangles.remove(v)) result.add(v);
        }
        return result;
    }

    public ArrayList<Vertex> WeakVerticesByPublic_Old() {
        Set<Vertex> inTriangles = new HashSet<>();
        for (int i = 0; i < getSize() - 1; i++) {
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int j = 0; j < getSize(); j++) {
                boolean hasEdge = IsEdge(i, j);
                if (hasEdge) neighbors.add(j);
            }
            for (int j = 0; j < neighbors.size() - 1; j++) {
                for (int k = j; k < neighbors.size(); k++) {
                    if (IsEdge(neighbors.get(j), neighbors.get(k))) {
                        inTriangles.add(getVertex(i));
                        inTriangles.add(getVertex(neighbors.get(j)));
                        inTriangles.add(getVertex(neighbors.get(k)));
                    }
                }
            }
        }
        ArrayList<Vertex> result = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            Vertex v = getVertex(i);
            if (v != null) result.add(getVertex(i));
        }
        result.removeAll(inTriangles);
        return result;
    }

    //Сложность time - O(n^3); память - O(n)
    public ArrayList<Vertex> WeakVerticesByMatrixAnalyze() {
        int vCnt = markVertexUnHitAndCount();
        if (vCnt == 0) return new ArrayList<>();
        ArrayList<Vertex> result = new ArrayList<>();
        for (int curVert = 0; curVert < vertex.length; curVert++) {
            if (!isVertexInTriangleCheckAndMark(curVert)) result.add(vertex[curVert]);
        }
        return result;
    }

    private boolean isVertexInTriangleCheckAndMark(int vInd) {
        if (vertex[vInd].Hit) return true;
        ArrayList<Integer> neighbors = new ArrayList<>();
        for (int j = 0; j < vertex.length; j++) {
            if (m_adjacency[vInd][j] == 1) {
                neighbors.add(j);
            }
        }
        for (int j = 0; j < neighbors.size() - 1; j++) {
            for (int k = j + 1; k < neighbors.size(); k++) {
                int n1 = neighbors.get(j);
                int n2 = neighbors.get(k);
                if (m_adjacency[n1][n2] == 1) {
                    vertex[vInd].Hit = true;
                    vertex[n1].Hit = true;
                    vertex[n2].Hit = true;
                    return true;
                }
            }
        }
        return false;
    }
}
