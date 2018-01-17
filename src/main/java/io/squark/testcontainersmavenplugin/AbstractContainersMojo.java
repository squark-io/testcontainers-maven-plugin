package io.squark.testcontainersmavenplugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

abstract class AbstractContainersMojo extends AbstractMojo {

  @Parameter(required = true)
  List<ContainerDefinition> containerDefinitions;

  @Parameter(readonly = true, defaultValue = "${project.build.directory}/testcontainers")
  File tempDirectory;

  String propertiesFile = "containers.properties";

}
