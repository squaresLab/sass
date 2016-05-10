from __future__ import print_function
import sys
import re

class PlanNode:

    def __init__(self):
        self.parent=None
        self.childCount=0
        self.children=[]
        self.stringName=""
        self.className=""
        self.forCount=0


    def addChild(self,child):
        self.children.append(child)

tokenToClassDict={
';': 'SequenceOperator',
'if-then:' : 'IfThenElseOperator',
'Loop' : 'ForOperator'}

tokenToChildCountDict={
';': 2,
'if-then:': 3,
'Loop' : 1}

def getClass(token):
    if tokenToClassDict.has_key(token):
        return tokenToClassDict[token]
    else:
        return token


def getChildrenCount(token):
    if tokenToChildCountDict.has_key(token):
        return tokenToChildCountDict[token]
    else:
        return 0

def dashrepl(matchobj):
    return matchobj.group(0).replace(' ','-')

def tokenize(planString):
    planString=planString.replace('(',' ( ').replace(')',' ) ')
    pat=re.compile('Loop.*?times')
    pat2=re.compile('if then:')
    planString=re.sub(pat,dashrepl,planString)
    planString=re.sub(pat2,dashrepl,planString)
    return planString.split()

def setChildrenAndClassName(p):
    if p.stringName.find('Loop-for-') != -1:
        line=p.stringName[9:]
        pos=line.find('-')
        loopCount=line[:pos]
        loopCount=int(loopCount)
        tokenString='Loop'
        p.className=getClass(tokenString)
        p.childCount=getChildrenCount(tokenString)
        p.forCount=loopCount
    else:
        p.className=getClass(p.stringName)
        p.childCount=getChildrenCount(p.stringName)

def read_from_tokens(tokens):
    if len(tokens) == 0:
        raise SyntaxError('unexpected EOF while reading')
    token = tokens.pop(0)
    if '(' == token:
        L = []
        while tokens[0] != ')':
            L.append(read_from_tokens(tokens))
        tokens.pop(0) # pop off ')'
        return L
    elif ')' == token:
        raise SyntaxError('unexpected )')
    else:
        return str(token)



def printBeforePlanString():
    beforeString='''
\tOmnetStateData sd = new OmnetStateData();
\tsd.initializeState();
\tGPIndividual ind = new GPIndividual();
\tGPTree[] treeInit = {new GPTree()};
\tind.trees = treeInit;'''
    print(beforeString)

def printAfterPlanString():
    print( '''
\tEvolve ev = new Evolve();
\tString[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
\tParameterDatabase params = ev.loadParameterDatabase(inputFile);
\tEvolutionState state = ev.initialize(params,0);
\t((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
\t((GPIndividual)ind), new SingleObjectiveProblem());
\tSystem.out.println("Final Result: "+sd.singleObjectiveScore());
\tassertTrue(sd.singleObjectiveScore()==0);''')

def buildNodeFromToken(token):
    p=PlanNode()
    p.stringName=str(token)
    setChildrenAndClassName(p)
    return p

def buildPlanFromList(planList):
    if type(planList[0]) is  list:
        return buildPlanFromList(planList)
    else:
        if type(planList) is str:
            p = buildNodeFromToken(planList)
        else:
            p = buildNodeFromToken(planList[0])
        childCount=1
        while childCount < p.childCount+1:
            child=buildPlanFromList(planList[childCount])
            p.addChild(child)
            child.parent=p
            childCount=childCount+1
        return p



def planFromString(planString):
    tokens=tokenize(planString)
    planList=read_from_tokens(tokens)
    return buildPlanFromList(planList)

def printNodeInPlan(plan,nodeCount):
    allChildrenAreLeafs=True
    for child in plan.children:
        if len(child.children) !=0:
            allChildrenAreLeafs=False
            break
    print("\tGPNode node%d = new %s();" % (nodeCount,plan.className))
    if plan.className=='ForOperator':
        print("\t((ForOperator)node%d).setForCount(%d);" %(nodeCount,plan.forCount))
    if allChildrenAreLeafs:
        print("\tGPNode[] node%dChildren = {" % (nodeCount),end='')
        for childCount,child in enumerate(plan.children):
            print("new %s()" % (child.className),end='')
            if childCount == len(plan.children)-1:
                print ("};")
            else:
                print(', ',end='')
        print("\tnode{0}.children=node{0}Children;".format(nodeCount))
        return nodeCount
    else:
        currentNodeCount=nodeCount
        childNodesCount=[]
        for child in plan.children:
            if len(child.children) !=0:
                childNodesCount.append(nodeCount+1)
                nodeCount=printNodeInPlan(child,nodeCount+1)
        print("\tGPNode[] childrenOfNode%d = new GPNode[%d];" % (currentNodeCount,plan.childCount))
        for childCount,child in enumerate(plan.children):
            if len(child.children) == 0:
                childString="new %s()" % (child.className)
            else:
                childString="node%d" % (childNodesCount.pop(0))
            print("\tchildrenOfNode%d[%d]=%s;" % (currentNodeCount,childCount,childString))
        print("\tnode{0}.children=childrenOfNode{0};".format(currentNodeCount))
        return nodeCount

def printPlan(plan):
    printNodeInPlan(plan,1)
    print("\tind.trees[0].child=node1;")

def main(argv):
    if len(argv) != 2:
        print("Incorrect use of script")
        print("Please specify the plan string")
    else:
        printBeforePlanString()
        plan = planFromString(argv[1])
        printPlan(plan)
        printAfterPlanString()

if __name__=="__main__":
    main(sys.argv)
