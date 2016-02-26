#! /usr/bin/python
# Bradley Taniguchi
# Search Control Algo for CSC 411 AI class

from MAC import *

__author__ = 'Bradley Taniguchi'


def main():
    print("IN MAIN")
    answer = depth_first_search()
    print("SolutionFound!")
    print("MissionaryLeft,CannibalsLeft,boat,cannibalRight,missionaryRight")
    print()
    print_answer(answer)

if __name__ == "__main__":
    main()
