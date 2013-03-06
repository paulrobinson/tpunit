package org.jboss.narayana.performance.tpunit;

/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public class Result {

    double throughputPerSecond;
    int threads;

    public Result(double throughputPerSecond, int threads) {

        this.throughputPerSecond = throughputPerSecond;
        this.threads = threads;
    }

    public double getThroughputPerSecond() {

        return throughputPerSecond;
    }

    public int getThreads() {

        return threads;
    }
}
