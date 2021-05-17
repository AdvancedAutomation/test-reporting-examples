package com.report.examples.config;

public final class PivotalCfg extends EnvironmentReader {

    private static final String GRADLE_FILE = "gradle.properties";
    private static final String API_TOKEN = "token";
    private static PivotalCfg environment;
    /**
     * Initializes an instance of properties files.
     */
    private PivotalCfg() {
        super(GRADLE_FILE);
    }

    /**
     * Gets a singleton instance of the PivotalEnvironment.
     * @return PivotalEnvironment instance.
     */
    public static PivotalCfg getInstance() {
        if (environment == null) {
            environment = new PivotalCfg();
        }
        return environment;
    }

    /**
     * get the token from the file.properties.
     *
     * @return token value.
     */
    public String getToken() {
        return getEnvProperty(API_TOKEN);
    }
}
