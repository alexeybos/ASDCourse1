package org.skillsmart.asd2real.lesson11;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void getMostDistantVertexAndDistance_EmptyAndSmallTree() {
        SimpleGraph graph = new SimpleGraph(3);
        assertEquals(0, graph.getMostDistantVertexAndDistance().size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddEdge(0, 1);
        graph.AddVertex(12);
        graph.AddEdge(1, 2);
        ArrayList<Integer> res = graph.getMostDistantVertexAndDistance();
        assertEquals(3, res.size());
        assertEquals(2, res.get(0));
        assertEquals(0, res.get(1));
        assertEquals(2, res.get(2));
    }

    @Test
    void getMostDistantVertexAndDistance_Cycles() {
        SimpleGraph graph = new SimpleGraph(7);
        assertEquals(0, graph.getMostDistantVertexAndDistance().size());
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddVertex(15);
        graph.AddVertex(16);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(2, 4);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 5);
        graph.AddEdge(5, 6);
        ArrayList<Integer> res = graph.getMostDistantVertexAndDistance();
        assertEquals(3, res.size());
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
        assertEquals(5, res.get(0));
        assertEquals(2, res.get(1));
        assertEquals(5, res.get(2));
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
        assertEquals(5, res.get(0));
        assertEquals(7, res.get(1));
        assertEquals(5, res.get(2));
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
        assertTrue(res.getFirst().contains(0));
        assertTrue(res.getFirst().contains(1));
        assertTrue(res.getFirst().contains(2));
        graph.AddEdge(2, 2);
        res = graph.getCycles();
        assertEquals(2, res.size());
        assertTrue(res.getFirst().contains(0));
        assertTrue(res.getFirst().contains(1));
        assertTrue(res.getFirst().contains(2));
        assertTrue(res.get(1).contains(2));
    }

    /**
     *   (0)     (3)
     *    |  \  /  |
     *    |  (2)   |
     *    |  /  \  |
     *   (1)     (4)
     *   Алгоритм найдет:
     *   Cycle1 0->1->2->0
     *   Cycle3 2->3->4->2
     */
    @Test
    void testGetCycles_GraphWithCommonVerticesInCycles() {
        SimpleGraph graph = new SimpleGraph(5);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddVertex(14);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(2, 4);
        graph.AddEdge(3, 4);
        ArrayList<ArrayList<Integer>> res = graph.getCycles();
        assertEquals(2, res.size());
        ArrayList<Integer> c1 = res.getFirst();
        assertTrue(c1.contains(0));
        assertTrue(c1.contains(1));
        assertTrue(c1.contains(2));

        ArrayList<Integer> c2 = res.get(1);
        assertTrue(c2.contains(2));
        assertTrue(c2.contains(3));
        assertTrue(c2.contains(4));
    }

    /**
     *           (1)
     *          / | \
     *       (0)  |  (3)
     *         \  | /
     *           (2)
     *   Алгоритм найдет:
     *   Cycle1 0->1->2->0
     *   Cycle2 1->2->3->1
     *   Cycle3 0->1->3->2->0
     *
     */
    @Test
    void testGetCycles_GraphWithAdjacentVerticesInCycles() {
        SimpleGraph graph = new SimpleGraph(4);
        graph.AddVertex(10);
        graph.AddVertex(11);
        graph.AddVertex(12);
        graph.AddVertex(13);
        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 2);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        ArrayList<ArrayList<Integer>> res = graph.getCycles();
        assertEquals(3, res.size());
        ArrayList<Integer> c1 = res.getFirst();
        assertEquals(3, c1.size());
        assertTrue(c1.contains(0));
        assertTrue(c1.contains(1));
        assertTrue(c1.contains(2));

        ArrayList<Integer> c2 = res.get(1);
        assertEquals(3, c2.size());
        assertTrue(c2.contains(3));
        assertTrue(c2.contains(1));
        assertTrue(c2.contains(2));

        ArrayList<Integer> c3 = res.get(2);
        assertEquals(4, c3.size());
        assertTrue(c3.contains(0));
        assertTrue(c3.contains(3));
        assertTrue(c3.contains(1));
        assertTrue(c3.contains(2));
    }
}