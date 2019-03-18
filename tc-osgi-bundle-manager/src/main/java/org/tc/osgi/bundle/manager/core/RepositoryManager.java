package org.tc.osgi.bundle.manager.core;

import java.util.HashMap;
import java.util.Map;

import org.tc.osgi.bundle.manager.conf.ManagerPropertyFile;
import org.tc.osgi.bundle.manager.mbean.LocalRepository;
import org.tc.osgi.bundle.manager.mbean.RemoteRepository;

public class RepositoryManager {

    public static final String DEFAULT_NAME = "default";
    private static RepositoryManager instance;

    public static final String LOCAL_NAME = "local";

    public static RepositoryManager getRepositoryManager() {
        if (RepositoryManager.instance == null) {
            RepositoryManager.instance = new RepositoryManager();
        }
        return RepositoryManager.instance;
    }

    private final LocalRepository localRepository;

    private final Map<String, RemoteRepository> repositories = new HashMap<>();

    private RepositoryManager() {
        final RemoteRepository r = new RemoteRepository(RepositoryManager.DEFAULT_NAME, ManagerPropertyFile.getInstance()
            .getStaticRepositoryUrl());
        repositories.put(RepositoryManager.DEFAULT_NAME, r);
        localRepository = new LocalRepository(RepositoryManager.LOCAL_NAME, ManagerPropertyFile.getInstance().getWorkDirectory());
    }

    public LocalRepository getLocalRepository() {
        return localRepository;
    }

    public Map<String, RemoteRepository> getRepositories() {
        return repositories;
    }
}
