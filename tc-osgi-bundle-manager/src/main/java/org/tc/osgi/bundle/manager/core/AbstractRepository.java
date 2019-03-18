package org.tc.osgi.bundle.manager.core;

import java.util.ArrayList;
import java.util.List;

import org.tc.osgi.bundle.manager.core.bundle.ITarGzBundle;
import org.tc.osgi.bundle.manager.mbean.RepositoryMBean;

public abstract class AbstractRepository implements RepositoryMBean {

    private List<ITarGzBundle> bundles = new ArrayList<>();
    private String repositoryName;

    private String repositoryUrl;

    protected AbstractRepository(final String name, final String url) {
        repositoryName = name;
        repositoryUrl = url;
    }

    @Override
    public abstract void fetch();

    @Override
    public List<ITarGzBundle> getBundles() {
        return bundles;
    }

    @Override
    public String getRepositoryName() {
        return repositoryName;
    }

    @Override
    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    @Override
    public abstract void pull(String bundle, String version);

    public void setBundles(final List<ITarGzBundle> bundles) {
        this.bundles = bundles;
    }

    @Override
    public void setRepositoryName(final String repositoryName) {
        this.repositoryName = repositoryName;
    }

    @Override
    public void setRepositoryUrl(final String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }
}
