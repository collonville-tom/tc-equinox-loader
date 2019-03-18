package org.tc.osgi.bundle.manager.exception;

public class TcManagerMBeanException extends ManagerException {
    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 1223268730355323292L;

    public TcManagerMBeanException(final String _message) {
        super(_message);
    }

    public TcManagerMBeanException(final String _message, final Throwable _e) {
        super(_message, _e);
    }
}
