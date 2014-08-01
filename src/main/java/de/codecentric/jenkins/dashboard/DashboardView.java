package de.codecentric.jenkins.dashboard;

import static de.codecentric.jenkins.dashboard.util.LocalMessages.DASHBOARD_VIEW_DISPLAYNAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;


import hudson.Extension;
import hudson.model.Descriptor;
import hudson.model.Item;
import hudson.model.TopLevelItem;
import hudson.model.View;
import hudson.model.ViewDescriptor;
import hudson.model.ViewGroup;
import hudson.util.FormValidation;
import jenkins.model.Jenkins;

public class DashboardView extends View {

    @Extension
    public static final DescriptorImpl DESCRIPTOR = new DescriptorImpl();

    private String artifactoryRestUri = "";
    private String username = "";
    private String password = "";


    public DashboardView(final String name) {
        super(name);
    }

    public DashboardView(final String name, final ViewGroup owner) {
        super(name, owner);
    }

    @DataBoundConstructor
    public DashboardView(final String name, final String artifactoryRestUri, final String username,
            final String password) {
        this(name);
        setArtifactoryRestUri(artifactoryRestUri);
        setUsername(username);
        setPassword(password);
    }

    @Override
    public ViewDescriptor getDescriptor() {
        return DESCRIPTOR;
    }

    /**
     * Gets all the items in this collection in a read-only view.
     */
    @Override
    public Collection<TopLevelItem> getItems() {
        return new ArrayList<TopLevelItem>();
    }

    /**
     * Checks if the job is in this collection.
     * @param item
     */
    @Override
    public boolean contains(final TopLevelItem item) {
        return false;
    }

    /**
     * Handles the configuration submission.
     * <p/>
     * Load view-specific properties here.
     * @param req
     */
    @Override
    protected void submit(final StaplerRequest req) throws IOException, ServletException, Descriptor.FormException {

    }

    /**
     * Creates a new {@link hudson.model.Item} in this collection.
     * <p/>
     * <p/>
     * This method should call {@link ModifiableItemGroup#doCreateItem(org.kohsuke.stapler.StaplerRequest, org.kohsuke.stapler.StaplerResponse)}
     * and then add the newly created item to this view.
     * @param req
     * @param rsp
     * @return null if fails.
     */
    @Override
    public Item doCreateItem(final StaplerRequest req, final StaplerResponse rsp) throws IOException, ServletException {
        return Jenkins.getInstance().doCreateItem(req, rsp);
    }

    public String getArtifactoryRestUri() {
        return artifactoryRestUri;
    }

    public void setArtifactoryRestUri(final String artifactoryRestUri) {
        this.artifactoryRestUri = artifactoryRestUri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }


    public static class DescriptorImpl extends ViewDescriptor {

        @Override
        public String getDisplayName() {
            return DASHBOARD_VIEW_DISPLAYNAME.toString();
        }

        @Override
        public String getHelpFile() {
            return "/plugin/jenkins-deployment-dashboard-plugin/help.html";
        }

        public FormValidation doCheckArtifactoryRestUri(@QueryParameter final String artifactoryRestUri) {
            FormValidation validationResult;

//            if (RestConnection.validateRestUri(artifactoryRestUri)) {
                validationResult = FormValidation.ok();
//            } else {
//                validationResult = FormValidation.error("Artifactory REST uri is not valid, cannot be empty and has to " +
//                        "start with 'http://' or 'https://'");
//            }

            return validationResult;
        }

        public FormValidation doCheckUsername(@QueryParameter final String username) {
            FormValidation validationResult;

//            if (RestConnection.validateUsername(username)) {
                validationResult = FormValidation.ok();
//            } else {
//                validationResult = FormValidation.error("Username for REST interface cannot be empty");
//            }

            return validationResult;
        }

        public FormValidation doCheckPassword(@QueryParameter final String password) {
            FormValidation validationResult;

//            if (RestConnection.validatePassword(password)) {
                validationResult = FormValidation.ok();
//            } else {
//                validationResult = FormValidation.error("Password for REST interface cannot be empty");
//            }

            return validationResult;
        }

        public FormValidation doTestArtifactoryConnection(@QueryParameter("artifactoryRestUri") final String artifactoryRestUri,
                @QueryParameter("username") final String username,
                @QueryParameter("password") final String password) {
            FormValidation validationResult;
//            RestConnection connection = new RestConnection(artifactoryRestUri, username, password, applicationName);

//            if (connection.validateConnection()) {
                validationResult = FormValidation.ok("Connection successful");
//            } else {
//                validationResult = FormValidation.warning("Connection with Artifactory RESTful interface could not be established");
//            }

            return validationResult;
        }


    }

}