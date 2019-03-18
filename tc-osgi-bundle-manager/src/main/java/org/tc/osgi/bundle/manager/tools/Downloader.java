package org.tc.osgi.bundle.manager.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.tc.osgi.bundle.manager.conf.ManagerPropertyFile;
import org.tc.osgi.bundle.manager.exception.DownloaderException;
import org.tc.osgi.bundle.manager.mbean.RemoteRepository;
import org.tc.osgi.bundle.manager.module.service.LoggerServiceProxy;

public class Downloader {

    public Downloader() {

    }

    public String buildDstRepoFileDir(final RemoteRepository repository) {
        final StringBuilder cacheDir = new StringBuilder();
        cacheDir.append(ManagerPropertyFile.getInstance().getWorkDirectory());
        cacheDir.append("/");
        cacheDir.append(repository.getRepositoryName());
        return cacheDir.toString();
    }

    public String buildRepoCacheFile(final RemoteRepository repository) {
        final StringBuilder cacheFile = new StringBuilder();
        cacheFile.append(ManagerPropertyFile.getInstance().getWorkDirectory());
        cacheFile.append("/");
        cacheFile.append(repository.getRepositoryName());
        cacheFile.append("/");
        cacheFile.append(ManagerPropertyFile.getInstance().getStaticRepositoryFile());
        return cacheFile.toString();
    }

    public String buildRepoListFileUrl(final RemoteRepository repository) {
        final StringBuilder urlFile = new StringBuilder();
        urlFile.append(repository.getRepositoryUrl());
        urlFile.append("/");
        urlFile.append(ManagerPropertyFile.getInstance().getStaticRepositoryFile());
        return urlFile.toString();
    }

    public void downloadFile(final String url, final String file) throws DownloaderException {
        InputStream in;
        try {
            if (new File(file).exists()) {
                new File(file).delete();
            }
            LoggerServiceProxy.getInstance().getLogger(Downloader.class).debug("ouverture du stream vers " + url);
            in = new URL(url).openStream();
            Files.copy(in, Paths.get(file), StandardCopyOption.REPLACE_EXISTING);
        } catch (final IOException e) {
            throw new DownloaderException("Erreur de chargement du fichier " + file + " a l'url " + url, e);
        }

    }

    public String getRepoList(final RemoteRepository repository) throws DownloaderException {
        final String remoteRepoFile = buildRepoListFileUrl(repository);
        final String cacheDir = buildDstRepoFileDir(repository);
        final String cacheFile = buildRepoCacheFile(repository);
        try {
            if (!new File(cacheDir).exists()) {
                Files.createDirectory(new File(cacheDir).toPath());
            }
            downloadFile(remoteRepoFile, cacheFile);
            return cacheFile;
        } catch (final IOException e) {
            throw new DownloaderException("erreur lors de la creation du repertoire " + cacheDir, e);
        }

    }

}
