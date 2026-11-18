package no.demo.maven;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo;

@Mojo(name = "hello-world")
public class HelloWorldMojo extends AbstractMojo {

    @Override
    public void execute() {
        getLog().info("Hello world");
    }
}
