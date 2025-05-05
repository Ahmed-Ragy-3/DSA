package Graphs.MaxFlow;

import java.util.*;

public class FlowNetwork {
	private final int n;
	private final List<FlowEdge>[] adj;

	@SuppressWarnings("unchecked")
	public FlowNetwork(int V) {
		this.n = V;
		adj = (List<FlowEdge>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<>();
	}

	public void addEdge(FlowEdge e) {
		int v = e.from(), w = e.to();
		adj[v].add(e);
		adj[w].add(e); // add both directions
	}

	public Iterable<FlowEdge> adj(int v) {
		return adj[v];
	}

	public Iterable<FlowEdge> edges() {
		Set<FlowEdge> list = new HashSet<>();
		for (int v = 0; v < n; v++)
			for (FlowEdge e : adj[v])
				if (e.from() == v)
					list.add(e);
		return list;
	}

	public int numberOfVertices() {
		return n;
	}
}
