package org.jboss.narayana.performance.tpunit;

/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public interface Task {

    public void setup();

    public void runOneIteration();

}
