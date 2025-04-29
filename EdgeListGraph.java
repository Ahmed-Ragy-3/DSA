

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class Vertex {
    String name;
    ArrayList<Edge> inEdges;
    ArrayList<Edge> outEdges;

    public Vertex(String name, boolean isDireted)
    {
        if(isDireted) 
        {
            this.name = name;
            this.inEdges = new ArrayList<Edge>();
            this.outEdges = new ArrayList<Edge>();
        }
        else{
            this.name = name;
            this.inEdges = new ArrayList<Edge>();
        }
    }

}

class Edge {
    int weight;
    Vertex source;
    Vertex destination;

    public Edge(Vertex source, Vertex destination, int weight) {
        this.weight = weight;
        this.source = source;
        this.destination = destination;
    }
}

public class EdgeListGraph {
    Map<String, Vertex> vertices = new HashMap<String, Vertex>();
    ArrayList<Edge> edges = new ArrayList<Edge>();  
    boolean isDireted;


    public EdgeListGraph(boolean isDireted) {
        this.isDireted = isDireted;
    }

    public void addVertex(String name) {
        vertices.put(name, new Vertex(name, isDireted));
    }

    public void addEdge(String source, String destination, int weight)
    {
        Edge newEdge = new Edge(vertices.get(source), vertices.get(destination), weight); 
        edges.add(newEdge);
        Vertex from = vertices.get(source);
        Vertex to = vertices.get(destination);
        if(isDireted) {
            from.outEdges.add(newEdge);
            to.inEdges.add(newEdge);
        }
        else
        {
            from.inEdges.add(newEdge);
            to.inEdges.add(newEdge);
        }
    }

    public void removeEdge(String source, String destination, int weight) 
    {
        Vertex from = vertices.get(source);
        Vertex to = vertices.get(destination);
        int isFound = 0;
        
        for(int i = 0; i < to.inEdges.size(); i++)
            {
                if(to.inEdges.get(i).source.name.equals(source) && to.inEdges.get(i).destination.name.equals(destination) && to.inEdges.get(i).weight==weight)
                {
                    isFound ++;
                    to.inEdges.remove(i);
                    break;
                }
            }
        if(isDireted) {
            for(int i = 0; i < from.outEdges.size(); i++)
            {
                if(from.outEdges.get(i).source.name.equals(source) && from.outEdges.get(i).destination.name.equals(destination) && from.outEdges.get(i).weight==weight)
                {
                    isFound ++;
                    from.outEdges.remove(i);
                    break;
                }
            }
        }
        else {
            for(int i = 0; i < from.inEdges.size(); i++)
            {
                if(from.inEdges.get(i).source.name.equals(source) && from.inEdges.get(i).destination.name.equals(destination) && from.inEdges.get(i).weight==weight)
                {
                    isFound++;
                    from.inEdges.remove(i);
                    break;
                }
            }
        }

        for(int i = 0; i < edges.size(); i++)
        {
            if(edges.get(i).destination == vertices.get(destination) && edges.get(i).source == vertices.get(source) && edges.get(i).weight == weight)
            {
                edges.remove(i);
                isFound++;
                break;

            }
        }

        if(isFound == 3)
        {
            throw new InvalidParameterException("NO EDGE IS FROM (" + source + ") TO (" + destination + ") WITH WEIGHT (" + weight + ")!");
        }
    }
    
    public boolean containsVertex(String vertex)
    {
        return vertices.containsKey(vertex);
    }

    public boolean containsEdge(String source, String destination, int weight)
    {
        for(int i = 0; i < edges.size(); i++)
        {
            if(edges.get(i).destination == vertices.get(destination) && edges.get(i).source == vertices.get(source) && edges.get(i).weight == weight)
            {
                return true;
            }
        }
        return false;
    }

    public void removeVertex(String vertex) 
    {
        if(!containsVertex(vertex))
        {
            throw new InvalidParameterException("NO VERTEX NAMED BY " + vertex);
        }
        Vertex ver = vertices.get(vertex);

        for(int i = 0; i < ver.inEdges.size(); i++)
        {
            
            Vertex in_from = ver.inEdges.get(i).destination;
            
            if(isDireted)
            {
                Vertex in_source = ver.outEdges.get(i).source;
                for(int j = 0; j < in_from.outEdges.size(); j++)
                {
                    if(in_from.outEdges.get(j).destination == vertices.get(vertex) || in_from.outEdges.get(j).source == vertices.get(vertex))
                    {
                        in_from.outEdges.remove(j);
                        j--;
                    }
                }
                for(int j = 0; j < in_source.inEdges.size(); j++)
                {
                    if(in_source.outEdges.get(j).destination == vertices.get(vertex) || in_source.outEdges.get(j).source == vertices.get(vertex))
                    {
                        in_source.inEdges.remove(j);
                        j--;
                    }
                }
                
            }
            else
            {
                for(int j = 0; j < in_from.inEdges.size(); j++)
                {
                    if(in_from.inEdges.get(j).destination == vertices.get(vertex))
                    {
                        in_from.inEdges.remove(j);
                        j--;
                    }
                }
            }

            edges.remove(ver.inEdges.get(i));
        }

        vertices.remove(vertex);
    }

    public void print()
    {
        for(int i = 0; i < vertices.size(); i++)
        {
            System.out.println();
            System.out.println(vertices.keySet().toArray()[i] + ": ");
            System.out.println("In From: ");
            for(int j = 0; j < vertices.get(vertices.keySet().toArray()[i]).inEdges.size(); j++)
            {
                if(vertices.get(vertices.keySet().toArray()[i]).inEdges.get(j).source != vertices.get(vertices.keySet().toArray()[i]))
                {
                    System.out.println(vertices.get(vertices.keySet().toArray()[i]).inEdges.get(j).source.name +"  "+ vertices.get(vertices.keySet().toArray()[i]).inEdges.get(j).weight );
                }
                else
                {
                    System.out.println(vertices.get(vertices.keySet().toArray()[i]).inEdges.get(j).destination.name +"  "+ vertices.get(vertices.keySet().toArray()[i]).inEdges.get(j).weight );
                }
                
            }
            if(isDireted)
            {
                System.out.println("Out To: ");
                for(int j = 0; j < vertices.get(vertices.keySet().toArray()[i]).outEdges.size(); j++)
                {
                    
                    if(vertices.get(vertices.keySet().toArray()[i]).outEdges.get(j).source != vertices.get(vertices.keySet().toArray()[i]))
                    {
                        System.out.println(vertices.get(vertices.keySet().toArray()[i]).outEdges.get(j).source.name +"  "+ vertices.get(vertices.keySet().toArray()[i]).outEdges.get(j).weight );
                    }
                    else
                    {
                        System.out.println(vertices.get(vertices.keySet().toArray()[i]).outEdges.get(j).destination.name +"  "+ vertices.get(vertices.keySet().toArray()[i]).outEdges.get(j).weight );
                    }
                }
            }
            
        }
    }



    public static void main(String[] args) {
        EdgeListGraph graph = new EdgeListGraph(false);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 2);
        graph.addEdge("A", "D", 3);
        graph.addEdge("B", "C", 4);


        graph.print();
        graph.removeVertex("A");

        graph.print();

        

    }

    
}
