package org.tc.osgi.bundle.utils.interf.module.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.InvalidSyntaxException;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;

public class TcOsgiProxy<T> implements InvocationHandler {

    private T proxy;
    private T service;

    private TcOsgiServiceTracker<T> tracker;

    public TcOsgiProxy(final BundleContext context, final Class<T> t) throws TcOsgiException {
        try {
            this.tracker = new TcOsgiServiceTracker<T>(context, t);

            tracker.open();
            this.service = tracker.getService();
            if (this.service != null) {
                this.proxy = (T) Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), this);
            }
        } catch (InvalidSyntaxException | BundleException e) {
            throw new TcOsgiException("TcOsgiServiceTracker not found", e);
        }
    }

    public void close() {
        this.tracker.close();
    }

    public T getInstance() {
        return this.proxy;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return method.invoke(service, args);
    }

    public void setProxy(final T proxy) {
        this.proxy = proxy;
    }

}
