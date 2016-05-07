import sys
import re
import ete3
import copy

class TreeNode:
    parent=None
    children=[]
    name=""
    def __init__(self,parent,name):
        self.parent=parent
        self.name=name
    def addChild(self,child):
        self.children.append(child)

def getLeafNodesOfTree(tree):
    if len(tree.children)==0:
        return [tree]
    else:
        leafNodes=[]
        for child in tree.children:
            leafNodes=leafNodes+getLeafNodesOfTree(child)
        return leafNodes


def dashrepl(matchobj):
    return matchobj.group(0).replace(' ','-')

def tokenize(planString):
    planString=planString.replace('(',' ( ').replace(')',' ) ')
    pat=re.compile('Loop.*?times')
    pat2=re.compile('if then:')
    planString=re.sub(pat,dashrepl,planString)
    planString=re.sub(pat2,dashrepl,planString)
    return planString.split()

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

def parse(planString):
    tokens=tokenize(planString)
    return read_from_tokens(tokens)

def appendTrees(tree1,tree2):
    tree1Leafs = getLeafNodesOfTree(tree1)
    print tree1Leafs
    for leaf in tree1Leafs:
        leaf.addChild(copy.deepcopy(tree2))
        tree2.parent=leaf
    return tree1

#transition the code to use ete3
#also make sure the return types are the same, some may be returning 
#children and others may be returning the parents
def buildTreeDepthFirst(expr,parent):
    if expr[0]==';':
        tree1=buildTreeDepthFirst(expr[1],parent)
        tree2=buildTreeDepthFirst(expr[2],parent)
        tree1=appendTrees(tree1,tree2)
        return [tree1]
    elif expr[0]=='if-then:':
        tree1=buildTreeDepthFirst(expr[1],parent)
        tree2=buildTreeDepthFirst(expr[2],parent)
        tree3=buildTreeDepthFirst(expr[3],parent)
        tree1=appendTrees(tree1,tree2)
        return [tree1,tree3]
    elif expr[0].find("Loop-for-") != -1:
        line=expr[0][9:]
        pos=line.find('-')
        loopCount=line[:pos]
        loopCount=int(loopCount)
        tree1=buildTreeDepthFirst(expr[1],parent)
        treeList=[tree1 for i in xrange(loopCount)]
        j=loopCount-2
        currentTree=treeList[loopCount-1]
        while j > -1:
            currentTree=appendTrees(treeList[j],currentTree)
            j=j-1
        return currentTree
    else:
        return TreeNode(parent,expr)


def convertTreeToEtetree(currentEte3Tree,dataTree):
    for child in dataTree.chilren:
        c=currentEte3Tree.add_child(name=child.name)
        c.add_face(ete3.TextFace(child.name),column=0)
        covertTreeToEtetree(c,child)


def convertSExprToTree(sExpr):
    t=TreeNode(None,"top")
    return buildTreeDepthFirst(sExpr,t)

def displayTree(tree):
    t=ete3.Tree()
    convertTreeToEtetree(t,tree)
    t.show()

def main(argv):
    if len(argv) != 2:
        print "invalid use of script"
        print "please provide the plan string"
    else:
        planString=argv[1]
        sExp=parse(planString)
        displayTree(convertSExprToTree(sExp))



if __name__=="__main__":
    main(sys.argv)
