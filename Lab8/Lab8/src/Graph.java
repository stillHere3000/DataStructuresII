/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/12/17
 * 
 * Lab 8: Breadth First Traversal
 * Pseudocode/Algorithm: 
 *      1. Create a graph class with a map of vertices and their adjacent vertices
 *     2. Create a method to add a vertex to the graph
 *    3. Create a method to add an edge between two vertices
 *  4. Create a method to perform a breadth first traversal of the graph
 * 5. In the main method, create a graph object
 * 6. Add vertices to the graph
 * 7. Add edges to the graph
 * 8. Call the breadth first traversal method on the graph object
 * 9. Print the results
 * 
 * 
 * Code: 
 * No input necessary
 * Output: 
 *
 * The algorithm works as expected.
 */
import java.util.*;

/**
 * Represents a graph data structure.
 */
public class Graph {
    private Map<Character, List<Character>> adjVertices = new HashMap<>();

    /**
     * Adds a vertex to the graph with the specified label.
     * If a vertex with the same label already exists, it is not added again.
     *
     * @param label the label of the vertex to be added
     */
    void addVertex(char label) {
        adjVertices.putIfAbsent(label, new ArrayList<>());
    }

    /**
     * Adds an edge between two vertices in the graph.
     * The graph is assumed to be undirected, so the edge is added in both directions.
     *
     * @param src  the source vertex
     * @param dest the destination vertex
     */
    void addEdge(char src, char dest) {
        adjVertices.get(src).add(dest);
        adjVertices.get(dest).add(src); // Assuming the graph is undirected
    }

    /**
     * Performs a Breadth-First Search (BFS) traversal starting from the specified vertex.
     * Prints the visited vertices in the order they are traversed.
     *
     * @param start the starting vertex for the BFS traversal
     */
    void BFS(char start) {
        Set<Character> visited = new LinkedHashSet<>();
        Queue<Character> queue = new LinkedList<>();
        
        queue.add(start);
        visited.add(start);
        
        while (!queue.isEmpty()) {
            char vertex = queue.poll();
            System.out.print(vertex + " ");
            
            List<Character> list = adjVertices.get(vertex);
            for (char v : list) {
                if (!visited.contains(v)) {
                    visited.add(v);
                    queue.add(v);
                }
            }
        }
        System.out.println("\nAll vertices are visited.\n");
    }

    /**
     * The main method is the entry point of the program.
     * It creates a graph, adds vertices and edges to the graph,
     * and performs a breadth-first traversal starting from vertex 'a'.
     */
    public static void main(String[] args) {
        Graph g = new Graph();
        // Add vertices
        g.addVertex('a');        g.addVertex('b');        g.addVertex('c');
        g.addVertex('d');        g.addVertex('e');        g.addVertex('f');
        g.addVertex('g');        g.addVertex('h');        g.addVertex('i');
        
        // Add edges
        g.addEdge('a', 'b');        g.addEdge('a', 'f');        g.addEdge('a', 'i');
        g.addEdge('b', 'c');        g.addEdge('b', 'e');        g.addEdge('c', 'd');
        g.addEdge('c', 'e');        g.addEdge('d', 'e');        g.addEdge('d', 'h');
        g.addEdge('e', 'f');        g.addEdge('e', 'g');        g.addEdge('f', 'g');
        g.addEdge('g', 'h');
        
        System.out.println("Breadth First Traversal starting from vertex 'a':");
        g.BFS('a');
    }
}
