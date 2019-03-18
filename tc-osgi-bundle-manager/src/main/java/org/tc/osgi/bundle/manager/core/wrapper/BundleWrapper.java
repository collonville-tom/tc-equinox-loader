package org.tc.osgi.bundle.manager.core.wrapper;

import org.osgi.framework.Bundle;

public class BundleWrapper {

    private long bundleId;
    private String location;
    private int majorVersion;
    private int minorVersion;
    private int state;
    private String stateSignification;
    private String symbolicName;

    public BundleWrapper(final Bundle bundle) {
        wrap(bundle);
    }

    public long getBundleId() {
        return bundleId;
    }

    public String getLocation() {
        return location;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getState() {
        return state;
    }

    public String getStateSignification() {
        return stateSignification;
    }

    public String getSymbolicName() {
        return symbolicName;
    }

    public void setBundleId(final long bundleId) {
        this.bundleId = bundleId;
    }

    public void setLocation(final String location) {
        this.location = location;
    }

    public void setMajorVersion(final int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public void setMinorVersion(final int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public void setState(final int state) {
        this.state = state;
    }

    public void setStateSignification(final String stateSignification) {
        this.stateSignification = stateSignification;
    }

    public void setSymbolicName(final String symbolicName) {
        this.symbolicName = symbolicName;
    }

    private void wrap(final Bundle bundle) {
        bundleId = bundle.getBundleId();
        symbolicName = bundle.getSymbolicName();
        state = bundle.getState();
        stateSignification = BundleStateEnum.detect(state).toString();
        location = bundle.getLocation();
        majorVersion = bundle.getVersion().getMajor();
        minorVersion = bundle.getVersion().getMinor();
    }

}
