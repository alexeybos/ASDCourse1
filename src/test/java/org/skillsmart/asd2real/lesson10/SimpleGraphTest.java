package org.skillsmart.asd2real.lesson10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    @Test
    void testDepthFirstSearch_EmptyAndSmallGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        assertEquals(0, graph.DepthFirstSearch(0, 2).size());
        graph.AddVertex(10);
        assertEquals(0, graph.DepthFirstSearch(0, 2).size());
        assertEquals(0, graph.DepthFirstSearch(2, 0).size());
        graph.AddVertex(11);
        graph.AddVertex(12);
        assertEquals(0, graph.DepthFirstSearch(0, 2).size());
        assertEquals(0, graph.DepthFirstSearch(0, 1).size());
        graph.AddEdge(0, 1);
        assertEquals(0, graph.DepthFirstSearch(0, 2).size());
        ArrayList<Vertex> res = graph.DepthFirstSearch(0, 1);
        assertEquals(2, res.size());
        assertEquals(10, res.getFirst().Value);
        assertEquals(11, res.get(1).Value);
        graph.AddEdge(1, 2);
        res = graph.DepthFirstSearch(0, 2);
        assertEquals(3, res.size());
        assertEquals(10, res.getFirst().Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
    }

    @Test
    void testDepthFirstSearch_BigGraph() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 4);
        assertEquals(0, graph.DepthFirstSearch(0, 3).size());
        assertEquals(0, graph.DepthFirstSearch(2, 3).size());
        ArrayList<Vertex> res = graph.DepthFirstSearch(0, 4);
        assertEquals(4, res.size());
        assertEquals(10, res.getFirst().Value);
        assertEquals(11, res.get(1).Value);
        assertEquals(12, res.get(2).Value);
        assertEquals(14, res.get(3).Value);
    }

    @Test
    void testDepthFirstSearch_WithReturn() {
        SimpleGraph graph = new SimpleGraph(7);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddVertex(15);
        graph.AddVertex(16);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(5, 6);
        assertEquals(0, graph.DepthFirstSearch(0, 5).size());
        assertEquals(0, graph.DepthFirstSearch(2, 5).size());
        ArrayList<Vertex> res = graph.DepthFirstSearch(0, 4);
        assertEquals(3, res.size());
        assertEquals(10, res.getFirst().Value);
        assertEquals(13, res.get(1).Value);
        assertEquals(14, res.get(2).Value);
        res = graph.DepthFirstSearch(4, 0);
        assertEquals(3, res.size());
        res = graph.DepthFirstSearch(5, 6);
        assertEquals(2, res.size());
        assertEquals(15, res.getFirst().Value);
        assertEquals(16, res.get(1).Value);
    }

    @Test
    void testIsConnected_EmptyGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        assertFalse(graph.isConnected());
    }

    @Test
    void testIsConnected() {
        SimpleGraph graph = new SimpleGraph(7);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddVertex(15);
        graph.AddVertex(16);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(5, 6);
        assertFalse(graph.isConnected());
        graph.AddEdge(2, 5);
        assertTrue(graph.isConnected());
    }
}