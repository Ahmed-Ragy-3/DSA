import copy
import random
from util import Node, PriorityQueue

UP = "up"
RIGHT = "right"
LEFT = "left"
DOWN = "down"

def print_board(board):
    for i in range(4):
        print("____________________________")
        print(f"|  {board[i][0]}  |  {board[i][1]}  |  {board[i][2]}  |  {board[i][3]}  |")
    print("____________________________")

def is_solvable(board):
    flat_board = [num for row in board for num in row if num is not None]
    inversions = 0

    for i in range(len(flat_board)):
        for j in range(i + 1, len(flat_board)):
            if flat_board[i] > flat_board[j]:
                inversions += 1

    empty_row = get_empty_block(board)[0]
    return (inversions % 2 == 0) if (empty_row % 2 == 1) else (inversions % 2 == 1)

def get_empty_block(board):
    for i in range(4):
        for j in range(4):
            if board[i][j] is None:
                return (i, j)

def initial_state():
    nums = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
    while True:
        board = [[None, None, None, None],
                 [None, None, None, None],
                 [None, None, None, None],
                 [None, None, None, None]]
        random.shuffle(nums)
        k = 0
        for i in range(4):
            for j in range(4):
                if i == 3 and j == 3:
                    break
                board[i][j] = nums[k]
                k += 1
        if is_solvable(board):
            return board

def available_actions(board):
    location = get_empty_block(board)
    actions = []
    if location[0] != 0:
        actions.append(DOWN)
    if location[0] != 3:
        actions.append(UP)
    if location[1] != 0:
        actions.append(RIGHT)
    if location[1] != 3:
        actions.append(LEFT)
    return actions

def neighbors(board):
    actions = available_actions(board)
    neighbors = []
    for action in actions:
        neighbors.append((result(board, action), action))
    return neighbors

def result(board, action):
    new_board = copy.deepcopy(board)
    empty = get_empty_block(board)
    moved = [0, 0]
    
    if action == UP:
        moved[0] = empty[0] + 1
        moved[1] = empty[1]
    elif action == DOWN:
        moved[0] = empty[0] - 1
        moved[1] = empty[1]
    elif action == RIGHT:
        moved[0] = empty[0]
        moved[1] = empty[1] - 1
    elif action == LEFT:
        moved[0] = empty[0]
        moved[1] = empty[1] + 1
    else:
        raise Exception("Wrong action")
    
    new_board[empty[0]][empty[1]] = new_board[moved[0]][moved[1]]
    new_board[moved[0]][moved[1]] = None
    
    return new_board

def is_goal(board):
    if board[3][3] is not None: return False
    checker = 1
    for i in range(4):
        for j in range(4):
            if i == 3 and j == 3:
                break
            if checker != board[i][j]:
                return False
            checker += 1
    return True

def board_to_tuple(board):
    return tuple(tuple(row) for row in board)

def solve(board):
    frontier = PriorityQueue()
    explored = set()
        
    start = Node(state=board, parent=None, action=None, cost=0)
    frontier.add(start, manhattan_distance(board))
    
    iterations = 0
    while True:
        iterations += 1
        if iterations % 1000 == 0:
            print(f"{iterations} iterations")
            print(f"Size of frontier: {len(frontier.elements)}")
        
        if frontier.empty():
            return None

        node = frontier.pop()

        if is_goal(node.state):
            solution = []
            while node.parent is not None:
                solution.append((node.state, node.action))
                node = node.parent
            solution.reverse()
            return solution

        explored.add(board_to_tuple(node.state))

        for state, action in neighbors(node.state):
            state_tuple = board_to_tuple(state)
            if state_tuple not in explored and not frontier.contains_state(state):
                child = Node(state=state, parent=node, action=action, cost=node.cost + 1)
                frontier.add(child, child.cost + manhattan_distance(state))
                explored.add(state_tuple)


def manhattan_distance(state):
    goal_positions = {
        1: (0, 0), 2: (0, 1), 3: (0, 2), 4: (0, 3),
        5: (1, 0), 6: (1, 1), 7: (1, 2), 8: (1, 3),
        9: (2, 0), 10: (2, 1), 11: (2, 2), 12: (2, 3),
        13: (3, 0), 14: (3, 1), 15: (3, 2)
    }
    distance = 0
    for i in range(4):
        for j in range(4):
            if state[i][j] is not None:
                value = state[i][j]
                goal_pos = goal_positions[value]
                distance += abs(goal_pos[0] - i) + abs(goal_pos[1] - j)
    return distance

def read_board():
    array = []
    for _ in range(4):
        row = input().strip().split()
        row = [int(num) if num != "None" else None for num in row]
        array.append(row)
    return array  

def main():
    board = initial_state()
    print("Initial Board:")
    print_board(board)
    
    if is_solvable(board):
        print("The board is solvable.")
        solution = solve(board)
        
        if solution:
            for step in solution:
                print_board(step[0])
                print(f"''{step[1]}''")
            print(f"Number of steps: {len(solution)}")
            print("[", end="")
            for step in solution:
                print(f"'{step[1]}', ", end="")
            print("]")
        else:
            print("No solution found.")
    else:
        print("The board is not solvable.")

if __name__ == "__main__":
    main()
