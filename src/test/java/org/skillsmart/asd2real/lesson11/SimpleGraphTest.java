package org.skillsmart.asd2real.lesson11;

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
    void getMostDistantVertexAndDistance_EmptyTree() {
        SimpleGraph graph = new SimpleGraph(3);
        assertEquals(0, graph.getMostDistantVertexAndDistance().size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddEdge(0, 1);
        ArrayList<Integer> res = graph.getMostDistantVertexAndDistance();
        assertEquals(3, res.size());
        assertEquals(0, res.get(0));
        assertEquals(1, res.get(1));
        assertEquals(1, res.get(2));
        graph.AddVertex(12);
        graph.AddEdge(1, 2);
        res = graph.getMostDistantVertexAndDistance();
        assertEquals(3, res.size());
        assertEquals(0, res.get(0));
        assertEquals(2, res.get(1));
        assertEquals(2, res.get(2));
    }

    @Test
    void getMostDistantVertexAndDistance_BigTree() {
        SimpleGraph graph = new SimpleGraph(6);
        assertEquals(0, graph.getMostDistantVertexAndDistance().size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddVertex(15);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(4, 5);
        ArrayList<Integer> res = graph.getMostDistantVertexAndDistance();
        assertEquals(3, res.size());
        assertEquals(0, res.get(0));
        assertEquals(5, res.get(1));
        assertEquals(3, res.get(2));
    }

    @Test
    void getMostDistantVertexAndDistance_LongInMiddle() {
        SimpleGraph graph = new SimpleGraph(8);
        assertEquals(0, graph.getMostDistantVertexAndDistance().size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddVertex(15);
        graph.AddVertex(16);
        graph.AddVertex(17);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(4, 5);
        graph.AddEdge(0, 6);
        graph.AddEdge(6, 7);
        ArrayList<Integer> res = graph.getMostDistantVertexAndDistance();
        assertEquals(3, res.size());
        assertEquals(0, res.get(0));
        assertEquals(5, res.get(1));
        assertEquals(3, res.get(2));
    }

    @Test
    void testGetCycles_EmptyAndSmallGraph() {
        SimpleGraph graph = new SimpleGraph(3);
        assertEquals(0, graph.getCycles().size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);
        assertEquals(0, graph.getCycles().size());
        graph.AddEdge(0, 2);
        ArrayList<ArrayList<Integer>> res = graph.getCycles();
        assertEquals(1, res.size());
        assertEquals(0, res.get(0).get(0));
        assertEquals(1, res.get(0).get(1));
        assertEquals(2, res.get(0).get(2));
        graph.AddEdge(2, 2);
        res = graph.getCycles();
        assertEquals(1, res.size());
        assertEquals(0, res.get(0).get(0));
        assertEquals(1, res.get(0).get(1));
        assertEquals(2, res.get(0).get(2));
        assertEquals(2, res.get(1).get(0));
    }

    @Test
    void testGetCycles_BigGraph() {
        SimpleGraph graph = new SimpleGraph(5);
        assertEquals(0, graph.getCycles().size());
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
        ArrayList<ArrayList<Integer>> res = graph.getCycles();
        assertEquals(5, res.size());
    }
}