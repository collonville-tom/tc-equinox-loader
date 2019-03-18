package org.tc.osgi.bundle.manager.module.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.utils.interf.context.IBundleCommand;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.IBundleUtilsService;

/**
 * UtilsServiceImpl.java.
 *
 * @author Collonville Thomas
 * @version 0.0.5
 * @track SDD_BUNDLE_UTILS_100
 */
public class BundleUtilsServiceProxy implements IBundleUtilsService {

    /**
     * UtilsServiceProxy instance.
     */
    private static BundleUtilsServiceProxy instance = null;

    /**
     * getInstance.
     * @return UtilsServiceProxy
     */
    public static BundleUtilsServiceProxy getInstance() {
        if (BundleUtilsServiceProxy.instance == null) {
            BundleUtilsServiceProxy.instance = new BundleUtilsServiceProxy();
        }
        return BundleUtilsServiceProxy.instance;
    }

    /**
     * IUtilsService service.
     */
    private IBundleUtilsService service = null;

    /**
     * UtilsServiceProxy constructor.
     */
    private BundleUtilsServiceProxy() {

    }

    @Override
    public BundleContext getBundleContext() throws TcOsgiException {
        return service.getBundleContext();
    }

    @Override
    public IBundleCommand getBundleInstaller() {
        return service.getBundleInstaller();
    }

    @Override
    public IBundleCommand getBundleKiller() {
        return service.getBundleKiller();
    }

    @Override
    public IBundleCommand getBundleStarter() {
        return service.getBundleStarter();
    }

    @Override
    public IBundleCommand getBundleUninstaller() {
        return service.getBundleUninstaller();
    }

    @Override
    public void getClassloaderContent(final ClassLoader loader) {
        service.getClassloaderContent(loader);
    }

    @Override
    public <T> void registerService(final Class<T> _class, final T instance, final BundleContext context, final BundleActivator activator) {
        service.registerService(_class, instance, context, activator);

    }

    public void setService(final IBundleUtilsService service) {
        this.service = service;
    }

    @Override
    public void unregister(final Class _class, final BundleActivator activator) {
        service.unregister(_class, activator);
    }

}
