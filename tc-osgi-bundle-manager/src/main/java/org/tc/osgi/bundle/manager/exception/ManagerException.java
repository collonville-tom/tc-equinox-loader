package org.tc.osgi.bundle.manager.exception;

import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;

public class ManagerException extends TcOsgiException {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = -7121384769012200725L;

    public ManagerException(final String _message) {
        super(_message);
    }

    public ManagerException(final String _message, final Throwable _e) {
        super(_message, _e);
    }
}