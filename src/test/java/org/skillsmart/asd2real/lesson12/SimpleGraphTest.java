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
}