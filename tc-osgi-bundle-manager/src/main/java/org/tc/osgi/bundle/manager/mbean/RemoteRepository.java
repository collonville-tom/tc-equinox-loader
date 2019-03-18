package org.tc.osgi.bundle.manager.mbean;

import org.tc.osgi.bundle.manager.core.AbstractRepository;
import org.tc.osgi.bundle.manager.core.bundle.ITarGzBundle;
import org.tc.osgi.bundle.manager.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.manager.tools.Downloader;
import org.tc.osgi.bundle.manager.tools.RepoParser;

public class RemoteRepository extends AbstractRepository {

    public RemoteRepository(final String name, final String url) {
        super(name, url);
    }

    @Override
    public void fetch() {
        try {
            final Downloader d = new Downloader();
            LoggerServiceProxy.getInstance().getLogger(RemoteRepository.class).debug("Downloading repofile on " + getRepositoryUrl());
            final String file = d.getRepoList(this);
            final RepoParser parseur = new RepoParser();
            setBundles(parseur.parseRepoList(file));
            LoggerServiceProxy.getInstance().getLogger(RemoteRepository.class).debug("Parsing repofile done :" + file);
        } catch (final Exception e) {
            LoggerServiceProxy.getInstance().getLogger(RemoteRepository.class).error("Fetching repository " + getRepositoryName()
                + " in error", e);
        }
    }

    @Override
    public void pull(final String bundle, final String version) {
        // TODO
    }

    @Override
    public String toString() {
        final StringBuilder b = new StringBuilder("[");
        b.append(getRepositoryName()).append(",").append("url").append("]\n");
        for (final ITarGzBundle bundle : getBundles()) {
            b.append(bundle.toString()).append("\n");
        }
        return b.toString();
    }

}
