package Graphs;

import java.util.HashMap;
import java.util.Map;

public class AdjacencyMatrixGraph {
    int[][] adjacencyMatrix;
    Map<Character, Integer> vertices;
    int numberOfVertices = 0;
    int numberOfEdges = 0;

    public AdjacencyMatrixGraph(int numberOfVertices) {
        this.vertices = new HashMap<>();
        for (int i = 0; i < numberOfVertices; i++) {
            this.vertices.put((char) ('a' + i), i);
        }
        this.numberOfVertices = numberOfVertices;
        adjacencyMatrix = new int[numberOfVertices][numberOfVertices];
    }

    public AdjacencyMatrixGraph(char[] vertices) {
        this.vertices = new HashMap<>();
        for (int i = 0; i < vertices.length; i++) {
            this.vertices.put(vertices[i], i);
        }
        this.numberOfVertices = vertices.length;
        adjacencyMatrix = new int[this.numberOfVertices][this.numberOfVertices];
    }

    public void addUndirectedEdge(char vertex1, char vertex2) {

        int index1 = this.vertices.get(vertex1);
        int index2 = this.vertices.get(vertex2);
        if (adjacencyMatrix[index1][index2] == 1 && adjacencyMatrix[index2][index1] == 1) {
            return;
        }
        adjacencyMatrix[index1][index2] = 1;
        adjacencyMatrix[index2][index1] = 1;
        this.numberOfEdges++;
    }

    public void addDirectedEdge(char vertex1, char vertex2) {
        int index1 = this.vertices.get(vertex1);
        int index2 = this.vertices.get(vertex2);
        if (adjacencyMatrix[index1][index2] == 1) {
            return;
        }
        adjacencyMatrix[index1][index2] = 1;
        this.numberOfEdges++;
    }

    public void removeUndirectedEdge(char vertex1, char vertex2) {

        int index1 = this.vertices.get(vertex1);
        int index2 = this.vertices.get(vertex2);
        if (adjacencyMatrix[index1][index2] == 0 && adjacencyMatrix[index2][index1] == 0) {
            return;
        }
        adjacencyMatrix[index1][index2] = 0;
        adjacencyMatrix[index2][index1] = 0;
        this.numberOfEdges--;
    }

    public void removeDirectedEdge(char vertex1, char vertex2) {

        int index1 = this.vertices.get(vertex1);
        int index2 = this.vertices.get(vertex2);
        if (adjacencyMatrix[index1][index2] == 0) {
            return;
        }
        adjacencyMatrix[index1][index2] = 0;
        this.numberOfEdges--;
    }

    public void addUndirectedEdge(int vertex1, int vertex2) {
        if (adjacencyMatrix[vertex1][vertex2] == 1 && adjacencyMatrix[vertex2][vertex1] == 1) {
            return;
        }
        adjacencyMatrix[vertex1][vertex2] = 1;
        adjacencyMatrix[vertex2][vertex1] = 1;
        this.numberOfEdges++;
    }

    public void addDirectedEdge(int vertex1, int vertex2) {
        if (adjacencyMatrix[vertex1][vertex2] == 1) {
            return;
        }
        adjacencyMatrix[vertex1][vertex2] = 1;
        this.numberOfEdges++;
    }

    public void removeUndirectedEdge(int vertex1, int vertex2) {
        if (adjacencyMatrix[vertex1][vertex2] == 0 && adjacencyMatrix[vertex2][vertex1] == 0) {
            return;
        }
        adjacencyMatrix[vertex1][vertex2] = 0;
        adjacencyMatrix[vertex2][vertex1] = 0;
        this.numberOfEdges--;
    }

    public void removeDirectedEdge(int vertex1, int vertex2) {
        if (adjacencyMatrix[vertex1][vertex2] == 0) {
            return;
        }
        adjacencyMatrix[vertex1][vertex2] = 0;
        this.numberOfEdges--;
    }

    public int getNumberOfVertices() {
        return this.numberOfVertices;
    }

    public int getNumberOfEdges() {
        return this.numberOfEdges;
    }

    public void printMatrix() {
        for (int i = 0; i < this.numberOfVertices; i++) {
            for (int j = 0; j < this.numberOfVertices; j++) {
                System.out.print(this.adjacencyMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(5);
        graph.addDirectedEdge('a', 'b');
        graph.addDirectedEdge('b', 'c');
        graph.addDirectedEdge('c', 'd');
        graph.addDirectedEdge('d', 'e');
        graph.removeDirectedEdge('d', 'e');

        graph.printMatrix();
    }
}
