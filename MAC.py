#! /usr/bin/python
# Missionary and Canibals problem

__author__ = 'brad'

counter = 0


class Queue:
    def __init__(self):
        self.A = []
        self.start = 0

    def append(self, item):
        self.A.append(item)

    def __len__(self):
        return len(self.A) - self.start

    def extend(self, items):
        self.A.extend(items)

    def pop(self):
        e = self.A[self.start]
        self.start += 1
        if self.start > 5 and self.start > len(self.A)/2:
            self.A = self.A[self.start:]
            self.start = 0
        return e

    def __contains__(self, item):
        return item in self.A[self.start:]


class ProblemState:
    def __init__(self, cannLeft, missLeft, boat, cannRight, missRight):
        self.cannLeft = cannLeft
        self.missLeft = missLeft
        self.boat = boat
        self.cannRight = cannRight
        self.missRight = missRight
        self.parent = None

    def is_win(self):
        if self.cannLeft == 0 and self.missLeft == 0:
            return True  # WE WIN
        else:
            return False

    def is_valid(self):
        if self.missLeft >= 0 and self.missRight >= 0 \
            and self.cannLeft >= 0 and self.cannRight >= 0 \
            and (self.missLeft == 0 or self.missLeft >= self.cannLeft) \
            and (self.missRight == 0 or self.missRight >= self.cannRight):
                return True
        else:
            return False


def child(cur_state):  # Very redundant, but also very easy to understand
    children = []
    global counter
    counter += 1
    if cur_state.boat == 'left':
        new_state = ProblemState(cur_state.cannLeft, cur_state.missLeft - 2, 'right',
                                 cur_state.cannRight, cur_state.missRight + 2)
        # Two missinaries cross left to right
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " , " +
                  str(cur_state.missLeft) + " -2 ,right," + str(cur_state.cannRight) + " , " +
                  str(cur_state.missRight) + " +2 >")
            children.append(new_state)  # add new state to children states
        new_state = ProblemState(cur_state.cannLeft - 2, cur_state.missLeft, 'right',
                                 cur_state.cannRight + 2, cur_state.missRight)
        # Two cannibals cross left to right
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " -2, " +
                  str(cur_state.missLeft) + " , right," + str(cur_state.cannRight) + " +2, " +
                  str(cur_state.missRight) + " >")
            children.append(new_state)  # add new state to children states
        new_state = ProblemState(cur_state.cannLeft - 1, cur_state.missLeft - 1, 'right',
                                 cur_state.cannRight + 1, cur_state.missRight + 1)
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " -1, " +
                  str(cur_state.missLeft) + " -1,right," + str(cur_state.cannRight) + " +2, " +
                  str(cur_state.missRight) + " +2>")
            children.append(new_state)  # add new state to children states
        new_state = ProblemState(cur_state.cannLeft, cur_state.missLeft - 1, 'right',
                                 cur_state.cannRight, cur_state.missRight + 1)
        # one mission and one cannibal cross left to right
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " , " +
                  str(cur_state.missLeft) + " -1,right," + str(cur_state.cannRight) + " , " +
                  str(cur_state.missRight) + " +1 >")
            children.append(new_state)  # add new state to children states
        # one missionary crosses left to right
        new_state = ProblemState(cur_state.cannLeft - 1, cur_state.missLeft, 'right',
                                 cur_state.cannRight + 1, cur_state.missRight)
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " -1, " +
                  str(cur_state.missLeft) + "  ,right," + str(cur_state.cannRight) + " +1, " +
                  str(cur_state.missRight) + " >")
            children.append(new_state)  # add new state to children states
    else:  # boat is on the rightside
        new_state = ProblemState(cur_state.cannLeft, cur_state.missLeft + 2, 'left',
                                 cur_state.cannRight, cur_state.missRight - 2)
        # Two missinaries cross left to right
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " , " +
                  str(cur_state.missLeft) + " ,left," + str(cur_state.cannRight) + " +2, " +
                  str(cur_state.missRight) + " -2 >")
            children.append(new_state)  # add new state to children states
        new_state = ProblemState(cur_state.cannLeft + 2, cur_state.missLeft, 'left',
                                 cur_state.cannRight - 2, cur_state.missRight)
        # Two cannibals cross left to right
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " +2, " +
                  str(cur_state.missLeft) + " ,left, " + str(cur_state.cannRight) + " -2, " +
                  str(cur_state.missRight) + " >")
            children.append(new_state)  # add new state to children states
        new_state = ProblemState(cur_state.cannLeft + 1, cur_state.missLeft + 1, 'left',
                                 cur_state.cannRight - 1, cur_state.missRight - 1)
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " +1, " +
                  str(cur_state.missLeft) + " +1,left, " + str(cur_state.cannRight) + " -1, " +
                  str(cur_state.missRight) + " -1 >")
            children.append(new_state)  # add new state to children states
        new_state = ProblemState(cur_state.cannLeft, cur_state.missLeft + 1, 'left',
                                 cur_state.cannRight, cur_state.missRight - 1)
        # one mission and one cannibal cross left to right
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " , " +
                  str(cur_state.missLeft) + " +1,left, " + str(cur_state.cannRight) + " , " +
                  str(cur_state.missRight) + " -1 >")
            children.append(new_state)  # add new state to children states
        # one missionary crosses left to right
        new_state = ProblemState(cur_state.cannLeft + 1, cur_state.missLeft, 'left',
                                 cur_state.cannRight - 1, cur_state.missRight)
        # one mission and one cannibal cross left to right
        if new_state.is_valid():
            new_state.parent = cur_state
            print("Appending <" + str(cur_state.cannLeft) + " +1, " +
                  str(cur_state.missLeft) + " ,left, " + str(cur_state.cannRight) + " -1, " +
                  str(cur_state.missRight) + " >")
            children.append(new_state)  # add new state to children states
    return children


def depth_first_search():
    initial_state = ProblemState(3, 3, 'left', 0, 0)
    if initial_state.is_win():
        return initial_state
    unknown = Queue()
    explored = set()  # what is this?
    unknown.append(initial_state)
    while unknown:
        state = unknown.pop()
        if state.is_win():
            return state
        explored.add(state)
        children = child(state)
        for kid in children:
            if (kid not in explored) or (kid not in unknown):
                unknown.append(kid)
    return None


def print_answer(solution):
    path = []
    path.append(solution)
    parent = solution.parent
    while parent:
        path.append(parent)
        parent = parent.parent

    for t in range(len(path)):
        state = path[len(path) - t - 1]
        print("(" + str(state.cannLeft) + "," + str(state.missLeft) +
              "," + state.boat + "," + str(state.cannRight) + "," +
              str(state.missRight) + ")")
