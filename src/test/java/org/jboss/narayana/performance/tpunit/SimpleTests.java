package org.jboss.narayana.performance.tpunit;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public class SimpleTests {

    private long testDuration = 11000;
    private long warmUp = 1000;
    private int threads = 10;
    private double accuracy = 0.9;

    @Test
    public void sleepTest () {

        int expectedThroughputPerSecond = 1000;

        TestRunner runner = new TestRunner(warmUp, testDuration, threads, SleepingTask.class);
        Result result = runner.run();

        Assert.assertTrue(result.getThroughputPerSecond() > expectedThroughputPerSecond*accuracy && result.getThroughputPerSecond() <= expectedThroughputPerSecond);
    }

}
