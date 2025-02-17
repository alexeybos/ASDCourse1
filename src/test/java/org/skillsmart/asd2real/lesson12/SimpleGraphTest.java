package org.skillsmart.asd2real.lesson12;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    @Test
    void breadthFirstSearch_EmptyAndSmallGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        assertEquals(0, graph.BreadthFirstSearch(1, 2).size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        assertEquals(0, graph.BreadthFirstSearch(1, 2).size());
        graph.AddVertex(12);
        assertEquals(0, graph.BreadthFirstSearch(0, 2).size());
        graph.AddEdge(0, 1);
        assertEquals(0, graph.BreadthFirstSearch(0, 2).size());
        graph.AddEdge(1, 2);
        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 2);
        assertEquals(3, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        graph.AddEdge(0, 2);
        res = graph.BreadthFirstSearch(0, 2);
        assertEquals(2, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(12, res.get(1).Value);
    }

    @Test
    void breadthFirstSearch_BigGraph() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(0, 3);
        ArrayList<Vertex> res = graph.BreadthFirstSearch(0, 4);
        assertEquals(3, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(13, res.get(1).Value);
        assertEquals(14, res.get(2).Value);
    }

    @Test
    void testWeakVertices_EmptyGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        ArrayList<Vertex> res = graph.WeakVertices();
        assertTrue(res.isEmpty());
    }

    @Test
    void testWeakVertices_SimpleGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        ArrayList<Vertex> res = graph.WeakVertices();
        assertTrue(res.isEmpty());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        res = graph.WeakVertices();
        assertEquals(3, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 0);
        res = graph.WeakVertices();
        assertTrue(res.isEmpty());
    }

    @Test
    void testWeakVertices() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        ArrayList<Vertex> res = graph.WeakVertices();
        assertEquals(5, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(13, res.get(3).Value);
        assertEquals(14, res.get(4).Value);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(0, 3);
        res = graph.WeakVertices();
        assertEquals(5, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(13, res.get(3).Value);
        assertEquals(14, res.get(4).Value);
        graph.AddEdge(0, 2);
        res = graph.WeakVertices();
        assertEquals(1, res.size());
        assertEquals(14, res.getFirst().Value);
    }

    @Test
    void testGetTrianglesCnt_EmptyGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        assertEquals(0, graph.getTrianglesCnt());
    }

    @Test
    void testGetTrianglesCnt_SmallGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        assertEquals(0, graph.getTrianglesCnt());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        assertEquals(0, graph.getTrianglesCnt());
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        assertEquals(0, graph.getTrianglesCnt());
        graph.AddEdge(2, 0);
        assertEquals(1, graph.getTrianglesCnt());
    }

    @Test
    void testGetTrianglesCnt_BigGraph() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 3);
        graph.AddEdge(3, 4);
        assertEquals(3, graph.getTrianglesCnt());
    }

    @Test
    void testWeakVerticesByPublic_EmptyGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        ArrayList<Vertex> res = graph.WeakVerticesByPublic();
        assertTrue(res.isEmpty());
    }

    @Test
    void testWeakVerticesByPublic_SimpleGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        ArrayList<Vertex> res = graph.WeakVerticesByPublic();
        assertTrue(res.isEmpty());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        res = graph.WeakVerticesByPublic();
        assertEquals(3, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 0);
        res = graph.WeakVerticesByPublic();
        assertTrue(res.isEmpty());
    }

    @Test
    void testWeakVerticesByPublic() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        ArrayList<Vertex> res = graph.WeakVerticesByPublic();
        assertEquals(5, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(13, res.get(3).Value);
        assertEquals(14, res.get(4).Value);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(0, 3);
        res = graph.WeakVerticesByPublic();
        assertEquals(5, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(13, res.get(3).Value);
        assertEquals(14, res.get(4).Value);
        graph.AddEdge(0, 2);
        res = graph.WeakVerticesByPublic();
        assertEquals(1, res.size());
        assertEquals(14, res.getFirst().Value);
    }

    @Test
    void testWeakVerticesByMatrix_Analyze_EmptyGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        ArrayList<Vertex> res = graph.WeakVerticesByMatrixAnalyze();
        assertTrue(res.isEmpty());
    }

    @Test
    void testWeakVerticesByMatrix_Analyze_SimpleGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        ArrayList<Vertex> res = graph.WeakVerticesByMatrixAnalyze();
        assertTrue(res.isEmpty());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        res = graph.WeakVerticesByMatrixAnalyze();
        assertEquals(3, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 0);
        res = graph.WeakVerticesByMatrixAnalyze();
        assertTrue(res.isEmpty());
    }

    @Test
    void testWeakVerticesByMatrixAnalyze() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        ArrayList<Vertex> res = graph.WeakVerticesByMatrixAnalyze();
        assertEquals(5, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(13, res.get(3).Value);
        assertEquals(14, res.get(4).Value);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(0, 3);
        res = graph.WeakVerticesByMatrixAnalyze();
        assertEquals(5, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(13, res.get(3).Value);
        assertEquals(14, res.get(4).Value);
        graph.AddEdge(0, 2);
        res = graph.WeakVerticesByMatrixAnalyze();
        assertEquals(1, res.size());
        assertEquals(14, res.getFirst().Value);
    }
}