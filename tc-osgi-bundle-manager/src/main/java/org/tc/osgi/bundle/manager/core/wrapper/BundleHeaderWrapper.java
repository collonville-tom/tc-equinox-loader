package org.tc.osgi.bundle.manager.core.wrapper;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BundleHeaderWrapper {

    private static final String BUNDLE_NAME = "Bundle-Name";

    private Map<String, String> properties = new HashMap<>();

    public BundleHeaderWrapper(final Dictionary<String, String> headers) {

        buildProperties(headers);
    }

    private void buildProperties(final Dictionary<String, String> headers) {
        final Enumeration<String> it = headers.keys();
        for (final Enumeration<String> e = headers.keys(); e.hasMoreElements();) {
            final String key = e.nextElement();
            properties.put(key, headers.get(key));
        }
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = properties;
    }
}
