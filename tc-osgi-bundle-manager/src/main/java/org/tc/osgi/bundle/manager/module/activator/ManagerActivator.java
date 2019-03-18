package org.tc.osgi.bundle.manager.module.activator;

import java.rmi.RemoteException;

import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.manager.conf.ManagerPropertyFile;
import org.tc.osgi.bundle.manager.mbean.EquinoxRegistry;
import org.tc.osgi.bundle.manager.mbean.EquinoxRegistryMBean;
import org.tc.osgi.bundle.manager.mbean.RemoteRegistry;
import org.tc.osgi.bundle.manager.mbean.RemoteRegistryMBean;
import org.tc.osgi.bundle.manager.module.service.BundleUtilsServiceProxy;
import org.tc.osgi.bundle.manager.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.manager.module.service.PropertyServiceProxy;
import org.tc.osgi.bundle.manager.rmi.EquinoxLoaderManager;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.IBundleUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.ILoggerUtilsService;
import org.tc.osgi.bundle.utils.interf.module.service.IPropertyUtilsService;
import org.tc.osgi.bundle.utils.interf.module.utils.AbstractTcOsgiActivator;
import org.tc.osgi.bundle.utils.interf.module.utils.TcOsgiProxy;

/**
 * Activator.java.
 *
 * @author Collonville Thomas
 * @version 0.0.2
 * @track SDD_BUNDLE_UTILS_100
 *
 * http://localhost:4567/bundles/short
 * http://localhost:4567/fetchRemoteRepo
 * http://localhost:4567/updateLocal
 * http://localhost:4567/pullOnRemoteRepo/tc-osgi-bundle-berkeley-db-wrapper/5.0.73
 *
 *
 *
 */
public class ManagerActivator extends AbstractTcOsgiActivator {

    private EquinoxRegistry equinoxRegistry;
    private String groovyDependencyBundleName;
    private String groovyDependencyBundleVersion;
    private TcOsgiProxy<IBundleUtilsService> iBundleUtilsService;

    private TcOsgiProxy<ILoggerUtilsService> iLoggerUtilsService;
    private TcOsgiProxy<IPropertyUtilsService> iPropertyUtilsService;

    private EquinoxLoaderManager manager;
    private RemoteRegistry repoRegistry;

    @Override
    protected void afterStart(final BundleContext context) throws TcOsgiException {
        manager = new EquinoxLoaderManager();
        repoRegistry = new RemoteRegistry();
        equinoxRegistry = new EquinoxRegistry();
        try {
            manager.createRegistry(manager.getPort());
            manager.register(repoRegistry, RemoteRegistryMBean.class);
            manager.register(equinoxRegistry, EquinoxRegistryMBean.class);

            iBundleUtilsService.getInstance().getBundleStarter().processOnBundle(context, getGroovyDependencyBundleName(),
                getGroovyDependencyBundleVersion());
        } catch (final RemoteException e) {
            throw new TcOsgiException("Erreur d'initialisation du bundle manager", e);
        }
    }

    @Override
    protected void afterStop(final BundleContext context) throws TcOsgiException {
    }

    @Override
    protected void beforeStart(final BundleContext context) throws TcOsgiException {

    }

    @Override
    protected void beforeStop(final BundleContext context) throws TcOsgiException {
        manager.unRegister(equinoxRegistry, RemoteRegistryMBean.class);
        manager.unRegister(repoRegistry, EquinoxRegistryMBean.class);
    }

    @Override
    protected void checkInitBundleUtilsService(final BundleContext context) throws TcOsgiException {
        throw new TcOsgiException("checkInitBundleUtilsService not implemented");
    }

    @Override
    protected void detachProxys(final BundleContext context) throws TcOsgiException {
        iBundleUtilsService.close();
        iLoggerUtilsService.close();
        iPropertyUtilsService.close();

    }

    @Override
    protected void detachServices(final BundleContext context) throws TcOsgiException {

    }

    public String getGroovyDependencyBundleName() throws FieldTrackingAssignementException {
        if (groovyDependencyBundleName == null) {
            PropertyServiceProxy.getInstance().getXMLPropertyFile(ManagerPropertyFile.getInstance().getXMLFile()).fieldTraking(this,
                "groovyDependencyBundleName");
        }
        return groovyDependencyBundleName;
    }

    public String getGroovyDependencyBundleVersion() throws FieldTrackingAssignementException {
        if (groovyDependencyBundleVersion == null) {
            PropertyServiceProxy.getInstance().getXMLPropertyFile(ManagerPropertyFile.getInstance().getXMLFile()).fieldTraking(this,
                "groovyDependencyBundleVersion");
        }
        return groovyDependencyBundleVersion;
    }

    @Override
    protected void initProxys(final BundleContext context) throws TcOsgiException {
        iPropertyUtilsService = new TcOsgiProxy<IPropertyUtilsService>(context, IPropertyUtilsService.class);
        PropertyServiceProxy.getInstance().setService(iPropertyUtilsService.getInstance());
        iLoggerUtilsService = new TcOsgiProxy<ILoggerUtilsService>(context, ILoggerUtilsService.class);
        LoggerServiceProxy.getInstance().setService(iLoggerUtilsService.getInstance());
        iBundleUtilsService = new TcOsgiProxy<IBundleUtilsService>(context, IBundleUtilsService.class);
        BundleUtilsServiceProxy.getInstance().setService(iBundleUtilsService.getInstance());
    }

    /**
     * activeUtilsService.
     * 
     * @param context BundleContext
     */
    @Override
    protected void initServices(final BundleContext context) {

    }

}
