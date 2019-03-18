package org.tc.osgi.bundle.utils.context;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.tc.osgi.bundle.utils.interf.context.IBundleCommand;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.logger.LoggerGestionnary;

public class BundleUninstaller implements IBundleCommand {

    @Override
    public void processOnBundle(final BundleContext context, final String bundleName, final String bundleVersion) throws TcOsgiException {
        final Bundle[] bundles = context.getBundles();
        for (final Bundle bundle : bundles) {
            if (bundle.getSymbolicName().startsWith(bundleName)) {
                if (bundle.getHeaders().get(IBundleCommand.VERSION_H).equals(bundleVersion)) {
                    LoggerGestionnary.getInstance(BundleUninstaller.class).debug("Uninstall bundle:" + bundle.getSymbolicName());
                    try {
                        bundle.uninstall();
                    } catch (final BundleException e) {

                        throw new TcOsgiException("Desinstallation bundle en erreur:" + bundle.getSymbolicName(), e);
                    }
                }
            }
        }

    }

}
