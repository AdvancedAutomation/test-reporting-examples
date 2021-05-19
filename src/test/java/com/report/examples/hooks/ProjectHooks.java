package com.report.examples.hooks;

import com.report.examples.client.RequestManager;
import com.report.examples.config.PivotalCfg;
import com.report.examples.context.Context;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.Date;

/**
 * Defines pre and post conditions.
 */
public class ProjectHooks {

    private final Context context;

    /**
     * Constructor for the LabelHooks.
     *
     * @param contextToSet Context to Set
     */
    public ProjectHooks(final Context contextToSet) {
        this.context = contextToSet;
    }

    /**
     * Creates project.
     */
    @Before(value = "@createProject", order = 0)
    public void createProject() throws IOException {
        String endpoint = PivotalCfg.getInstance().getBaseUrl() + "/projects";
        String name = "at-12".concat(Long.toString(new Date().getTime()));
        String body = "{\"name\":\"" + name + "\"}";
        Response response = RequestManager.post(endpoint, body);
        context.saveData(response.asString());
    }

    /**
     * Deletes project.
     */
    @After(value = "@deleteProject")
    public void deleteProject() {
        String projectId = context.getValueData("id");
        String endpoint = PivotalCfg.getInstance().getBaseUrl() + "/projects/" + projectId;
        RequestManager.delete(endpoint);
    }
}
