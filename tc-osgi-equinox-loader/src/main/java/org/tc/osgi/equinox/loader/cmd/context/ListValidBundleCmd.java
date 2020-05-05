package org.tc.osgi.equinox.loader.cmd.context;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.utils.context.BundleLister;
import org.tc.osgi.bundle.utils.context.BundleStarter;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.equinox.loader.cmd.exception.EquinoxCmdException;

public class ListValidBundleCmd extends AbstractBundleContextCmd {

    public ListValidBundleCmd(final BundleContext context) {
        super(context);
    }

    @Override
    public void execute() throws EquinoxCmdException {
        try {
            new BundleLister().processOnBundle(this.context,null,null);

        } catch (TcOsgiException e) {
            throw new EquinoxCmdException(e.getMessage(), e);
        }
    }
}
