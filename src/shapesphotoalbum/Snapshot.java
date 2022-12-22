package shapesphotoalbum;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * A Snapshot class to store information about each snapshot.
 */
public class Snapshot {
  private Timestamp timestamp;
  private String description;
  private List<IShape> shapes;

  /**
   * Instantiates a new Snapshot.
   *
   * @param timestamp   the timestamp
   * @param description the description
   * @param shapes      a list of IShapes
   */
  public Snapshot(Timestamp timestamp, String description, List<IShape> shapes) {
    if (timestamp == null || shapes == null) {
      throw new IllegalArgumentException("Invalid input parameter(s)");
    }
    this.timestamp = timestamp;
    this.description = description;
    this.shapes = shapes;
  }

  /**
   * Gets timestamp.
   *
   * @return the timestamp
   */
  public Timestamp getTimestamp() {
    return this.timestamp;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets snapshot.
   *
   * @return list of shapes in the snapshot
   */
  public List<IShape> getSnapshot() {
    return this.shapes;
  }

  @Override
  public String toString() {
    String output = "Snapshot Timestamp: " + getTimestamp() + "\nDescription: " + getDescription()
            + "\nShape Information:\n";
    for (IShape shape : shapes) {
      output += shape.toString() + "\n";
    }
    return output;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Snapshot snapshot = (Snapshot) o;
    return timestamp.equals(snapshot.timestamp) && shapes.equals(snapshot.shapes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(timestamp, shapes);
  }
}
