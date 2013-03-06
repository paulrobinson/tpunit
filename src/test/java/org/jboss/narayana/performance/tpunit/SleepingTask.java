package org.jboss.narayana.performance.tpunit;

import org.jboss.narayana.performance.tpunit.Task;

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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new TestFailureRuntimeException("Test interupted");
        }
    }
}
