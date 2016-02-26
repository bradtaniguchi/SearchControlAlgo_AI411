__author__ = 'brad'


class ProblemState:
    def __init__(self, integerstring):
        if len(integerstring) == 9:
            self.myarray = integerstring
        else:
            print("ERROR UPON PROBLEM STATE DECLARE")
            self.myarray = []

    def if_win(self):
        if self.myarray == [1,2,3,8,0,4,7,6,5]: