package org.jboss.narayana.performance.tpunit.tasks;

import org.jboss.narayana.performance.tpunit.Task;
import org.jboss.narayana.performance.tpunit.TestFailureRuntimeException;

/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public class SleepingTask implements Task {

    @Override
    public void setup() {

    }

    @Override
    public void runOneIteration() {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new TestFailureRuntimeException("Test interupted");
        }
    }
}
