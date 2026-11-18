package no.demo.maven;

import java.util.List;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo(name = "list-coordinates", aggregator = true)
public class ProjectCoordinatesMojo extends AbstractMojo {

    @Parameter(defaultValue = "${reactorProjects}", readonly = true, required = true)
    private List<MavenProject> reactorProjects;

    @Parameter(defaultValue = "${session}", readonly = true, required = true)
    private MavenSession session;

    @Override
    public void execute() throws MojoExecutionException {
        List<MavenProject> projects = reactorProjects != null ? reactorProjects :
            (session != null ? session.getProjects() : List.of());

        if (projects.isEmpty()) {
            throw new MojoExecutionException("No pom.xml projects found in the current build.");
        }

        for (MavenProject project : projects) {
            getLog().info(coordinates(project));
        }
    }

    private String coordinates(MavenProject project) {
        String groupId = project.getGroupId() != null ? project.getGroupId() : project.getModel().getGroupId();
        String artifactId = project.getArtifactId();
        String version = project.getVersion() != null ? project.getVersion() : project.getModel().getVersion();
        return groupId + ":" + artifactId + ":" + version;
    }
}
