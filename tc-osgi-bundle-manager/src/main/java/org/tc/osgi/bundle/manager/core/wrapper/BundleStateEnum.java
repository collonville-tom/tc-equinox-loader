package org.tc.osgi.bundle.manager.core.wrapper;

public enum BundleStateEnum {
    ACTIVE, INSTALLED, RESOLVED, STARTING, STOPPING, UNINSTALLED, UNKNOW;

    public static BundleStateEnum detect(final int value) {
        switch (value) {
            case 1:
                return UNINSTALLED;
            case 2:
                return INSTALLED;
            case 4:
                return RESOLVED;
            case 8:
                return STARTING;
            case 10:
                return STOPPING;
            case 32:
                return ACTIVE;
            default:
                return UNKNOW;
        }
    }

}
