package org.tc.osgi.equinox.loader.cmd.context;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.utils.conf.XMLPropertyFile;
import org.tc.osgi.bundle.utils.context.BundleStarter;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.logger.LoggerGestionnary;
import org.tc.osgi.equinox.loader.cmd.exception.EquinoxCmdException;
import org.tc.osgi.equinox.loader.conf.EquinoxPropertyFile;
import org.tc.osgi.equinox.loader.conf.exception.EquinoxConfigException;

/**
 * StartDefaultBundleCmd.java.
 * @author collonville thomas
 * @version 0.1.4
 */
public class StartDefaultBundleCmd extends AbstractBundleContextCmd {

    private String managerDependencyBundleName;
    private String managerDependencyBundleVersion;

    private String utilsDependencyBundleName;
    private String utilsDependencyBundleVersion;

    /**
     * StartDefaultBundleCmd constructor.
     * @param context BundleContext
     */
    public StartDefaultBundleCmd(final BundleContext context) {
        super(context);
    }

    /**
     * @throws EquinoxCmdException
     * @see org.tc.osgi.equinox.loader.cmd.AbstractEquinoxCmd#execute()
     */
    @Override
    public void execute() throws EquinoxCmdException {
        try {
            new BundleStarter().processOnBundle(context, getUtilsDependencyBundleName(), getUtilsDependencyBundleVersion());

        } catch (final TcOsgiException e) {
            throw new EquinoxCmdException(e.getMessage(), e);
        }

        try {
            new BundleStarter().processOnBundle(context, getManagerDependencyBundleName(), getManagerDependencyBundleVersion());

        } catch (final TcOsgiException e) {
            LoggerGestionnary.getInstance(StartDefaultBundleCmd.class).error("Lancement auto du bundle echoué :"
                + managerDependencyBundleName + " ce dernier est peut etre simplement absent", e);
        }

    }

    public String getManagerDependencyBundleName() throws FieldTrackingAssignementException, EquinoxConfigException, EquinoxCmdException {
        if (managerDependencyBundleName == null) {
            XMLPropertyFile.getInstance(EquinoxPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "managerDependencyBundleName");
        }
        return managerDependencyBundleName;
    }

    public String getManagerDependencyBundleVersion() throws FieldTrackingAssignementException, EquinoxConfigException,
        EquinoxCmdException {
        if (managerDependencyBundleVersion == null) {
            XMLPropertyFile.getInstance(EquinoxPropertyFile.getInstance().getXMLFile()).fieldTraking(this,
                "managerDependencyBundleVersion");
        }
        return managerDependencyBundleVersion;
    }

    public String getUtilsDependencyBundleName() throws FieldTrackingAssignementException, EquinoxConfigException, EquinoxCmdException {
        if (utilsDependencyBundleName == null) {
            XMLPropertyFile.getInstance(EquinoxPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "utilsDependencyBundleName");
        }
        return utilsDependencyBundleName;
    }

    public String getUtilsDependencyBundleVersion() throws FieldTrackingAssignementException, EquinoxConfigException, EquinoxCmdException {
        if (utilsDependencyBundleVersion == null) {
            XMLPropertyFile.getInstance(EquinoxPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "utilsDependencyBundleVersion");
        }
        return utilsDependencyBundleVersion;
    }

}
