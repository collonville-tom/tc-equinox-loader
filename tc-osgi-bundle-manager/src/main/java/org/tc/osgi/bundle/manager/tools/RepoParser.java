package org.tc.osgi.bundle.manager.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.tc.osgi.bundle.manager.core.bundle.ITarGzBundle;
import org.tc.osgi.bundle.manager.core.bundle.TarGzBundle;
import org.tc.osgi.bundle.manager.exception.RepoParserException;
import org.tc.osgi.bundle.manager.module.service.LoggerServiceProxy;

public class RepoParser {

    public static final String SNAPSHOT = "SNAPSHOT";
    private final Pattern bundlePattern = Pattern.compile(".*/(.*)-(.*).tar.gz");

    private final Pattern snapshotBundlePattern = Pattern.compile(".*/(.*)-(.*-SNAPSHOT).tar.gz");

    public RepoParser() {

    }

    // dans le repo local, les targz ne odivent pas etre indexé via leur
    // urllocal complete
    // sinon on ne peut les recuperer sur lcette base pour un autre equinox
    // il faut supprimer a l'enregistrement la partie en
    // /var/equinox-loader-manager/local et la remettre le cas echant au besoin

    public ITarGzBundle bundleBuilder(final String url) {
        LoggerServiceProxy.getInstance().getLogger(RepoParser.class).debug("Parsing " + url);
        Matcher bundleMatcher;
        if (url.contains(RepoParser.SNAPSHOT)) {
            bundleMatcher = snapshotBundlePattern.matcher(url);
        } else {
            bundleMatcher = bundlePattern.matcher(url);
        }
        bundleMatcher.find();
        return new TarGzBundle(bundleMatcher.group(1), bundleMatcher.group(2), url);
    }

    public List<ITarGzBundle> parseRepoElement(final List<String> lurls) {
        return lurls.stream().map(x -> bundleBuilder(x)).collect(Collectors.toList());
    }

    public List<ITarGzBundle> parseRepoList(final String file) throws RepoParserException {
        List<ITarGzBundle> bundles = new ArrayList<>();
        try {
            final List<String> lurls = Files.readAllLines(new File(file).toPath());
            bundles = parseRepoElement(lurls);

        } catch (final IOException e) {
            throw new RepoParserException("Erreur dans le parsing du fichier " + file, e);
        }

        return bundles;
    }

}
