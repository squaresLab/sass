import sys
import matplotlib
import matplotlib.pyplot

class Generation:
    number=-1
    avgFitness=-1
    bestFitness=-1
    def __init__(self,number,avgFitness,bestFitness):
        self.number=number
        self.avgFitness=avgFitness
        self.bestFitness=bestFitness

def main(argv):
    if len(argv) != 2:
        print "incorrect use of script"
        print "please specify the ECJ stat file to read"
    else:
        generationString= "Generation: "
        averageFitnessString= "Average Fitness: "
        bestFitnessString="Fitness: Standardized="
        generationList=[]
        with open(argv[1],'r') as fin:
            for line in fin:
                if line.find(generationString) != -1:
                    generationNumber=line[len(generationString):]
                    generationNumber=generationNumber.strip()
                elif line.find(averageFitnessString) != -1:
                    averageFitness=line[len(averageFitnessString):]
                    averageFitness=averageFitness.strip()
                elif line.find(bestFitnessString) != -1:
                    line=line[len(bestFitnessString):]
                    lineItems=line.split()
                    bestFitness=lineItems[0]
                    g=Generation(generationNumber,averageFitness,bestFitness)
                    generationList.append(g)
        avgFitList=[550000-float(g.avgFitness) for g in generationList]
        bestFitList=[550000-float(g.bestFitness) for g in generationList]
        line1,=matplotlib.pyplot.plot(avgFitList, color='r',label="Average Fitness")
        line2,=matplotlib.pyplot.plot(bestFitList, color='b',label="Best Fitness")
        matplotlib.pyplot.ylabel("FitnessScore")
        matplotlib.pyplot.xlabel("Generations")
        matplotlib.pyplot.title("Fitness Convergence")
        matplotlib.pyplot.legend([line1,line2],["Average Fitness","Best Fitness"])
        matplotlib.pyplot.show()

if __name__=="__main__":
    main(sys.argv)
