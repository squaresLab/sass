import sys
import os
import re
import random
import json
import importlib
import createJsonComponents


#This function reads a templateFile replaces the text 
#between two @ with the string expression. It then returns
#the replaced text as a string list
def createStringFromTemplateFile(templateFile,fileName,expression):
    #creating an inner function for the sub command
    #it seems to need a function
    print "filName received at line 16: %s" % (fileName)
    newFileStringList=[]
    replaceExpression=re.compile('@([^@])*@')
    with open(templateFile,'r') as fin:
        for line in fin:
            #print "line: %s" % (line)
            line=re.sub(replaceExpression,expression,line)
            #print "line after expression replaced: %s" % (line)
            newFileStringList.append(line)
    fileName=re.sub(replaceExpression,expression,fileName)
    print "fileName after subsitution on line 26: %s" % (fileName)
    return newFileStringList,fileName

#This is a generic function to create new files from a template
#it takes the directory with the files, the name of the type of files,
#and the location of the template file
def createNewFileTypeFromTemplate(dirWithAddFiles,templateFile,expressionList,fileName):
    print "filename received at line 31: %s" % (fileName)
    itemsInDir=os.listdir(dirWithAddFiles)
    sampleFile=None
    for expression in expressionList:
        newFileContents,newFileName=createStringFromTemplateFile(templateFile,fileName,expression)
        with open(newFileName,'w') as fout:
            for line in newFileContents:
                fout.write(line)


def createParamsString(startingTacticNumber, tacticTemplateStringList, expressionList):
    replaceExpression=re.compile('@([^@])*@')
    resultStringList=[]
    for tactic in tacticTemplateStringList:
        for expression in expressionList:
            singleTactic=re.sub(replaceExpression,expression,tactic)
            resultStringList.append("gp.fs.0.func.%d = omnet.tactics.%s" % (startingTacticNumber,singleTactic))
            resultStringList.append("gp.fs.0.func.%d.nc = nc0" % (startingTacticNumber))
            startingTacticNumber = startingTacticNumber + 1
    return resultStringList



def main(argv):
    expressionList=['A','B','C','D']
    tacticTemplateList=[]
    tacticTemplateList.append('StartNewServer@@')
    tacticTemplateList.append('ShutdownServer@@')
    tacticTemplateList.append('DecreaseDimmerLevel@@')
    tacticTemplateList.append('IncreaseDimmerLevel@@')
    tacticTemplateList.append('DecreaseTrafficLevel@@')
    tacticTemplateList.append('IncreaseTrafficLevel@@')
    resultParamsString=createParamsString(3,tacticTemplateList,expressionList)
    for line in resultParamsString:
        print line
    #createNewFileTypeFromTemplate(os.getcwd(), 'StartNewServerTemplate.txt',expressionList,'StartNewServer@@.java')
    #createNewFileTypeFromTemplate(os.getcwd(), 'ShutdownTemplate.txt',expressionList,'ShutdownServer@@.java')
    #createNewFileTypeFromTemplate(os.getcwd(), 'DecreaseDimmerTemplate.txt',expressionList, 'DecreaseDimmerLevel@@.java')
    #createNewFileTypeFromTemplate(os.getcwd(), 'IncreaseDimmerTemplate.txt',expressionList, 'IncreaseDimmerLevel@@.java')
    #createNewFileTypeFromTemplate(os.getcwd(), 'DecreaseTrafficTemplate.txt',expressionList, 'DecreaseTrafficLevel@@.java')
    #createNewFileTypeFromTemplate(os.getcwd(), 'IncreaseTrafficTemplate.txt',expressionList, 'IncreaseTrafficLevel@@.java')

if __name__=="__main__":
    main(sys.argv)
