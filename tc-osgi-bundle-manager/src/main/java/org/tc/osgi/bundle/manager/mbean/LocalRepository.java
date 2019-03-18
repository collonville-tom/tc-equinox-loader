package org.tc.osgi.bundle.manager.mbean;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import org.tc.osgi.bundle.manager.conf.ManagerPropertyFile;
import org.tc.osgi.bundle.manager.core.AbstractRepository;
import org.tc.osgi.bundle.manager.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.manager.tools.RepoParser;

public class LocalRepository extends AbstractRepository {

    public LocalRepository(final String name, final String url) {
        super(name, url);
    }

    @Override
    public void fetch() {
        try {
            final List<Path> paths = Files.list(new File(getRepositoryUrl() + "/" + getRepositoryName()).toPath()).collect(Collectors
                .toList());
            final String file = ManagerPropertyFile.getInstance().getWorkDirectory() + "/" + getRepositoryName() + "/" + ManagerPropertyFile
                .getInstance().getStaticRepositoryFile();
            final FileWriter writer = new FileWriter(new File(file));
            for (final Path p : paths) {
                if (!p.endsWith(ManagerPropertyFile.getInstance().getStaticRepositoryFile())) {
                    writer.write(p.toString().replace("/var/equinox-loader-manager/local", "."));
                    writer.write("\n");
                }
            }
            writer.close();

            final RepoParser parseur = new RepoParser();
            setBundles(parseur.parseRepoList(file));
        } catch (final Exception e) {
            LoggerServiceProxy.getInstance().getLogger(LocalRepository.class).error("Erreur while fetching local repository", e);
        }

    }

    @Override
    public void pull(final String bundle, final String version) {
        // TODO Auto-generated method stub

    }

}
