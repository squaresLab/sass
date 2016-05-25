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
def createStringFromTemplateFile(templateFile,expression):
    #creating an inner function for the sub command
    #it seems to need a function
    newFileStringList=[]
    with open(templateFile,'r') as fin:
        replaceExpression=re.compile('@([^@])*@')
        for line in fin:
            #print "line: %s" % (line)
            line=re.sub(replaceExpression,expression,line)
            #print "line after expression replaced: %s" % (line)
            newFileStringList.append(line)
    return newFileStringList

#This is a generic function to create new files from a template
#it takes the directory with the files, the name of the type of files,
#and the location of the template file
def createNewFileTypeFromTemplate(dirWithAddFiles,fileString, templateFile):
    itemsInDir=os.listdir(dirWithAddFiles)
    foundLocationNumbers=[]
    sampleFile=None
    for item in itemsInDir:
        if item.find(fileString)!=-1:
            if sampleFile==None:
                sampleFile=item
            #looking for the add server Nubmer
            posOfInterest=item.find(fileString)+len(fileString)
            foundLocationNumbers.append(int(item[posOfInterest]))
    lastLocation=max(foundLocationNumbers)
    newLocation=lastLocation+1
    for location in range(newLocation,newLocation+3):
        newFileStringList=createStringFromTemplateFile(templateFile,str(location))
        posOfInterest=sampleFile.find(fileString)+len(fileString)
        outputFileName="%s%d%s" % (sampleFile[0:posOfInterest],location,sampleFile[posOfInterest+1:])
        with open(outputFileName,'w') as fout:
            fout.writelines(newFileStringList)

#This method was an attempt to automatically create new server experssions
def subsituteValuesForServer(templateFileStrings, expression):
    pattern=re.compile('&&')
    cost=0
    total=random.randint(150,250)
    while cost==0:
        time=random.randint(5,120)
        compPower=random.randint(3,15)
        failureWeight=random.uniform(0.01,0.2)
        cost=round((total-3.33*time+20*compPower+2000*failureWeight)/100)
        if cost < 20 or cost > 300:
            cost=0
    inputDict={}
    inputDict[0]=time
    inputDict[1]=compPower
    inputDict[2]=failureWeight
    inputDict[3]=cost
    pattersFound=0
    for line in templateFileStrings:
       e

#this method was eventually going to be changed to use the random
#server creation method
def createActionFromTemplate(templateFile,expression):
    templateFile=createStringFromTemplateFile(templateFile,expression)


#This method creates new add server files from a template
def createNewAddAction(dirWithAddFiles):
    createNewFileTypeFromTemplate(dirWithAddFiles,"AddServerL","ASTemplate.java")

#This method create new delete server fiels from a template
def createNewDeleteAction(dirWithDeleteFiles):
    createNewFileTypeFromTemplate(dirWithDeleteFiles,"DeleteServerL","DSTemplate.java")

def createComponents(dic):
    return createJsonComponents.Component(dic['name'],dic['clockTime'],dic['failureWeight'],dic['cost'],dic['computingPower'])

def loadComponents(componentFile):
    componentList=[]
    with open(componentFile) as fin:
        for line in fin:
            line=line.rstrip()
            componentList.append(createComponents(json.loads(line)))
    return componentList


def createActionsFromComponents(componentList):
    actionList=[]
    #finish this later
    return actionList


def updateParamsFile(paramsFile,actionList):
    outputResult=[]
    newActionNumber=0
    with open(paramsFile) as fin:
        for line in fin:
            if line.find('##') != -1:
                pos=line.find('##')
                line=line[0:pos]+len(actionList)+line[pos+2:]
            if line.find('gp.fs.0.func.')!=-1:
                actionNumber=int(line[13])
                if actionNumber > newActionNumber:
                    newActionNumber = actionNumber
            outputResult.append(line)
    for action in actionList:
        actionNameLine="gp.fs.0.func.%d = actions.tactics.%s" % (newActionNumber,action.name)
        outputResult.append(actionNameLine)
        #assuming that actions never have children
        childLine="gp.fs.0.func.%d.nc = nc0" % (newActionNumber)
        outputResult.append(childLine)
    return outputResult


def main(argv):
    componentList=loadComponents(argv[1])
    for component in componentList:
        print component.name
    #createNewAddAction(argv[1])
    #createNewDeleteAction(argv[1])

if __name__=="__main__":
    main(sys.argv)
