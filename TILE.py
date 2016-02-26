__author__ = 'brad'
#from collections import deque #get queue


class ProblemState:
    """
    Problem State is defined by where the 3 white tiles are, 3 black tiles are and
    the 1 null area
    """
    def __init__(self, variablestring):
        if len(variablestring) == 7:  # MUST be this size
            self.myarray = variablestring  # needs to be
        else:
            print(">BAD VARIABLE STRING")
            self.myarray = []
