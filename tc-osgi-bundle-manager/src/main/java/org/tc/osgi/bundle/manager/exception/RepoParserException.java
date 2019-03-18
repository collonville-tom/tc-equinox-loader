package org.tc.osgi.bundle.manager.exception;

public class RepoParserException extends ManagerException {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = -8385958235286230848L;

    public RepoParserException(final String _message) {
        super(_message);
    }

    public RepoParserException(final String _message, final Throwable _e) {
        super(_message, _e);
    }
}
