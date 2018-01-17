package io.squark.testcontainersmavenplugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.testcontainers.containers.GenericContainer;

@Mojo(name = "stop-containers")
@Execute(goal = "stop-containers", phase = LifecyclePhase.POST_INTEGRATION_TEST)
public class StopContainersMojo extends AbstractContainersMojo {

  private static Field containerIdField;

  @Override
  @SuppressWarnings("squid:S2095")
  public void execute() throws MojoExecutionException {
    File containersFile = new File(tempDirectory, propertiesFile);
    Properties properties = new Properties();
    try (FileInputStream fileInputStream = new FileInputStream(containersFile)) {
      properties.load(fileInputStream);
    } catch (IOException e) {
      throw new MojoExecutionException("Failed to read file " + containersFile.getAbsolutePath(), e);
    }
    for (String containerId : properties.stringPropertyNames()) {
      String containerImage = properties.getProperty(containerId);
      GenericContainer container = new GenericContainer(containerImage);
      setContainerId(container, containerId);
      getLog().info("Closing container " + containerImage + "[" + containerId + "]");
      container.stop();
    }
  }

  private static void setContainerId(GenericContainer container, String containerId) throws MojoExecutionException {
    try {
      if (containerIdField == null) {
        // noinspection JavaReflectionMemberAccess
        containerIdField = GenericContainer.class.getDeclaredField("containerId");
        containerIdField.setAccessible(true);
      }
      containerIdField.set(container, containerId);
    } catch (NoSuchFieldException | IllegalAccessException e) {
      throw new MojoExecutionException("Failed to set Container ID", e);
    }
  }
}
