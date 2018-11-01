package se.typedef.dropwizard.domain;

import com.google.auto.value.AutoValue;
import org.apache.commons.lang3.Validate;

@AutoValue
public abstract class UserId {

  public abstract String value();

  public static UserId create(final String value) {
    Validate.notBlank(value);
    return new AutoValue_UserId(value);
  }
}
