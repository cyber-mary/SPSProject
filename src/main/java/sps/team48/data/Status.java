package sps.team48.data;

/** An item on a todo list. */
public final class Status {

  private final long id;
  private final String title;
  private final long timestamp;

  public Status(long id, String title, long timestamp) {
    this.id = id;
    this.title = title;
    this.timestamp = timestamp;
  }
}