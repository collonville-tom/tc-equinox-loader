package org.tc.osgi.bundle.manager.mbean;

import java.util.List;

import org.tc.osgi.bundle.manager.core.bundle.ITarGzBundle;

public interface RepositoryMBean {

    public void fetch();

    public List<ITarGzBundle> getBundles();

    public String getRepositoryName();

    public String getRepositoryUrl();

    public void pull(String bundle, String version);

    public void setRepositoryName(String repositoryName);

    public void setRepositoryUrl(String repositoryUrl);
}
