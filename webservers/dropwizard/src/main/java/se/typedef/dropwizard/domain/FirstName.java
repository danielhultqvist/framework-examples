package se.typedef.dropwizard.domain;

import com.google.auto.value.AutoValue;
import org.apache.commons.lang3.Validate;

@AutoValue
public abstract class FirstName {

  public abstract String value();

  public static FirstName create(final String value) {
    Validate.notBlank(value);
    return new AutoValue_FirstName(value);
  }
}
