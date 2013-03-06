package org.jboss.narayana.performance.tpunit;


/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public class Bootstrap {

    public static void main (String[] args) throws Exception{

        long warmup;
        long totalDuration;
        int threads;
        String taskClassName;

        try {
            warmup = Long.valueOf(args[0]);
            totalDuration = Long.valueOf(args[1]);
            threads = Integer.valueOf(args[2]);
            taskClassName = args[3];
        } catch (Throwable e) {
            System.err.println("usage: <warmup millis> <total duration millis> <threads> <task class name>");
            return;
        }

        System.out.println("Running with params: ");
        System.out.println("warmup:" + (warmup/1000) + " secs");
        System.out.println("totalDuration: " + (totalDuration/1000) + " secs");
        System.out.println("threads: " + threads);
        System.out.println("taskClass: " + taskClassName);

        Class<Task> taskType = (Class<Task>) Class.forName(taskClassName);
        TestRunner runner = new TestRunner(warmup, totalDuration, threads, taskType);
        Result result = runner.run();

        System.out.println("Throughput: " + result.getThroughputPerSecond() + " /sec");
    }

}
