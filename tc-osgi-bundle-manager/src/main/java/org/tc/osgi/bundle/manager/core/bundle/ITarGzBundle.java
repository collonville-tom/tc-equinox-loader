package org.tc.osgi.bundle.manager.core.bundle;

public interface ITarGzBundle {

    public String getName();

    public String getUrl();

    public String getVersion();

    public void setName(String name);

    public void setUrl(String url);

    public void setVersion(String version);
}
