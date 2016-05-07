import sys

def printConstants(fout):
    constantString = '''
const int SYSTEM_DEMAND=1000; //requests per second
const int HORIZON = 5;
const int MAX_DIMMER_LEVEL = 4;
const int INIT_DIMMER_LEVEL = 0;
const int MAX_TRAFFIC_LEVEL = 4;
const int INIT_TRAFFIC_LEVEL = 4;
const int MAX_SERVER_COUNT_AT_LOC = 5;
const int INIT_SERVER_COUNT_AT_LOC = 1;
const double NORMAL_PROFIT_PER_SECOND = 3.0;
const double DIMMED_PROFIT_PER_SECOND = 1.0;
const int ADD_SERVER_A_LATENCY=120;
const int ADD_SERVER_B_LATENCY=120;
const int ADD_SERVER_C_LATENCY=120;
const int ADD_SERVER_D_LATENCY=60;
const int ADD_SERVER_E_LATENCY=30;
const int ADD_SERVER_F_LATENCY=30;
const int ADD_SERVER_G_LATENCY=30;
const double ADD_SERVER_FAILURE_WEIGHT=0.1;
const int SHUTDOWN_SERVER_LATENCY=30;
const double SHUTDOWN_SERVER_FAILURE_WEIGHT=0.1;
const int INCREASE_DIMMER_LEVEL_LATENCY=1;
const double INCREASE_DIMMER_LEVEL_FAILURE_WEIGHT=0.05;
const int DECREASE_DIMMER_LEVEL_LATENCY=1;
const double DECREASE_DIMMER_LEVEL_FAILURE_WEIGHT=0.05;
const int INCREASE_TRAFFIC_LEVEL_LATENCY=1;
const double INCREASE_TRAFFIC_LEVEL_FAILURE_WEIGHT=0.05;
const int DECREASE_TRAFFIC_LEVEL_LATENCY=5;
const double DECREASE_TRAFFIC_LEVEL_FAILURE_WEIGHT=0.01;
const double SERVER_A_COST_PER_SECOND=0.5;
const int SERVER_A_HANDLED_REQUESTS_PER_SECOND=50;
const int SERVER_A_DIMMED_REQUESTS_HANDLED_PER_SECOND=150;
const double SERVER_B_COST_PER_SECOND=0.7;
const int SERVER_B_HANDLED_REQUESTS_PER_SECOND=130;
const int SERVER_B_DIMMED_REQUESTS_HANDLED_PER_SECOND=200;
const double SERVER_C_COST_PER_SECOND=1;
const int SERVER_C_HANDLED_REQUESTS_PER_SECOND=150;
const int SERVER_C_DIMMED_REQUESTS_HANDLED_PER_SECOND=300;
const double SERVER_D_COST_PER_SECOND=0.5;
const int SERVER_D_HANDLED_REQUESTS_PER_SECOND=25;
const int SERVER_D_DIMMED_REQUESTS_HANDLED_PER_SECOND=80;
const double SERVER_E_COST_PER_SECOND=0.5;
const int SERVER_E_HANDLED_REQUESTS_PER_SECOND=100;
const int SERVER_E_DIMMED_REQUESTS_HANDLED_PER_SECOND=200;
const double SERVER_F_COST_PER_SECOND=1.2;
const int SERVER_F_HANDLED_REQUESTS_PER_SECOND=250;
const int SERVER_F_DIMMED_REQUESTS_HANDLED_PER_SECOND=550;
const double SERVER_G_COST_PER_SECOND=0.5;
const int SERVER_G_HANDLED_REQUESTS_PER_SECOND=60;
const int SERVER_G_DIMMED_REQUESTS_HANDLED_PER_SECOND=180;\n'''
    fout.write(constantString)

def printActiveServers(fout, serverLocations, activeLocationCount):
    fout.write('formula active_servers = ')
    for count,loc in enumerate(serverLocations[:activeLocationCount]):
        fout.write('active_servers_%s' % (loc))
        if count < activeLocationCount - 1:
            fout.write(' + ')
        else:
            fout.write(';\n\n')

def printTotalTrafficLevel(fout, serverLocations, activeLocationCount):
    for loc in serverLocations[:activeLocationCount]:
        fout.write('formula traffic_{0} = traffic_level_{0} * active_servers_{0};'.format(loc))
    fout.write('formula total_traffic_level = ')
    for count,loc in enumerate(serverLocations[:activeLocationCount]):
        fout.write('traffic_%s' % (loc))
        if count < activeLocationCount - 1:
            fout.write(' + ')
        else:
            fout.write(';\n\n')

def printControllerVariables(fout, serverLocations, activeLocationCount):
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\tactive_servers_%s : [0..MAX_SERVER_COUNT_AT_LOC] init INIT_SERVER_COUNT_AT_LOC;\n' % (loc))
    fout.write('\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\tdimmer_level_%s : [0..MAX_DIMMER_LEVEL] init INIT_DIMMER_LEVEL;\n' % (loc))
    fout.write('\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\ttraffic_level_%s : [0..MAX_TRAFFIC_LEVEL] init INIT_TRAFFIC_LEVEL;\n' % (loc))
    fout.write('\n\tperformingAction : bool init false;\n\n')

def printControllerTactics(fout, serverLocations, activeLocationCount):
    fout.write('\t//the state doesn\'t change on failure other than failing takes up an action at the moment\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\t[addServer{0}] active_servers_{0} < MAX_SERVER_COUNT_AT_LOC & !performingAction -> 1-ADD_SERVER_FAILURE_WEIGHT : (active_servers_{0}\' = active_servers_{0} + 1)&(performingAction\'=true) + ADD_SERVER_FAILURE_WEIGHT : (performingAction\'=true);\n'.format(loc))
    fout.write('\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\t[shutdownServer{0}] active_servers > 0 & active_servers_{0} > 0 & !performingAction -> 1-SHUTDOWN_SERVER_FAILURE_WEIGHT : (active_servers_{0}\' = active_servers_{0} - 1)&(performingAction\'=true) + SHUTDOWN_SERVER_FAILURE_WEIGHT : (performingAction\'=true);\n'.format(loc))
    fout.write('\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\t[increaseDimLevel{0}] dimmer_level_{0} < MAX_DIMMER_LEVEL & !performingAction -> 1-INCREASE_DIMMER_LEVEL_FAILURE_WEIGHT : (dimmer_level_{0}\' = dimmer_level_{0} + 1)&(performingAction\'=true) + INCREASE_DIMMER_LEVEL_FAILURE_WEIGHT : (performingAction\'=true);\n'.format(loc))
    fout.write('\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\t[decreaseDimLevel{0}] dimmer_level_{0} > 0 & !performingAction -> 1 - DECREASE_DIMMER_LEVEL_FAILURE_WEIGHT : (dimmer_level_{0}\' = dimmer_level_{0} - 1)&(performingAction\'=true) + DECREASE_DIMMER_LEVEL_FAILURE_WEIGHT : (performingAction\'=true);\n'.format(loc))
    fout.write('\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\t[increaseTrafficLevel{0}] traffic_level_{0} < MAX_TRAFFIC_LEVEL & !performingAction -> 1 - INCREASE_TRAFFIC_LEVEL_FAILURE_WEIGHT : (traffic_level_{0}\' = traffic_level_{0} + 1)&(performingAction\'=true) + INCREASE_TRAFFIC_LEVEL_FAILURE_WEIGHT : (performingAction\'=true);\n'.format(loc))
    fout.write('\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('\t[decreaseTrafficLevel{0}] traffic_level_{0} > 0 & total_traffic_level > 0 & !performingAction -> 1 - DECREASE_TRAFFIC_LEVEL_FAILURE_WEIGHT : (traffic_level_{0}\' = traffic_level_{0} - 1)&(performingAction\'=true) + DECREASE_TRAFFIC_LEVEL_FAILURE_WEIGHT : (performingAction\'=true);\n'.format(loc))
    fout.write('\n')
    fout.write('\t[finishedAction] performingAction -> 1 : (performingAction\' = false);\n')

def printController(fout, serverLocations, activeLocationCount):
    fout.write('module controller\n')
    printControllerVariables(fout,serverLocations, activeLocationCount)
    printControllerTactics(fout,serverLocations, activeLocationCount)
    fout.write('endmodule\n\n')

def printStepClock(fout, serverLocations, activeLocationCount):
    fout.write('module stepClock\n')
    fout.write('\t//have to add one to HORIZON to provide an extra state to \n')
    fout.write('\t//calculate the reward at HORIZON\n')
    fout.write('\tsteps : [0..HORIZON+1] init 0;\n')
    fout.write('\t[finishedAction] (steps < HORIZON+1) -> 1 : (steps\' = steps + 1);\n')
    fout.write('endmodule\n\n')

def printFormulasForLocation(fout, serverLocations, activeLocationCount):
    fout.write('formula REQUESTS_PER_TRAFFIC_LEVEL = SYSTEM_DEMAND/(total_traffic_level);\n\n')
    for loc in serverLocations[:activeLocationCount]:
        fout.write('formula REQUESTS_SENT_TO_SERVER_{0} = traffic_{0} * REQUESTS_PER_TRAFFIC_LEVEL;\n'.format(loc))
        fout.write('formula DIMMER_FACTOR_{0} = dimmer_level_{0}/MAX_DIMMER_LEVEL;\n'.format(loc))
        fout.write('formula NORMAL_REQUESTS_HANDLED_{0} = (1 - DIMMER_FACTOR_{0})*SERVER_{0}_HANDLED_REQUESTS_PER_SECOND*active_servers_{0};\n'.format(loc))
        fout.write('formula MAX_NORMAL_PROFITS_{0} = NORMAL_REQUESTS_HANDLED_{0} * NORMAL_PROFIT_PER_SECOND;\n'.format(loc))
        fout.write('formula DIMMED_REQUESTS_HANDLED_{0} = DIMMER_FACTOR_{0}*SERVER_{0}_DIMMED_REQUESTS_HANDLED_PER_SECOND*active_servers_{0};\n'.format(loc))
        fout.write('formula MAX_DIMMED_PROFITS_{0} = DIMMED_REQUESTS_HANDLED_{0} * DIMMED_PROFIT_PER_SECOND;\n'.format(loc))
        fout.write('formula HANDLED_REQUESTS_{0} = NORMAL_REQUESTS_HANDLED_{0} + DIMMED_REQUESTS_HANDLED_{0};\n'.format(loc))
        fout.write('formula REQUESTS_OVER_NORMAL_REQUEST_RATE_{0} = REQUESTS_SENT_TO_SERVER_{0} - NORMAL_REQUESTS_HANDLED_{0};\n'.format(loc))
        fout.write('//If the request rate sent to the server is greater than or equal to the max\n')
        fout.write('//request rate the server can handle, return the max profit for that setting.\n')
        fout.write('//Otherwise, calculate profit based on the number of normal and dimmed requests\n')
        fout.write('//handled with the normal requests getting the first priority.\n')
        fout.write('formula SERVER_{0}_GROSS_INCOME = (REQUESTS_SENT_TO_SERVER_{0} >= HANDLED_REQUESTS_{0}) ?  (MAX_NORMAL_PROFITS_{0}+ MAX_DIMMED_PROFITS_{0}) : ((REQUESTS_SENT_TO_SERVER_{0} > NORMAL_REQUESTS_HANDLED_{0}) ? (MAX_NORMAL_PROFITS_{0} + REQUESTS_OVER_NORMAL_REQUEST_RATE_{0} * DIMMED_PROFIT_PER_SECOND) : (REQUESTS_SENT_TO_SERVER_{0} * NORMAL_PROFIT_PER_SECOND));\n'.format(loc))
        fout.write('formula SERVER_{0}_COST = active_servers_{0} * SERVER_{0}_COST_PER_SECOND;\n\n'.format(loc))

    fout.write('\n')
    fout.write('formula GROSS_INCOME = ')
    for count,loc in enumerate(serverLocations[:activeLocationCount]):
        fout.write('SERVER_%s_GROSS_INCOME' % (loc))
        if count != activeLocationCount - 1:
            fout.write(' + ')
        else:
            fout.write(';\n\n')
    fout.write('formula COST = ')
    for count, loc in enumerate(serverLocations[:activeLocationCount]):
        fout.write('SERVER_%s_COST' % (loc))
        if count != activeLocationCount - 1:
            fout.write(' + ')
        else:
            fout.write(';\n\n')

    fout.write('//unable to have a negative reward so all negative rewards are set 0\n')
    fout.write('formula PROFIT = (GROSS_INCOME - COST > 0 ) ? (GROSS_INCOME - COST) : 0;\n\n')
    fout.write('//goal label has to be at least one step further than\n')
    fout.write('//the state where the reward is calculated\n')
    fout.write('label "endOfPlan" = steps=HORIZON+1;\n')
    fout.write('\n')
    fout.write('rewards\n')
    fout.write('\tsteps=HORIZON : PROFIT;\n')
    fout.write('endrewards\n')

def main(argv):
    if len(argv) != 2:
        print "invalid use of script"
        print "please supply the file to write to"
    else:
        activeLocationCount=3
        serverLocations=['A','B','C','D','E','F','G']
        with open(argv[1],'w') as fout:
            fout.write('mdp\n')
            printConstants(fout)
            printActiveServers(fout,serverLocations,activeLocationCount)
            printTotalTrafficLevel(fout,serverLocations,activeLocationCount)
            printController(fout,serverLocations,activeLocationCount)
            printStepClock(fout, serverLocations, activeLocationCount)
            printFormulasForLocation(fout, serverLocations, activeLocationCount)


if __name__=="__main__":
    main(sys.argv)
