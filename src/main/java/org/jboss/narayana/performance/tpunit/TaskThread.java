package org.jboss.narayana.performance.tpunit;

import java.math.BigInteger;

/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public class TaskThread extends Thread {

    private volatile Phase currentPhase = Phase.WARM_UP;

    private Task task;

    private long iterationsCompleted;

    public TaskThread(Task task) {
        this.task = task;
    }

    public void changePhase(Phase newPhase) {
        currentPhase = newPhase;
    }


    public void setup() {
        task.setup();
    }

    public long getIterationsCompleted() {

        return iterationsCompleted;
    }

    @Override
    public void run() {

        while (currentPhase == Phase.WARM_UP) {
            task.runOneIteration();
        }

        while (currentPhase == Phase.RUNNING) {
            task.runOneIteration();
            iterationsCompleted++;
        }
    }
}
