#! /usr/bin/python
# Missionary and Canibals problem

__author__ = 'brad'


class ProblemState():
    def __init__(self, cannLeft, missLeft, boat, cannRight, missRight):
        self.cannLeft = cannLeft
        self.missLeft = missLeft
        self.boat = boat
        self.cannRight = cannRight
        self.missRight = missRight
