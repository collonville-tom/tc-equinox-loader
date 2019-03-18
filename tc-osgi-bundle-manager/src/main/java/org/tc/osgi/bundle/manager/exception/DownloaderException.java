package org.tc.osgi.bundle.manager.exception;

public class DownloaderException extends ManagerException {

    /**
     * long serialVersionUID.
     */
    private static final long serialVersionUID = 8254353715382645297L;

    public DownloaderException(final String _message) {
        super(_message);
    }

    public DownloaderException(final String _message, final Throwable _e) {
        super(_message, _e);
    }
}
