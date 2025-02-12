package org.skillsmart.asd2real.lesson10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DirectedGraphTest {

    @Test
    void getLongestPath_EmptyAndSmallGraph() {
        DirectedGraph graph = new DirectedGraph(3);
        assertEquals(0, graph.getLongestPath().size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddEdge(0, 1);
        assertEquals(2, graph.getLongestPath().size());
        ArrayList<Vertex> res = graph.getLongestPath();
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 0);
        res = graph.getLongestPath();
        assertEquals(3, res.size());
        assertEquals(11, res.get(0).Value);
        assertEquals(10, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
    }

    @Test
    void getLongestPath_GraphWithCycle() {
        DirectedGraph graph = new DirectedGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(2, 4);
        graph.AddEdge(1, 0);
        graph.AddEdge(2, 0);
        assertTrue(graph.hasCycles());
        ArrayList<Vertex> res = graph.getLongestPath();
        assertEquals(4, res.size());
        assertEquals(10, res.get(0).Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(14, res.get(3).Value);
    }
}