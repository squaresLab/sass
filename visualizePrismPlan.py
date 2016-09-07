import sys
import ete3

class AdversaryTransition:
    startState=-1
    endState=-1
    percentage=-1
    action=""
    def __init__(self,startState,endState,percentage,action):
        self.startState = startState
        self.endState = endState
        self.percentage = percentage
        self.action = action

def buildTreeDepthFirst(tranDict,t,nextState):
    if nextState in tranDict:
        possibleTrans=tranDict[nextState]
        for tran in possibleTrans:
            if tran.action == "finishedAction":
                buildTreeDepthFirst(tranDict,t,tran.endState)
            else:
                c=t.add_child(name=tran.action)
                c.dist=tran.percentage
                if float(tran.percentage) > 0.5:
                    c.add_face(ete3.TextFace(tran.action),column=0)
                else:
                    c.add_face(ete3.TextFace("action failed"),column=0)
                buildTreeDepthFirst(tranDict,c,tran.endState)



def main(argv):
    if len(argv) != 3:
        print "invalid arguments"
        print "please specify the prism adversary file location and then the prism state file"
    else:
        adversaryTransitionDict={}
        with open(argv[1],'r') as fin:
            for line in fin:
                lineItems=line.split()
                if len(lineItems)==4:
                    a=AdversaryTransition(lineItems[0],lineItems[1],lineItems[2],lineItems[3])
                    if lineItems[0] in adversaryTransitionDict:
                        adversaryTransitionDict[lineItems[0]].append(a)
                    else:
                        adversaryTransitionDict[lineItems[0]]=[a]
        with open(argv[2],'r') as fin:
            for line in fin:
                if line.find('(1,1,1,0,0,0,4,4,4,false,0)') != -1:
                    lineItems=line.split(':')
                    startingState=lineItems[0]
        print startingState
        t=ete3.Tree()
        buildTreeDepthFirst(adversaryTransitionDict,t,startingState)
        t.show()


if __name__=="__main__":
    main(sys.argv)
