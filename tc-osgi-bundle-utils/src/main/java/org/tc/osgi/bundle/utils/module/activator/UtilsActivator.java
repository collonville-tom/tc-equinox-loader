package org.tc.osgi.bundle.utils.module.activator;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.utils.conf.UtilsPropertyFile;
import org.tc.osgi.bundle.utils.conf.XMLPropertyFile;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.IBundleUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.ICollectionUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.ICommandRunnerUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IPropertyUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IUtilsService;
import org.tc.osgi.bundle.utils.interf.module.utils.AbstractTcOsgiActivator;
import org.tc.osgi.bundle.utils.logger.LoggerGestionnary;
import org.tc.osgi.bundle.utils.module.service.impl.BundleUtilsServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.CollectionUtilsServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.CommandRunnerUtilsServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.LoggerUtilsServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.PropertyUtilsServiceImpl;
import org.tc.osgi.bundle.utils.module.service.impl.UtilsServiceImpl;
import org.tc.osgi.bundle.utils.rmi.client.EquinoxLoaderRMIClient;

/**
 * Activator.java.
 *
 * @author Collonville Thomas
 * @version 0.0.2
 * @track SDD_BUNDLE_UTILS_100
 */
public class UtilsActivator extends AbstractTcOsgiActivator {

    /**
     * String version.
     */
    private String version;

    @Override
    protected void afterStart(final BundleContext context) throws TcOsgiException {
        LoggerGestionnary.getInstance(UtilsActivator.class).debug("UtilsService start");

        LoggerGestionnary.getInstance(UtilsActivator.class).debug("Test conso objet RMI");
        EquinoxLoaderRMIClient.getInstance().getIEquinoxLoaderBundleContext();
        LoggerGestionnary.getInstance(UtilsActivator.class).debug("Test conso objet RMI OK");

    }

    @Override
    protected void afterStop(final BundleContext context) throws TcOsgiException {
        LoggerGestionnary.getInstance(UtilsActivator.class).debug("UtilsService stop");
    }

    @Override
    protected void beforeStart(final BundleContext context) throws TcOsgiException {

    }

    @Override
    protected void beforeStop(final BundleContext context) throws TcOsgiException {
        LoggerGestionnary.getInstance(UtilsActivator.class).debug("VersionStatic:" + UtilsPropertyFile.getInstance().getVersion());
        LoggerGestionnary.getInstance(UtilsActivator.class).debug("VersionDynamic:" + getVersion());

    }

    @Override
    protected void checkInitBundleUtilsService(final BundleContext context) throws TcOsgiException {
        getIBundleUtilsService().setProxy(new BundleUtilsServiceImpl());
        getIBundleUtilsService().getInstance().registerService(IBundleUtilsService.class, getIBundleUtilsService().getInstance(), context,
            this);
    }

    @Override
    protected void detachProxys(final BundleContext context) throws TcOsgiException {
        // TODO Auto-generated method stub

    }

    @Override
    protected void detachServices(final BundleContext context) throws TcOsgiException {
        getIBundleUtilsService().getInstance().unregister(ICommandRunnerUtilsService.class, this);
        getIBundleUtilsService().getInstance().unregister(IUtilsService.class, this);
        getIBundleUtilsService().getInstance().unregister(ICollectionUtilsService.class, this);
        getIBundleUtilsService().getInstance().unregister(ILoggerUtilsService.class, this);
        getIBundleUtilsService().getInstance().unregister(IPropertyUtilsService.class, this);
        getIBundleUtilsService().getInstance().unregister(IBundleUtilsService.class, this);

    }

    /**
     * getVersion.
     * 
     * @return String
     * @throws FieldTrackingAssignementException
     */
    public String getVersion() throws FieldTrackingAssignementException {
        if (version == null) {
            XMLPropertyFile.getInstance(UtilsPropertyFile.getInstance().getXMLFile()).fieldTraking(this, "version");
        }
        return version;
    }

    @Override
    protected void initProxys(final BundleContext context) throws TcOsgiException {
        // TODO Auto-generated method stub

    }

    /**
     * activeUtilsService.
     * 
     * @param context BundleContext
     */
    @Override
    protected void initServices(final BundleContext context) {
        getIBundleUtilsService().getInstance().registerService(ILoggerUtilsService.class, new LoggerUtilsServiceImpl(), context, this);
        getIBundleUtilsService().getInstance().registerService(IUtilsService.class, new UtilsServiceImpl(), context, this);
        getIBundleUtilsService().getInstance().registerService(ICollectionUtilsService.class, new CollectionUtilsServiceImpl(), context,
            this);
        getIBundleUtilsService().getInstance().registerService(IPropertyUtilsService.class, new PropertyUtilsServiceImpl(), context, this);
        getIBundleUtilsService().getInstance().registerService(ICommandRunnerUtilsService.class, CommandRunnerUtilsServiceImpl
            .getInstance(), context, this);

    }

}
