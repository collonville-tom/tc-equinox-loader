package org.tc.osgi.bundle.utils.interf.module.utils;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;
import org.tc.osgi.bundle.utils.interf.module.service.IBundleUtilsService;

public abstract class AbstractTcOsgiActivator implements BundleActivator {

    private TcOsgiProxy<IBundleUtilsService> iBundleUtilsService;

    protected abstract void afterStart(final BundleContext context) throws TcOsgiException;

    protected abstract void afterStop(final BundleContext context) throws TcOsgiException;

    protected abstract void beforeStart(final BundleContext context) throws TcOsgiException;

    protected abstract void beforeStop(final BundleContext context) throws TcOsgiException;

    protected abstract void checkInitBundleUtilsService(final BundleContext context) throws TcOsgiException;

    protected abstract void detachProxys(final BundleContext context) throws TcOsgiException;

    protected abstract void detachServices(final BundleContext context) throws TcOsgiException;

    public TcOsgiProxy<IBundleUtilsService> getIBundleUtilsService() {
        return iBundleUtilsService;
    }

    protected void initBundleUtilsService(final BundleContext context) throws TcOsgiException {
        iBundleUtilsService = new TcOsgiProxy<IBundleUtilsService>(context, IBundleUtilsService.class);

        if (iBundleUtilsService.getInstance() == null) {
            checkInitBundleUtilsService(context);
        }

    }

    protected abstract void initProxys(final BundleContext context) throws TcOsgiException;

    protected abstract void initServices(final BundleContext context) throws TcOsgiException;

    public void setiBundleUtilsService(final TcOsgiProxy<IBundleUtilsService> iBundleUtilsService) {
        this.iBundleUtilsService = iBundleUtilsService;
    }

    @Override
    public void start(final BundleContext context) throws Exception {
        initBundleUtilsService(context);
        beforeStart(context);
        initProxys(context);
        initServices(context);
        afterStart(context);
    }

    /**
     * @param context
     *            BundleContext
     * @throws Exception
     * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        beforeStop(context);
        detachServices(context);
        detachProxys(context);
        iBundleUtilsService.close();
        afterStop(context);
    }

    protected void waitSpringBundle(final long temp) {
        try {
            Thread.sleep(temp);
        } catch (final InterruptedException e) {
            // Nothing to do
        }
    }
}
