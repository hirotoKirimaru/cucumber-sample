package kirimaru.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Value;

/**
 * Semantic Versioning
 */
@Value
class Version {

  public Version(String version) {
    List<Integer> collect = Arrays.stream(version.split("\\."))
        .map(Integer::parseInt)
        .collect(Collectors.toList());
    this.major = collect.get(0);
    this.minor = collect.get(1);
    this.patch = collect.get(2);
  }

  int major;
  int minor;
  int patch;
}
