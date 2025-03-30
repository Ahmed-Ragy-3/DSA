#include <bits/stdc++.h>
using namespace std;

void dfs(int node, vector<bool> &visited, vector<vector<int>> &graph) {
   visited[node] = true;
   cout << node << " ";  // Process the node
   for (int neighbor : graph[node]) {
      if (!visited[neighbor]) {
         dfs(neighbor, visited, graph);
      }
   }
}

void bfs(int start, vector<vector<int>> &graph) {
   int n = graph.size();
   vector<bool> visited(n, false);
   queue<int> q;
   q.push(start);
   visited[start] = true;

   while (!q.empty()) {
      int node = q.front();
      q.pop();
      cout << node << " ";  // Process the node
      for (int neighbor : graph[node]) {
         if (!visited[neighbor]) {
            visited[neighbor] = true;
            q.push(neighbor);
         }
      }
   }
}

void dijkstra(int start, vector<vector<pair<int, int>>> &graph) {
   int n = graph.size();
   vector<int> dist(n, INT_MAX);
   priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

   dist[start] = 0;
   pq.push({0, start});

   while (!pq.empty()) {
      int current = pq.top().second;
      pq.pop();
      for (auto &edge : graph[current]) {
         int neighbor = edge.first, weight = edge.second;
         if (dist[current] + weight < dist[neighbor]) {
            dist[neighbor] = dist[current] + weight;
            pq.push({dist[neighbor], neighbor});
         }
      }
   }

   for (int i = 0; i < n; i++) {
      cout << "Distance to " << i << " is " << dist[i] << endl;
   }
}

void bellmanFord(int start, vector<vector<int>> &edges, int n) {
    vector<int> dist(n, INT_MAX);
    dist[start] = 0;

    for (int i = 0; i < n - 1; i++) {
        bool anyUpdate = false;
        for (auto &edge : edges) {
            int u = edge[0], v = edge[1], weight = edge[2];

            if (dist[u] != INT_MAX && dist[u] + weight < dist[v]) {
                dist[v] = dist[u] + weight;
                anyUpdate = true;
            }
        }
        // If no edge was relaxed, stop early
        if (!anyUpdate) break;
    }
   
    // negative cycle detection
    for (auto &edge : edges) {
      int u = edge[0], v = edge[1], weight = edge[2];
      if (dist[u] != INT_MAX && dist[u] + weight < dist[v]) {
         // graph has negative weighted cycle
         break;
      }
    }

    for (int i = 0; i < n; i++) {
        cout << "Distance to " << i << " is " << dist[i] << endl;
    }
}
void floydWarshall(vector<vector<int>> &dist, int n) {
   for (int k = 0; k < n; k++) {
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            if (dist[i][k] != INT_MAX && dist[k][j] != INT_MAX) {
               dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
         }
      }
   }

   for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
         cout << "Distance from " << i << " to " << j << " is " << dist[i][j] << endl;
      }
   }
}

struct Edge {
   int u, v, weight;
   bool operator<(Edge const &other) { return weight < other.weight; }
};

int findParent(int v, vector<int> &parent) {
   if (parent[v] == v) return v;
   return parent[v] = findParent(parent[v], parent);
}

void kruskal(vector<Edge> &edges, int n) {
   vector<int> parent(n);
   for (int i = 0; i < n; i++) parent[i] = i;

   sort(edges.begin(), edges.end());
   vector<Edge> result;

   for (Edge e : edges) {
      int u = findParent(e.u, parent);
      int v = findParent(e.v, parent);
      if (u != v) {
         result.push_back(e);
         parent[u] = v;
      }
   }

   for (Edge e : result) {
      cout << e.u << " - " << e.v << " : " << e.weight << endl;
   }
}

void prim(vector<vector<pair<int, int>>> &graph, int n) {
   vector<bool> visited(n, false);
   priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
   pq.push({0, 0});

   while (!pq.empty()) {
      int u = pq.top().second;
      int weight = pq.top().first;
      pq.pop();

      if (visited[u]) continue;
      visited[u] = true;
      cout << "Added edge to " << u << " with weight " << weight << endl;

      for (auto &edge : graph[u]) {
         int v = edge.first, w = edge.second;
         if (!visited[v]) {
            pq.push({w, v});
         }
      }
   }
}

void topologicalSortUtil(int u, vector<bool> &visited, stack<int> &st, vector<vector<int>> &graph) {
   visited[u] = true;
   for (int v : graph[u]) {
      if (!visited[v]) {
         topologicalSortUtil(v, visited, st, graph);
      }
   }
   st.push(u);
}

void topologicalSort(vector<vector<int>> &graph, int n) {
   stack<int> st;
   vector<bool> visited(n, false);

   for (int i = 0; i < n; i++) {
      if (!visited[i]) {
         topologicalSortUtil(i, visited, st, graph);
      }
   }

   while (!st.empty()) {
      cout << st.top() << " ";
      st.pop();
   }
}

////////////////////////////////////////////////
struct Node {
   int x, y, g, h;
   bool operator>(const Node &other) const { return (g + h) > (other.g + other.h); }
};

int heuristic(int x1, int y1, int x2, int y2) { return abs(x1 - x2) + abs(y1 - y2); }

void aStar(vector<vector<int>> &grid, pair<int, int> start, pair<int, int> goal) {
   int n = grid.size();
   priority_queue<Node, vector<Node>, greater<Node>> pq;
   pq.push({start.first, start.second, 0,
            heuristic(start.first, start.second, goal.first, goal.second)});

   while (!pq.empty()) {
      Node current = pq.top();
      pq.pop();
      if (current.x == goal.first && current.y == goal.second) {
         cout << "Path found with cost " << current.g << endl;
         return;
      }
      // Explore neighbors (left, right, up, down)
      int dx[] = {-1, 1, 0, 0};
      int dy[] = {0, 0, -1, 1};
      for (int i = 0; i < 4; i++) {
         int nx = current.x + dx[i], ny = current.y + dy[i];
         if (nx >= 0 && nx < n && ny >= 0 && ny < n && grid[nx][ny] == 0) {
            int new_g = current.g + 1;
            int new_h = heuristic(nx, ny, goal.first, goal.second);
            pq.push({nx, ny, new_g, new_h});
         }
      }
   }
   cout << "No path found" << endl;
}

///////////////////////////////////////////////
void tarjan(int u, vector<int> &disc, vector<int> &low, stack<int> &st, vector<bool> &inStack,
            vector<vector<int>> &graph, int &time) {
   disc[u] = low[u] = ++time;
   st.push(u);
   inStack[u] = true;

   for (int v : graph[u]) {
      if (disc[v] == -1) {
         tarjan(v, disc, low, st, inStack, graph, time);
         low[u] = min(low[u], low[v]);
      } else if (inStack[v]) {
         low[u] = min(low[u], disc[v]);
      }
   }

   if (low[u] == disc[u]) {
      cout << "SCC: ";
      while (st.top() != u) {
         cout << st.top() << " ";
         inStack[st.top()] = false;
         st.pop();
      }
      cout << st.top() << endl;
      inStack[st.top()] = false;
      st.pop();
   }
}

void findSCCs(vector<vector<int>> &graph, int n) {
   vector<int> disc(n, -1), low(n, -1);
   vector<bool> inStack(n, false);
   stack<int> st;
   int time = 0;

   for (int i = 0; i < n; i++) {
      if (disc[i] == -1) {
         tarjan(i, disc, low, st, inStack, graph, time);
      }
   }
}
