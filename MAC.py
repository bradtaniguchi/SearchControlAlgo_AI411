#! /usr/bin/python
# Missionary and Canibals problem

__author__ = 'brad'


class Stack:
    def __init__(self):
        self.items= []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def size(self):
        return len(self.items)


class ProblemState():
    def __init__(self, cannLeft, missLeft, boat, cannRight, missRight):
        self.cannLeft = cannLeft
        self.missLeft = missLeft
        self.boat = boat
        self.cannRight = cannRight
        self.missRight = missRight
        self.parent = None

    def is_win(self):
        if self.cannLeft == 0 and self.missLeft == 0:
            return True # WE WIN
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


def child(cur_state):
    children = []
    if cur_state.boat == 'left':
        print("Poop")
    else:  # boat is on the rightside
        print("Poop")
    return children

