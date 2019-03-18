package org.tc.osgi.bundle.utils.context;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.tc.osgi.bundle.utils.interf.context.IBundleCommand;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.module.service.impl.LoggerUtilsServiceImpl;

public class BundleInstaller implements IBundleCommand {

    @Override
    public void processOnBundle(final BundleContext context, final String path, final String version) throws TcOsgiException {
        try {

            LoggerUtilsServiceImpl.getInstance().getLogger(BundleInstaller.class).debug("Installation du bundle: " + path);
            context.installBundle(path);
        } catch (final BundleException e) {
            throw new TcOsgiException("Erreur d'installation " + path, e);
        }

    }

}
