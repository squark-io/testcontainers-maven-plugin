package io.squark.testcontainersmavenplugin;

import org.apache.maven.plugins.annotations.Parameter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContainerDefinition {
  @Parameter
  private String imageName;
  @Parameter
  private List<Integer> exposedPorts = new ArrayList<>();
  @Parameter
  private Map<String, String> environment = new HashMap<>();
  @Parameter
  private List<String> aliases = new ArrayList<>();

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public List<Integer> getExposedPorts() {
    return exposedPorts;
  }

  public void setExposedPorts(List<Integer> ports) {
    this.exposedPorts = ports;
  }

  public Map<String, String> getEnvironment() {
    return environment;
  }

  public void setEnvironment(Map<String, String> environment) {
    this.environment = environment;
  }

  public List<String> getAliases() {
    return aliases;
  }

  public void setAliases(List<String> aliases) {
    this.aliases = aliases;
  }
}
