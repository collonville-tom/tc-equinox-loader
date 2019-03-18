package org.tc.osgi.bundle.manager.core.wrapper;

import org.osgi.framework.Bundle;

public class BundleWrapperShortDescription {

    private String bundleDetails;

    public BundleWrapperShortDescription(final Bundle bundle) {
        wrap(bundle);
    }

    public String getBundleDetails() {
        return bundleDetails;
    }

    public void setBundleDetails(final String bundleDetails) {
        this.bundleDetails = bundleDetails;
    }

    private void wrap(final Bundle bundle) {
        final StringBuilder b = new StringBuilder("(");
        b.append(bundle.getBundleId()).append(":");
        b.append(BundleStateEnum.detect(bundle.getState()).toString()).append(":");
        b.append(bundle.getSymbolicName()).append(")");
        bundleDetails = b.toString();
    }

}
