package org.tc.osgi.bundle.manager.exception;

public class TcEquinoxRegistryException extends ManagerException {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 7950737747103301107L;

    public TcEquinoxRegistryException(final String _message) {
        super(_message);
    }

    public TcEquinoxRegistryException(final String _message, final Throwable _e) {
        super(_message, _e);
    }
}
