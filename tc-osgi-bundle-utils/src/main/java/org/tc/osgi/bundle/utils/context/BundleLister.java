package org.tc.osgi.bundle.utils.context;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.tc.osgi.bundle.utils.interf.context.IBundleCommand;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.logger.LoggerGestionnary;

/**
 * BundleStarter.java.
 *
 * @author collonville thomas
 * @version 0.2.0
 * @track SDD_BUNDLE_UTILS_125
 * @track SDD_BUNDLE_UTILS_120
 */
public class BundleLister implements IBundleCommand {


    public void listBundle(final BundleContext context) throws BundleException {
        final Bundle[] bundles = context.getBundles();

        for (int i = 0; i < bundles.length; i++) {
            String infos = "INFO bundles: "
                    + bundles[i].getSymbolicName()
                    + "(" + bundles[i].getHeaders().get(VERSION_H)
                    + "," + bundles[i].getState() + ")";

            LoggerGestionnary.getInstance(BundleLister.class).debug(infos);
        }
    }

    @Override
    public void processOnBundle(BundleContext context, String bundleName, String version) throws TcOsgiException {
        try {
            this.listBundle(context);
        } catch (BundleException e) {
            throw new TcOsgiException("Erreur lors du lancement du bundle " + bundleName, e);
        }
    }
}