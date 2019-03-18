package org.tc.osgi.bundle.manager.core.tools;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.tc.osgi.bundle.manager.core.bundle.ITarGzBundle;
import org.tc.osgi.bundle.manager.exception.RepoParserException;
import org.tc.osgi.bundle.manager.module.service.LoggerServiceProxy;
import org.tc.osgi.bundle.manager.tools.RepoParser;
import org.tc.osgi.bundle.utils.module.service.impl.LoggerUtilsServiceImpl;

public class RepoParserTest {

    public static final String DATA = "./repositories/tc-release-local/org/tc/osgi/bundle/utils/tc-osgi-bundle-utils-interfaces/0.1.0/tc-osgi-bundle-utils-interfaces-0.1.0.tar.gz";

    public static final String DATA2 = "./Repertoire/tc-osgi-bundle-utils-interfaces-0.1.0-SNAPSHOT.tar.gz";
    public static final String TEST_FILE = "./src/test/resources/repository.list";

    @Test
    public void testLoadFile() throws RepoParserException {
        LoggerServiceProxy.getInstance().setService(new LoggerUtilsServiceImpl());
        final RepoParser parseur = new RepoParser();
        final List<ITarGzBundle> l = parseur.parseRepoList(RepoParserTest.TEST_FILE);

        Assert.assertEquals(7, l.size());

    }

    @Test
    public void testParse() {

        LoggerServiceProxy.getInstance().setService(new LoggerUtilsServiceImpl());
        final RepoParser parseur = new RepoParser();
        final ITarGzBundle b = parseur.bundleBuilder(RepoParserTest.DATA);
        Assert.assertEquals("tc-osgi-bundle-utils-interfaces", b.getName());
        Assert.assertEquals("0.1.0", b.getVersion());
        Assert.assertEquals(RepoParserTest.DATA, b.getUrl());

        final ITarGzBundle b2 = parseur.bundleBuilder(RepoParserTest.DATA2);
        Assert.assertEquals("tc-osgi-bundle-utils-interfaces", b2.getName());
        Assert.assertEquals("0.1.0-SNAPSHOT", b2.getVersion());
        Assert.assertEquals(RepoParserTest.DATA2, b2.getUrl());

    }

}
