package se.typedef.common;

import java.util.UUID;

public class DomainModel {

  public final String eventId;

  private DomainModel(final String eventId) {
    this.eventId = eventId;
  }

  public static DomainModel create(final String uuid) {
    return new DomainModel(uuid);
  }
}
