package org.jboss.narayana.performance.tpunit;

/**
 * @author paul.robinson@redhat.com 06/03/2013
 */
public class TestFailureRuntimeException extends RuntimeException {

    public TestFailureRuntimeException(String message) {

        super(message);
    }

    public TestFailureRuntimeException(String message, Throwable cause) {

        super(message, cause);
    }
}
