package com.report.examples.config;

import com.report.examples.throwables.InitializationError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * PropertiesReader class.
 */
public class EnvironmentReader {

    private static final Logger LOGGER = LogManager.getLogger(EnvironmentReader.class);
    private Properties property;
    private FileReader reader;

    /**
     * Initializes an instance of properties files.
     *
     * @param propertiesPath
     */
    public EnvironmentReader(final String propertiesPath) {
        try {
            reader = new FileReader(propertiesPath);
            property = new Properties();
            property.load(reader);
        } catch (FileNotFoundException e) {
            LOGGER.error("Error when reading file");
            LOGGER.error(e.getMessage());
            throw new InitializationError("Error when reading properties files");
        } catch (IOException e) {
            LOGGER.error("Error getting properties");
            LOGGER.error(e.getMessage());
            throw new InitializationError("Error when reading properties files");
        } finally {
            closeReader();
        }
    }

    /**
     * get the BaseUrl from the file.properties.
     *
     * @return base url.
     */
    public String getBaseUrl() {
        return getEnvProperty("baseUrl");
    }

    /**
     * get the schemasPath from the file.properties.
     *
     * @return schemasPath value.
     */
    public String getSchemasPath() {
        return getEnvProperty("schemasPath");
    }

    /**
     * get the cucumberThreadCount from the file.properties.
     *
     * @return cucumberThreadCount value.
     */
    public String getCucumberThreadCount() {
        return getEnvProperty("cucumberThreadCount");
    }

    /**
     * Gets environment property.
     * @param env
     * @return property value.
     */
    protected String getEnvProperty(final String env) {
        String localProperty = System.getProperty(env);
        if (localProperty == null) {
            return this.property.getProperty(env);
        }
        return localProperty;
    }

    private void closeReader() {
        try {
            reader.close();
        } catch (IOException e) {
            LOGGER.error("Cannot close File Reader.");
        }
    }
}
