import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    @Test
    void testAddEdge() {
        Graph graph = new Graph();
        
        // Test adding an edge between two vertices
        graph.addEdge('A', 'B');
        assertTrue(graph.adjVertices.get('A').contains('B'));
        assertTrue(graph.adjVertices.get('B').contains('A'));
        
        // Test adding an edge between the same vertices
        graph.addEdge('A', 'A');
        assertTrue(graph.adjVertices.get('A').contains('A'));
        
        // Test adding an edge between two different vertices
        graph.addEdge('B', 'C');
        assertTrue(graph.adjVertices.get('B').contains('C'));
        assertTrue(graph.adjVertices.get('C').contains('B'));
    }
}