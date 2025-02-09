package org.skillsmart.asd2real.lesson8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {

    @Test
    void testAddVertex() {
        SimpleGraph graph = new SimpleGraph(3);
        assertNull(graph.vertex[0]);
        assertNull(graph.vertex[1]);
        graph.AddVertex(10);
        graph.AddVertex(11);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(11, graph.vertex[1].Value);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, graph.m_adjacency[i][j]);
            }
        }
        graph.AddEdge(0, 1);
        graph.AddVertex(12);
        assertEquals(12, graph.vertex[2].Value);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 0 && j == 1 || i == 1 && j == 0) {
                    assertEquals(1, graph.m_adjacency[i][j]);
                } else {
                    assertEquals(0, graph.m_adjacency[i][j]);
                }
            }
        }
    }

    @Test
    void removeVertex() {
        SimpleGraph graph = new SimpleGraph(3);
        assertNull(graph.vertex[0]);
        assertNull(graph.vertex[1]);
        graph.AddVertex(10);
        graph.AddVertex(11);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(11, graph.vertex[1].Value);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, graph.m_adjacency[i][j]);
            }
        }
        graph.AddEdge(0, 1);
        graph.AddVertex(12);
        graph.AddEdge(1, 2);
        graph.AddEdge(0, 2);
        assertEquals(1, graph.m_adjacency[0][1]);
        assertEquals(1, graph.m_adjacency[0][2]);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(1, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[2][1]);
        assertEquals(1, graph.m_adjacency[1][2]);

        graph.RemoveVertex(1);
        assertNull(graph.vertex[1]);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(12, graph.vertex[2].Value);

        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(1, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(1, graph.m_adjacency[2][0]);

        assertEquals(0, graph.m_adjacency[2][1]);
        assertEquals(0, graph.m_adjacency[1][2]);
        graph.AddVertex(21);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(12, graph.vertex[2].Value);
        assertEquals(21, graph.vertex[1].Value);
    }

    @Test
    void isEdge() {
        SimpleGraph graph = new SimpleGraph(3);
        assertNull(graph.vertex[0]);
        assertNull(graph.vertex[1]);
        graph.AddVertex(10);
        graph.AddVertex(11);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(11, graph.vertex[1].Value);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, graph.m_adjacency[i][j]);
                assertFalse(graph.IsEdge(i, j));
            }
        }
        graph.AddEdge(0, 1);
        assertTrue(graph.IsEdge(0, 1));
        assertTrue(graph.IsEdge(1, 0));
        assertFalse(graph.IsEdge(0, 2));
        assertFalse(graph.IsEdge(2, 0));
        assertFalse(graph.IsEdge(1, 2));
        assertFalse(graph.IsEdge(2, 1));
        graph.AddVertex(12);
        graph.AddEdge(1, 2);
        assertTrue(graph.IsEdge(0, 1));
        assertTrue(graph.IsEdge(1, 0));
        assertTrue(graph.IsEdge(2, 1));
        assertTrue(graph.IsEdge(1, 2));
        assertFalse(graph.IsEdge(0, 2));
        assertFalse(graph.IsEdge(2, 0));
        graph.AddEdge(0, 2);
        assertTrue(graph.IsEdge(0, 1));
        assertTrue(graph.IsEdge(1, 0));
        assertTrue(graph.IsEdge(2, 1));
        assertTrue(graph.IsEdge(1, 2));
        assertTrue(graph.IsEdge(0, 2));
        assertTrue(graph.IsEdge(2, 0));

        graph.RemoveVertex(1);
        assertNull(graph.vertex[1]);
        assertFalse(graph.IsEdge(0, 1));
        assertFalse(graph.IsEdge(1, 0));
        assertFalse(graph.IsEdge(2, 1));
        assertFalse(graph.IsEdge(1, 2));
        assertTrue(graph.IsEdge(0, 2));
        assertTrue(graph.IsEdge(2, 0));
    }

    @Test
    void addEdge() {
        SimpleGraph graph = new SimpleGraph(3);
        assertNull(graph.vertex[0]);
        assertNull(graph.vertex[1]);
        graph.AddVertex(10);
        graph.AddVertex(11);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(11, graph.vertex[1].Value);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, graph.m_adjacency[i][j]);
            }
        }
        graph.AddEdge(0, 1);
        assertEquals(1, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(0, graph.m_adjacency[2][1]);
        assertEquals(0, graph.m_adjacency[1][2]);
        graph.AddVertex(12);
        assertEquals(1, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(0, graph.m_adjacency[2][1]);
        assertEquals(0, graph.m_adjacency[1][2]);
        graph.AddEdge(1, 2);
        assertEquals(1, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[2][1]);
        assertEquals(1, graph.m_adjacency[1][2]);
        graph.AddEdge(0, 2);
        assertEquals(1, graph.m_adjacency[0][1]);
        assertEquals(1, graph.m_adjacency[0][2]);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(1, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[2][1]);
        assertEquals(1, graph.m_adjacency[1][2]);
    }

    @Test
    void removeEdge() {
        SimpleGraph graph = new SimpleGraph(3);
        assertNull(graph.vertex[0]);
        assertNull(graph.vertex[1]);
        graph.AddVertex(10);
        graph.AddVertex(11);
        assertEquals(10, graph.vertex[0].Value);
        assertEquals(11, graph.vertex[1].Value);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(0, graph.m_adjacency[i][j]);
            }
        }
        graph.AddEdge(0, 1);
        graph.AddVertex(12);
        graph.AddEdge(1, 2);
        assertEquals(1, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(1, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[2][1]);
        assertEquals(1, graph.m_adjacency[1][2]);
        graph.RemoveEdge(1, 0);
        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[2][1]);
        assertEquals(1, graph.m_adjacency[1][2]);
        graph.AddEdge(0, 2);
        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(1, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(1, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[2][1]);
        assertEquals(1, graph.m_adjacency[1][2]);
        graph.RemoveEdge(0, 2);
        assertEquals(0, graph.m_adjacency[0][1]);
        assertEquals(0, graph.m_adjacency[0][2]);
        assertEquals(0, graph.m_adjacency[1][0]);
        assertEquals(0, graph.m_adjacency[2][0]);
        assertEquals(1, graph.m_adjacency[2][1]);
        assertEquals(1, graph.m_adjacency[1][2]);
    }
}