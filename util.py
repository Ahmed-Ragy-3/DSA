import heapq
class Node:
    def __init__(self, state, parent, action, cost):
        self.state = state
        self.parent = parent
        self.action = action
        self.cost = cost
    

    def __lt__(self, other):
        return self.cost < other.cost

class StackFrontier():
    def __init__(self):
        self.frontier = []
        self.size = 0

    def add(self, node):
        self.frontier.append(node)
        self.size += 1

    def contains_state(self, state):
        return any(node.state == state for node in self.frontier)

    def empty(self):
        return len(self.frontier) == 0

    def remove(self):
        if self.empty():
            raise Exception("empty frontier")
        else:
            node = self.frontier[-1]
            self.frontier = self.frontier[:-1]
            self.size -= 1
            
            return node
    


class QueueFrontier(StackFrontier):

    def remove(self):
        if self.empty():
            raise Exception("empty frontier")
        else:
            node = self.frontier[0]
            self.frontier = self.frontier[1:]
            self.size -= 1
            return node
        
class PriorityQueue:
    def __init__(self):
        self.elements = []


    def empty(self):
        return len(self.elements) == 0

    def add(self, item, priority):
        heapq.heappush(self.elements, (priority, item))

    def pop(self):
        return heapq.heappop(self.elements)[1]

    def contains_state(self, state):
        return any(item[1].state == state for item in self.elements)

