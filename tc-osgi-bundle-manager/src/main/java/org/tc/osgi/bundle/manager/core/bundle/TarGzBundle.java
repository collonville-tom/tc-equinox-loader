package org.tc.osgi.bundle.manager.core.bundle;

public class TarGzBundle implements ITarGzBundle {

    private String name;
    private String url;
    private String version;

    public TarGzBundle(final String name, final String version, final String url) {
        this.name = name;
        this.url = url;
        this.version = version;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public void setUrl(final String url) {
        this.url = url;
    }

    @Override
    public void setVersion(final String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder(name);
        b.append("(").append(version).append("):");
        return b.append(url).toString();
    }
}
