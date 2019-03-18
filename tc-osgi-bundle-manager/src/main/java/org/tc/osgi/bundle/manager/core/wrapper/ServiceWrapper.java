package org.tc.osgi.bundle.manager.core.wrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;
import org.osgi.framework.ServiceReference;

public class ServiceWrapper {

    private List<BundleWrapper> bWrapper = new ArrayList<>();
    private Map<String, Object> serviceProperties = new HashMap<>();

    public ServiceWrapper(final ServiceReference<?> service) {

        final String[] ss = service.getPropertyKeys();
        if (ss != null) {
            for (final String s : ss) {
                // LoggerGestionnary.getInstance(ServiceWrapper.class).debug("analyse
                // des propriété du service " + s);
                serviceProperties.put(s, service.getProperty(s));
            }
        }
        final Bundle[] bs = service.getUsingBundles();
        if (bs != null) {
            for (final Bundle b : bs) {
                // LoggerGestionnary.getInstance(ServiceWrapper.class).debug("analyse
                // des bundles associés au service: " + b.getSymbolicName());
                bWrapper.add(new BundleWrapper(b));
            }
        }

    }

    public List<BundleWrapper> getbWrapper() {
        return bWrapper;
    }

    public Map<String, Object> getServiceProperties() {
        return serviceProperties;
    }

    public void setbWrapper(final List<BundleWrapper> bWrapper) {
        this.bWrapper = bWrapper;
    }

    public void setServiceProperties(final Map<String, Object> serviceProperties) {
        this.serviceProperties = serviceProperties;
    }

}
