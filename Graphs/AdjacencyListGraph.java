package Graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class vertex {
    String name;
    ArrayList<edge> edges;

    public vertex(String name) {
        this.name = name;
        this.edges = new ArrayList<edge>();
    }
}

class edge {
    int weight;
    vertex destination;

    public edge(int weight, vertex destination) {
        this.weight = weight;
        this.destination = destination;
    }
}

public class AdjacencyListGraph {
    Map<String, vertex> vertices = new HashMap<String, vertex>();
    int numberOfVertices = 0;
    int numberOfEdges = 0;
    boolean isDirected = false;

    public AdjacencyListGraph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public void addEdge(String vertex1, String vertex2, int weight) {
        ArrayList<edge> edges = vertices.get(vertex1).edges;
        boolean ishere = false;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).destination.name.equals(vertex2) && edges.get(i).weight == weight) {
                ishere = true;
                break;
            } else if (edges.get(i).destination.name.equals(vertex2) && edges.get(i).weight != weight) {
                edges.get(i).weight = weight;
                break;
            }
        }
        if (!ishere) {
            edges.add(new edge(weight, vertices.get(vertex2)));
        }
        numberOfEdges++;
        if (!isDirected) {
            edges = vertices.get(vertex2).edges;
            ishere = false;
            for (int i = 0; i < edges.size(); i++) {
                if (edges.get(i).destination.name.equals(vertex1) && edges.get(i).weight == weight) {
                    ishere = true;
                    break;
                } else if (edges.get(i).destination.name.equals(vertex1) && edges.get(i).weight != weight) {
                    edges.get(i).weight = weight;
                    break;
                }
            }
            if (!ishere) {
                edges.add(new edge(weight, vertices.get(vertex1)));
            }
        }
    }

    public void addVertex(String name) {
        vertices.put(name, new vertex(name));
        numberOfVertices++;
    }

    public void removeVertex(String name) {
        vertices.remove(name);
        for (int i = 0; i < vertices.keySet().size(); i++) {
            ArrayList<edge> edges = vertices.get(vertices.keySet().toArray()[i]).edges;
            for (int j = 0; j < edges.size(); j++) {
                if (edges.get(j).destination.name.equals(name)) {
                    edges.remove(j);
                    numberOfEdges--;
                    break;
                }
            }
        }
        numberOfVertices--;
    }

    public boolean containsVertex(String name) {
        return vertices.containsKey(name);
    }

    public boolean containsEdge(String vertex1, String vertex2, int weight) {
        ArrayList<edge> edges = vertices.get(vertex1).edges;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).destination.name.equals(vertex2) && edges.get(i).weight == weight) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public int getNumberOfEdges() {
        return numberOfEdges;
    }

    public String[] adjacentNodes(String vertex) {
        ArrayList<edge> edges = vertices.get(vertex).edges;
        String[] result = new String[edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            result[i] = edges.get(i).destination.name;
        }
        return result;
    }

    public void print() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.keySet().toArray()[i] + ": ");
            for (int j = 0; j < vertices.get(vertices.keySet().toArray()[i]).edges.size(); j++) {
                System.out.print("{v: " + vertices.get(vertices.keySet().toArray()[i]).edges.get(j).destination.name
                        + ", w: " + vertices.get(vertices.keySet().toArray()[i]).edges.get(j).weight);
                if (j != vertices.get(vertices.keySet().toArray()[i]).edges.size() - 1) {
                    System.out.print("}, ");
                } else {
                    System.out.print("} ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(false);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("B", "C", 3);
        graph.addEdge("D", "E", 2);

        graph.print();
        System.out.println();

        graph.removeVertex("A");
        graph.print();
    }
}