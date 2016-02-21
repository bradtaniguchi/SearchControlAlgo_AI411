#! /usr/bin/python
__author__ = 'Bradley Taniguchi'


class Tree:
    """
    Tree is a basic definition of a a moderate tree structure
    """
    def __init__(self, item=None, children=None):
        """
        Constructor of Tree Object, default is its root
        :param item: Item at this node
        :param children: List of children branches
        """
        self.mychildren = []
        if children is not []:
            for child in range(0,children):
                self.mychildren.append(child)
        self.node = item

    def testprint(self):
        """Test that prints out contents of node"""
        print(str(self.node))

    def printchildren(self):
        """Print out children nodes"""
        print("Printing Children")
        print(self.mychildren)
        for child in self.mychildren:
            child.testprint()

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
