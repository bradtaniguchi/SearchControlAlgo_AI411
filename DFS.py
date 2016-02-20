#! /usr/bin/python
__author__ = 'Bradley Taniguchi'


class Tree:
    """
    Tree is a basic definition of a basic tree structure
    """
    def __init__(self, item, lparent=None, rparent=None):
        """
        Constructor of Tree Object, default is its root
        """
        if (lparent and rparent) is None:  # logically I hope this is true!
            self.root = True
        else:
            self.root = False
        self.lparent = lparent
        self.rparent = rparent
        self.node = item


def depth_first_search(graphtosearch, itemtofind):
    """
    A depth first search in list: listtosearch, to find itemtofind
    :param graphtosearch: list to search through
    :param itemtofind: Item to find
    :return: Boolean, true if found, false if not foudn
    """
    if graphtosearch.node == itemtofind: # we found the item
        print("WE FOUND THE ITEM!")
        return True
    else:
        depth_first_search(graphtosearch.lparent, itemtofind)  # recursivly find depth first item
        depth_first_search(graphtosearch.rparent, itemtofind)