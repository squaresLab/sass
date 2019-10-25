# Self-Adaption with Stochastic Search (SASS)

## How to run it

`./gradlew runRepertoire > output.csv`

This command will run the main method in the
`src/main/java/ecj/RepertoireBuilder.java` file, which is the main entry point
for the building reusable repertoires research thrust experiments.

When run, ten experiments will be run (controlled by the 'numTrials' variable in
the code). For each trial, a random unexpected change scenario is generated, and
the planner will be called to generated a new adaptation strategy. Parameters
such as the number of generations of evolution and the number of individuals in
the population may be set in the code, (currently, 30 generations and 1000
individuals). It can be useful to reduce these (say, to 10 generations) to
perform quick tinkering / prototyping, but 30 and 1000 are what we use in
production experiments.

To initialize the search, plans from the `repertoire` folder are randomly
selected to go into the initial population. A percentage of the starting
population (currently 90%) are randomly generated from scratch, the remaining
10% are chosen from the repertoire folder. (These were values identified from
the prior work, but perhaps with the semantic transforms we can use 100% from
the repertoire folder and just heavily trim 90% or something like this).

## The Output

A line of output is produced for each generation of evolution. The number of
lines of output is then the number of generations, times the number of trials.
The output will be in the following format:

`trial,generation,bestSize,runtime,profit,distance,structureDistance,plan,init,window,buildProb,runtimeKill,trimmerChance,scenario,averageSize`

From this output, we care primarily about two values, the runtime, which is the
time in milliseconds that the planner took to evaluate that generation (lower is better), and the
profit, which is the utility (higher is better). Note that the utility function
takes into account the fact that the system's utility depends on the planning
time (i.e., currently I am getting 5 utility per second, it takes me 10 seconds
to figure out a plan that will give me 10 utility per second, if I get this
utility for another 10 seconds, my total utility is 5x10 + 10x10).
