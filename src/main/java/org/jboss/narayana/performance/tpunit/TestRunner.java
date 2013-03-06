package org.jboss.narayana.performance.tpunit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public class TestRunner {

    private List<TaskThread> tasks;
    private long warmup;
    private long testDuration;
    private int threads;
    private Class<? extends Task> taskType;


    public TestRunner(long warmup, long totalDuration, int threads, Class<? extends Task> taskType) {

        this.warmup = warmup;
        this.testDuration = totalDuration - warmup;
        this.threads = threads;
        this.taskType = taskType;
        tasks = new ArrayList<TaskThread>(threads);
    }

    public Result run() {

        try {

            setupThreads();
            startThreads();

            Thread.sleep(warmup);

            changePhase(Phase.RUNNING);
            Thread.sleep(testDuration);

            changePhase(Phase.STOPPING);
            waitForThreadsToComplete();

            long totalIterations = gatherIteration();
            double throughputPerSecond = (Double.valueOf(totalIterations) / testDuration) * 1000;
            return new Result(throughputPerSecond, threads);

        } catch (InterruptedException e) {
            throw new TestFailureRuntimeException("Test interupted");
        }
    }

    private void setupThreads() {

        for (int i = 0; i < threads; i++) {

            try {
                Task task = taskType.newInstance();
                TaskThread t = new TaskThread(task);
                t.setup();
                tasks.add(t);
            } catch (InstantiationException e) {
                throw new TestFailureRuntimeException("Error creating instance of " + taskType.getName());
            } catch (IllegalAccessException e) {
                throw new TestFailureRuntimeException("Error creating instance of " + taskType.getName());
            }
        }
    }

    private void startThreads() {

        for (TaskThread t : tasks) {
            t.start();
        }
    }

    private void waitForThreadsToComplete() {

        for (TaskThread t : tasks) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new TestFailureRuntimeException("Join interupted");
            }
        }
    }

    private void changePhase(Phase newPhase) {

        for (TaskThread t : tasks) {
            t.changePhase(newPhase);
        }
    }

    private int gatherIteration() {

        int total = 0;
        for (TaskThread t : tasks) {
            total += t.getIterationsCompleted();
        }
        return total;
    }
}
