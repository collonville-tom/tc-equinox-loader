package org.tc.osgi.bundle.manager.core.wrapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import org.tc.osgi.bundle.manager.exception.TcEquinoxRegistryException;

public class BundleControlWrapper {

    public static final String ARCHI = "Architecture:";
    public static final String DEPENDS = "Depends:";
    public static final String DESCRIPTION = "Description:";
    public static final String HOMEPAGE = "Homepage:";
    public static final String MAINTENER = "Maintainer:";
    public static final String PACKAGE = "Package:";
    public static final String PRIORITY = "Priority:";
    public static final String SECTION = "Section:";
    public static final String VERSION = "Version:";
    private String architecture;
    private List<String> depends;
    private String description;
    private String homepage;
    private String maintainer;
    private String packageName;
    private String priority;
    private String section;
    private String version;

    public BundleControlWrapper(final String bundleControlFile) throws TcEquinoxRegistryException {
        try {
            extractProperties(Files.readAllLines(new File(bundleControlFile).toPath()));

        } catch (final IOException e) {
            throw new TcEquinoxRegistryException("Erreur de traitemennt du fichier de control " + bundleControlFile, e);
        }
    }

    private void extractProperties(final List<String> values) {
        for (final String s : values) {
            isPackage(s);
            isVersion(s);
            isSection(s);
            isPriority(s);
            isArchitecture(s);
            isDepends(s);
            isMaintainer(s);
            isHomePage(s);
            isDescription(s);
        }
    }

    public String getArchitecture() {
        return architecture;
    }

    public List<String> getDepends() {
        return depends;
    }

    public String getDescription() {
        return description;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPriority() {
        return priority;
    }

    public String getSection() {
        return section;
    }

    public String getVersion() {
        return version;
    }

    private void isArchitecture(final String s) {
        if (s.startsWith(BundleControlWrapper.ARCHI)) {
            architecture = s.replaceAll(BundleControlWrapper.ARCHI, "");
        }
    }

    private void isDepends(final String s) {
        if (s.startsWith(BundleControlWrapper.DEPENDS)) {
            depends = Arrays.asList(s.replaceAll(BundleControlWrapper.DEPENDS, "").split(","));
        }
    }

    private void isDescription(final String s) {
        if (s.startsWith(BundleControlWrapper.DESCRIPTION)) {
            description = s.replaceAll(BundleControlWrapper.DESCRIPTION, "");
        }
    }

    private void isHomePage(final String s) {
        if (s.startsWith(BundleControlWrapper.HOMEPAGE)) {
            homepage = s.replaceAll(BundleControlWrapper.HOMEPAGE, "");
        }

    }

    private void isMaintainer(final String s) {
        if (s.startsWith(BundleControlWrapper.MAINTENER)) {
            maintainer = s.replaceAll(BundleControlWrapper.MAINTENER, "");
        }
    }

    private void isPackage(final String s) {
        if (s.startsWith(BundleControlWrapper.PACKAGE)) {
            packageName = s.replaceAll(BundleControlWrapper.PACKAGE, "");
        }
    }

    private void isPriority(final String s) {
        if (s.startsWith(BundleControlWrapper.PRIORITY)) {
            priority = s.replaceAll(BundleControlWrapper.PRIORITY, "");
        }
    }

    private void isSection(final String s) {
        if (s.startsWith(BundleControlWrapper.SECTION)) {
            section = s.replaceAll(BundleControlWrapper.SECTION, "");
        }

    }

    private void isVersion(final String s) {
        if (s.startsWith(BundleControlWrapper.VERSION)) {
            version = s.replaceAll(BundleControlWrapper.VERSION, "");
        }
    }

    public void setArchitecture(final String architecture) {
        this.architecture = architecture;
    }

    public void setDepends(final List<String> depends) {
        this.depends = depends;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setHomepage(final String homepage) {
        this.homepage = homepage;
    }

    public void setMaintainer(final String maintainer) {
        this.maintainer = maintainer;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public void setPriority(final String priority) {
        this.priority = priority;
    }

    public void setSection(final String section) {
        this.section = section;
    }

    public void setVersion(final String version) {
        this.version = version;
    }
}
