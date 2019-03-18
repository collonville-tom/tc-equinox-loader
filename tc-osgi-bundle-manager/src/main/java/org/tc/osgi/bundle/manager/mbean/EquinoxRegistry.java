package org.tc.osgi.bundle.manager.mbean;

import java.util.ArrayList;
import java.util.List;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.tc.osgi.bundle.manager.conf.ManagerPropertyFile;
import org.tc.osgi.bundle.manager.core.wrapper.BundleControlWrapper;
import org.tc.osgi.bundle.manager.core.wrapper.BundleHeaderWrapper;
import org.tc.osgi.bundle.manager.core.wrapper.BundleWrapper;
import org.tc.osgi.bundle.manager.core.wrapper.BundleWrapperShortDescription;
import org.tc.osgi.bundle.manager.core.wrapper.ServiceWrapper;
import org.tc.osgi.bundle.manager.exception.TcEquinoxRegistryException;
import org.tc.osgi.bundle.manager.module.service.BundleUtilsServiceProxy;
import org.tc.osgi.bundle.manager.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.manager.tools.JsonSerialiser;
import org.tc.osgi.bundle.utils.interf.conf.exception.FieldTrackingAssignementException;
import org.tc.osgi.bundle.utils.interf.exception.TcOsgiException;

// classe qui permet d'acceder au differents ffonctionnalité de manipulation des
// bundles, install, start, stop, remove
public class EquinoxRegistry implements EquinoxRegistryMBean {

    private static final String BUNDLE_CLASSIFIER = "-assembly.jar";

    /**
     * 
     */
    private static final long serialVersionUID = 1126882670873927286L;

    public EquinoxRegistry() {

    }

    @Override
    public String buildPath(final String bundleName, final String version) throws FieldTrackingAssignementException {
        final StringBuilder builder = new StringBuilder(ManagerPropertyFile.getInstance().getBundleLocalBase());
        builder.append(ManagerPropertyFile.getInstance().getBundleDirectory()).append("/");
        builder.append(bundleName).append("-").append(version);
        builder.append(EquinoxRegistry.BUNDLE_CLASSIFIER);
        return builder.toString();
    }

    @Override
    public String bundleDependencies(final String bundleName, final String version) {
        final String bundleControlFile = ManagerPropertyFile.getInstance().getBundlesDirectory() + "/" + bundleName + "-" + version
            + "/control";
        BundleControlWrapper wrapper;
        try {
            wrapper = new BundleControlWrapper(bundleControlFile);
            return new JsonSerialiser().toJson(wrapper);
        } catch (final TcEquinoxRegistryException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error(
                "Une erreur s'est produite lors de la determination des dependannces du bundle " + bundleName, e);
        }
        return "Une erreur s'est produite lors de la determination des dependannces du bundle ";
    }

    @Override
    public String bundleInfo(final String bundleName, final String version) throws TcOsgiException {
        try {
            final Bundle b = retrieveBundle(bundleName);
            final BundleHeaderWrapper wrapper = new BundleHeaderWrapper(b.getHeaders());
            return new JsonSerialiser().toJson(wrapper);
        } catch (final TcEquinoxRegistryException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error(
                "Une erreur s'est produite lors de la recuperation des services du bunddle " + bundleName, e);
        }
        return "Une erreur s'est produite lors de la recuperation des info du bunddle ";
    }

    @Override
    public String bundleInstall(final String bundleName, final String version) {
        try {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).warn("Parameter version is not used yet " + version);
            final String bundlePath = buildPath(bundleName, version);
            BundleUtilsServiceProxy.getInstance().getBundleInstaller().processOnBundle(getBundleContext(), bundlePath, version);
            return bundleName + " install";
        } catch (final TcOsgiException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error("Error in stoinstalling bundle " + bundleName, e);
        }
        return "Error in installing bundle " + bundleName;
    }

    @Override
    public String bundleList() throws TcOsgiException {
        LoggerServiceProxy.getInstance().getLogger(RemoteRegistry.class).debug("Retreive bundle list");
        final List<BundleWrapper> wrappers = new ArrayList<>();
        try {
            for (final Bundle b : getBundleContext().getBundles()) {
                wrappers.add(new BundleWrapper(b));
            }
        } catch (final Throwable e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error("Quel bordel ces saut RMI", e);
            throw new TcOsgiException("AH AH!", e);
        }
        return new JsonSerialiser().toJson(wrappers);
    }

    @Override
    public String bundleService(final String bundleName) throws TcOsgiException {
        LoggerServiceProxy.getInstance().getLogger(RemoteRegistry.class).debug("Retreive service list");
        final List<ServiceWrapper> wrapper = new ArrayList<>();
        try {
            final Bundle bundle = retrieveBundle(bundleName);
            ServiceReference<?>[] services;
            services = bundle.getRegisteredServices();
            if (services != null) {
                for (final ServiceReference<?> service : services) {
                    LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).debug("Traitement du service: " + service);
                    wrapper.add(new ServiceWrapper(service));
                }
            }

            return new JsonSerialiser().toJson(wrapper);
        } catch (final TcEquinoxRegistryException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error(
                "Une erreur s'est produite lors de la recuperation des services du bunddle " + bundleName, e);
        }
        return "Une erreur s'est produite lors de la recuperation des services du bunddle ";
    }

    @Override
    public String bundleServices() throws TcOsgiException {
        LoggerServiceProxy.getInstance().getLogger(RemoteRegistry.class).debug("Retreive services list");
        final List<ServiceWrapper> wrapper = new ArrayList<>();

        ServiceReference<?>[] services;
        try {
            services = getBundleContext().getServiceReferences((String) null, (String) null);
            if (services != null) {
                for (final ServiceReference<?> service : services) {
                    LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).debug("Traitement du service: " + service);
                    wrapper.add(new ServiceWrapper(service));
                }
            }
        } catch (final InvalidSyntaxException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error("Erreur dans le recuperation de la liste de services",
                e);
        }
        return new JsonSerialiser().toJson(wrapper);
    }

    @Override
    public String bundleShortList() throws TcOsgiException {
        LoggerServiceProxy.getInstance().getLogger(RemoteRegistry.class).debug("Retreive bundle list");
        final List<BundleWrapperShortDescription> wrappers = new ArrayList<>();
        for (final Bundle b : getBundleContext().getBundles()) {
            wrappers.add(new BundleWrapperShortDescription(b));
        }
        return new JsonSerialiser().toJson(wrappers);
    }

    @Override
    public String bundleStart(final String bundleName, final String version) {
        try {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).warn("Parameter version is not used yet " + version);
            BundleUtilsServiceProxy.getInstance().getBundleStarter().processOnBundle(getBundleContext(), bundleName, version);
            return bundleName + " started";
        } catch (final TcOsgiException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error("Error in starting bundle " + bundleName, e);
        }
        return "Error in starting bundle " + bundleName;
    }

    @Override
    public String bundleStop(final String bundleName, final String version) {
        try {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).warn("Parameter version is not used yet " + version);
            BundleUtilsServiceProxy.getInstance().getBundleKiller().processOnBundle(getBundleContext(), bundleName, version);
            return bundleName + " stoped";
        } catch (final TcOsgiException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error("Error in stoping bundle " + bundleName, e);
        }
        return "Error in stoping bundle " + bundleName;
    }

    @Override
    public String bundleUninstall(final String bundleName, final String version) {
        try {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).warn("Parameter version is not used yet " + version);
            BundleUtilsServiceProxy.getInstance().getBundleUninstaller().processOnBundle(getBundleContext(), bundleName, version);
            return bundleName + " uninstall";
        } catch (final TcOsgiException e) {
            LoggerServiceProxy.getInstance().getLogger(EquinoxRegistry.class).error("Error in unintalling bundle " + bundleName, e);
        }
        return "Error in unintalling bundle " + bundleName;
    }

    private BundleContext getBundleContext() throws TcOsgiException {
        return BundleUtilsServiceProxy.getInstance().getBundleContext();
    }

    public Bundle retrieveBundle(final String bundleName) throws TcOsgiException {
        for (final Bundle b : getBundleContext().getBundles()) {
            if (b.getSymbolicName().equals(bundleName)) {
                return b;
            }
        }
        throw new TcEquinoxRegistryException("Bundle " + bundleName + " not found");
    }

}
