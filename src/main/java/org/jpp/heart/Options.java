// Copyright (c) Corporation for National Research Initiatives
package org.jpp.heart;

/**
 * A class with static fields for each of the settable options. The options from
 * registry and command line is copied into the fields here and the rest of
 * Jython checks these fields.
 */
public class Options {
    // Jython options. Some of these can be set from the command line
    // options, but all can be controlled through the Jython registry

    /**
     * when an exception occurs in Java code, and it is not caught, should the
     * interpreter print out the Java exception in the traceback?
     */
    public static boolean showJavaExceptions = false;

    /**
     * If true, Exceptions raised from Python code will include a Java stack
     * trace in addition to the Python traceback.  This can slow raising
     * considerably.
     */
    public static boolean includeJavaStackInExceptions = true;

    /**
     * When true, python exception raised in overridden methods will be shown on
     * stderr. This option is remarkably useful when python is used for
     * implementing CORBA server. Some CORBA servers will turn python exception
     * (say a NameError) into an anonymous user exception without any
     * stacktrace. Setting this option will show the stacktrace.
     */
    public static boolean showPythonProxyExceptions = false;

    /**
     * If true, Jython respects Java the accessibility flag for fields,
     * methods, and constructors. This means you can only access public members.
     * Set this to false to access all members by toggling the accessible flag
     * on the member.
     */
    public static boolean respectJavaAccessibility = true;

    /**
     * When false the <code>site.py</code> will not be imported. This is only
     * honored by the command line main class.
     */
    public static boolean importSite = true;

    /**
     * Set verbosity to Py.ERROR, Py.WARNING, Py.MESSAGE, Py.COMMENT, or
     * Py.DEBUG for varying levels of informative messages from Jython. Normally
     * this option is set from the command line.
     */
    public static int verbose = Py.WARNING;

    /**
     * A directory where the dynamically generated classes are written. Nothing is
     * ever read from here, it is only for debugging purposes.
     */
    public static String proxyDebugDirectory;

    /**
     * If true, Jython will use the first module found on sys.path where java
     * File.isFile() returns true. Setting this to true have no effect on
     * unix-type filesystems. On Windows/HFS+ systems setting it to true will
     * enable Jython-2.0 behaviour.
     */
    public static boolean caseok = false;

    /**
     * If true, enable truedivision for the '/' operator.
     */
    public static boolean Qnew = false;

    /** Force stdin, stdout and stderr to be unbuffered, and opened in
     * binary mode */
    public static boolean unbuffered = false;

    /** Whether -3 (py3k warnings) was enabled via the command line. */
    public static boolean py3k_warning = false;
    
    /** Whether -B (don't write bytecode on import) was enabled via the command line. */
    public static boolean dont_write_bytecode = false;

    /** Whether -E (ignore environment) was enabled via the command line. */
    public static boolean ignore_environment = false;

    //XXX: place holder, not implemented yet.
    public static boolean no_user_site = false;

    //XXX: place holder, not implemented yet.
    public static boolean no_site = false;

    //XXX: place holder
    public static int bytes_warning = 0;

    // Corresponds to -O (Python bytecode optimization), -OO (remove docstrings)
    // flags in CPython; it's not clear how Jython should expose its optimization,
    // but this is user visible as of 2.7.
    public static int optimize = 0;

    /**
     * Enable division warning. The value maps to the registry values of
     * <ul>
     * <li>old: 0</li>
     * <li>warn: 1</li>
     * <li>warnall: 2</li>
     * </ul>
     */
    public static int division_warning = 0;

    /**
     * Cache spec for the SRE_STATE code point cache. The value maps to the
     * CacheBuilderSpec string and affects how the SRE_STATE cache will behave/evict
     * cached PyBytes -> int[] code points.
     */
    public static final String sreCacheSpecDefault = "weakKeys,concurrencyLevel=4,maximumWeight=2621440,expireAfterAccess=30s";
    public static String sreCacheSpec = sreCacheSpecDefault;

    //
    // ####### END OF OPTIONS
    //

    private Options() {
        ;
    }

    private static boolean getBooleanOption(String name, boolean defaultValue) {
        String prop = PySystemState.registry.getProperty("python." + name);
        if (prop == null) {
            return defaultValue;
        }
        return prop.equalsIgnoreCase("true") || prop.equalsIgnoreCase("yes");
    }

    private static String getStringOption(String name, String defaultValue) {
        String prop = PySystemState.registry.getProperty("python." + name);
        if (prop == null) {
            return defaultValue;
        }
        return prop;
    }

    /**
     * Initialize the static fields from the registry options.
     */
    public static void setFromRegistry() {
        // Set the more unusual options
        Options.showJavaExceptions = getBooleanOption(
                "options.showJavaExceptions", Options.showJavaExceptions);

        Options.includeJavaStackInExceptions = getBooleanOption(
        	"options.includeJavaStackInExceptions", Options.includeJavaStackInExceptions);

        Options.showPythonProxyExceptions = getBooleanOption(
                "options.showPythonProxyExceptions",
                Options.showPythonProxyExceptions);

        Options.respectJavaAccessibility = getBooleanOption(
                "security.respectJavaAccessibility",
                Options.respectJavaAccessibility);

        Options.proxyDebugDirectory = getStringOption(
                "options.proxyDebugDirectory", Options.proxyDebugDirectory);

        // verbosity is more complicated:
        String prop = PySystemState.registry.getProperty("python.verbose");
        if (prop != null) {
            if (prop.equalsIgnoreCase("error")) {
                Options.verbose = Py.ERROR;
            } else if (prop.equalsIgnoreCase("warning")) {
                Options.verbose = Py.WARNING;
            } else if (prop.equalsIgnoreCase("message")) {
                Options.verbose = Py.MESSAGE;
            } else if (prop.equalsIgnoreCase("comment")) {
                Options.verbose = Py.COMMENT;
            } else if (prop.equalsIgnoreCase("debug")) {
                Options.verbose = Py.DEBUG;
            } else {
                throw Py.ValueError("Illegal verbose option setting: '" + prop
                        + "'");
            }
        }

        Options.caseok = getBooleanOption("options.caseok", Options.caseok);

        Options.Qnew = getBooleanOption("options.Qnew", Options.Qnew);

        prop = PySystemState.registry.getProperty("python.division_warning");
        if (prop != null) {
            if (prop.equalsIgnoreCase("old")) {
                Options.division_warning = 0;
            } else if (prop.equalsIgnoreCase("warn")) {
                Options.division_warning = 1;
            } else if (prop.equalsIgnoreCase("warnall")) {
                Options.division_warning = 2;
            } else {
                throw Py.ValueError("Illegal division_warning option "
                        + "setting: '" + prop + "'");
            }
        }

        Options.sreCacheSpec = getStringOption("sre.cachespec", Options.sreCacheSpec);

        Options.importSite = getBooleanOption("import.site", Options.importSite);
    }
}
