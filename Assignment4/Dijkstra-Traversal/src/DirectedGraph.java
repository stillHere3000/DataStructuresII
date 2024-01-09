/*
 * COSC 2007 Section O & LAB O 
 * Author: Trevor Maliro 
 * Student ID: 239498690
 * Date: 2023/12/17
 * 
 * Assignment 4
 * Pseudocode/Algorithm: 
 *      1. Create a graph class with a map of vertices and their adjacent vertices
 *     2. Create a method to add a vertex to the graph
 *    3. Create a method to add an edge between two vertices weighted.
 *  4. Create a method to perform a Djikstra's Algo of the graph
 * 5. In the main method, create a graph object
 * 6. Add vertices to the graph
 * 7. Add edges to the graph
 * 8. Call the breadth printDjikstra's Algo method on the graph object
 * 9. Print the results
 * 
 * 
 * Code: 
 * No input necessary
 * Output: 
 *
 * The algorithm works as expected.
 */
import java.util.Arrays;

public class DirectedGraph {
    private int vertices;
    private int[][] adjacencyMatrix;

    public DirectedGraph(int vertices) {
        this.vertices = vertices;
        adjacencyMatrix = new int[vertices][vertices];
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyMatrix[source][destination] = weight;
    }

    public void dijkstra(int sourceVertex) {
        boolean[] visited = new boolean[vertices];
        int[] distance = new int[vertices];
        int[] previous = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        distance[sourceVertex] = 0;

        for (int i = 0; i < vertices; i++) {
            int u = findMinDistance(distance, visited);
            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (!visited[v] && adjacencyMatrix[u][v] != 0 && 
                    (distance[u] + adjacencyMatrix[u][v]) < distance[v]) {
                    distance[v] = distance[u] + adjacencyMatrix[u][v];
                    previous[v] = u;
                }
            }
        }

        printDijkstra(distance, sourceVertex, previous);
    }

    /**
     * Performs a Dijkstra's traversal and prints every node in the path
     * 
     * @param int[] distances weighted values for each path
     * @param boolean list of visited nodes
     * @return the int weight value for the path
     */
    private int findMinDistance(int[] distance, boolean[] visited) {
        int minDistance = Integer.MAX_VALUE, minDistanceVertex = -1;
        for (int i = 0; i < vertices; i++) {
            if (!visited[i] && distance[i] < minDistance) {
                minDistance = distance[i];
                minDistanceVertex = i;
            }
        }
        return minDistanceVertex;
    }

    /**
     * Performs a Dijkstra's traversal and prints every node in the path
     * 
     * @param int[] distances weighted values for each path
     * @param sourceVertex the current node on the path
     * @param int[] the previous path traversed
     */
    private void printDijkstra(int[] distances, int sourceVertex, int[] previous) {
        System.out.println("Dijkstra Algorithm: (Source Vertex: " + (char)(getVertex(sourceVertex)) + ")");
        for (int i = 0; i < vertices; i++) {
            if (i != sourceVertex) {
                System.out.print("Distance to vertex " + (char)(getVertex(i)) + " is " + distances[i]);
                printPath(i, previous);
                System.out.println();
            }
        }
    }

    /**
     * Performs a right rotation on the specified node.
     * 
     * @param currentVertex the current node
     * @param int[] the previous path traversed
     */
    private void printPath(int currentVertex, int[] previous) {
        if (currentVertex == -1) {
            return;
        }
        printPath(previous[currentVertex], previous);
        System.out.print(" -> " + (char)( getVertex(currentVertex) ));
    }

    /**
     * Returns char for a particular vertex.
     * 
     * @param x the choosen vertex
     * @return the character of the vertex
     */
    private char getVertex(int x){
        char vertex='0';
        switch(x){
            case 0: vertex= 's';    break;
            case 1: vertex= 'a';    break;
            case 2: vertex= 'b';    break;
            case 3: vertex= 'c';    break;
            case 4: vertex= 'd';    break;
            default:        break;

        }
        return vertex;
    }


    /**
     * Sets up the directed weighhjted graph and traverses it using dijkstra algo
     * 
     */
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph(5);
        graph.addEdge(0, 1, 4); // s -> a
        graph.addEdge(0, 2, 2); // s -> b
        graph.addEdge(1, 2, 3); // a -> b
        graph.addEdge(1, 3, 2); // a -> c
        graph.addEdge(1, 4, 3); // a -> d
        graph.addEdge(2, 1, 1); // b -> a
        graph.addEdge(2, 3, 4); // b -> c
        graph.addEdge(2, 4, 5); // b -> d
        graph.dijkstra(0);        
    }
}
