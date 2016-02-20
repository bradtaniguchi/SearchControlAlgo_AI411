#! /usr/bin/python
__author__ = 'Bradley Taniguchi'


class Graph:
    def __init__(self):
        print("I AM GRAPH")


def depth_first_search(listtosearch, itemtofind):
    """
    A depth first search in list: listtosearch, to find itemtofind
    :param listtosearch: list to search through
    :param itemtofind: Item to find
    :return: Boolean, true if found, false if not foudn
    """
