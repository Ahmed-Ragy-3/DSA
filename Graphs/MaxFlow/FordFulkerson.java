package Graphs.MaxFlow;

import java.util.LinkedList;
import java.util.Queue;

public class FordFulkerson {
    private boolean[] marked;   // marked[v] = true if s -> v path in residual graph
    private FlowEdge[] edgeTo;  // edgeTo[v] = last edge on s -> v path
    private double value;       // current max flow

    public FordFulkerson(FlowNetwork G, int s, int t) {
        while (hasAugmentingPath(G, s, t)) {
            double bottle = Double.POSITIVE_INFINITY;

            for (int v = t; v != s; v = edgeTo[v].other(v))
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));

            for (int v = t; v != s; v = edgeTo[v].other(v))
                edgeTo[v].addResidualFlowTo(v, bottle);

            value += bottle;
        }
    }

    public double value() {
        return value;
    }

    public boolean inCut(int v) {
        return marked[v];
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        edgeTo = new FlowEdge[G.numberOfVertices()];
        marked = new boolean[G.numberOfVertices()];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        marked[s] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (FlowEdge e : G.adj(u)) {
                int v = e.other(u);

                if (e.residualCapacityTo(v) > 0 && !marked[v]) {
                    edgeTo[v] = e;
                    marked[v] = true;
                    queue.add(v);
                }
            }
        }

        return marked[t];
    }
}
