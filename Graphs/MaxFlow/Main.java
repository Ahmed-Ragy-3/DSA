package Graphs.MaxFlow;

public class Main {
	public static void main(String[] args) {
		FlowNetwork G = new FlowNetwork(6);
		G.addEdge(new FlowEdge(0, 1, 16));
		G.addEdge(new FlowEdge(0, 2, 13));
		G.addEdge(new FlowEdge(1, 2, 10));
		G.addEdge(new FlowEdge(2, 1, 4));
		G.addEdge(new FlowEdge(1, 3, 12));
		G.addEdge(new FlowEdge(2, 4, 14));
		G.addEdge(new FlowEdge(3, 2, 9));
		G.addEdge(new FlowEdge(3, 5, 20));
		G.addEdge(new FlowEdge(4, 3, 7));
		G.addEdge(new FlowEdge(4, 5, 4));

		FordFulkerson maxflow = new FordFulkerson(G, 0, 5);
		System.out.println("Max flow from 0 to 5: " + maxflow.value());

		System.out.println("Min cut:");
		for (int v = 0; v < G.numberOfVertices(); v++)
			if (maxflow.inCut(v))
				for (FlowEdge e : G.adj(v)) {
					int w = e.other(v);
					if (!maxflow.inCut(w) && e.capacity() > 0)
						System.out.println(e);
				}
	}

}
