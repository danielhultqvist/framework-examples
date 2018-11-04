package se.typedef.frameworks.springmvn.programatically.domain;

import com.google.auto.value.AutoValue;
import org.apache.commons.lang3.Validate;

@AutoValue
public abstract class LastName {

  public abstract String value();

  public static LastName create(final String value) {
    Validate.notBlank(value);
    return new AutoValue_LastName(value);
  }
}
