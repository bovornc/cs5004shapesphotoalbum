package shapesphotoalbum;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * A Java class to represent a new Shape Photo Album.
 */
public class PhotoAlbumModel {
  private LinkedHashMap<String, IShape> shapeList;
  private LinkedHashMap<String, Snapshot> snapshots;
  private int snapshotCount;
  private final Appendable out;

  /**
   * Instantiates a new PhotoAlbumModel.
   *
   * @param appendable the appendable
   */
  public PhotoAlbumModel(Appendable appendable) {
    if (appendable == null) {
      throw new IllegalArgumentException("Invalid appendable");
    }
    this.shapeList = new LinkedHashMap<String, IShape>();
    this.snapshots = new LinkedHashMap<String, Snapshot>();
    this.snapshotCount = 0;
    this.out = appendable;
  }

  /**
   * Gets shape list.
   *
   * @return the shape list
   */
  public LinkedHashMap<String, IShape> getShapeList() {
    return this.shapeList;
  }

  /**
   * Gets snapshots.
   *
   * @return the snapshots
   */
  public LinkedHashMap<String, Snapshot> getSnapshots() {
    return this.snapshots;
  }

  /**
   * Gets log.
   *
   * @return the log
   */
  public Appendable getLog() {
    return this.out;
  }

  public void createShape(String name, String type, double xposition, double yposition,
                            double width, double height, double R, double G, double B) {
    if (type == null || type == "") {
      throw new IllegalArgumentException("Missing shape type");
    }
    if (!type.equalsIgnoreCase("rectangle")
            && !type.equalsIgnoreCase("oval")) {
      throw new IllegalArgumentException("Shape type not supported");
    }
    if (name == null || name == "") {
      throw new IllegalArgumentException("Missing shape name");
    }
    if (R < 0 || R > 255 || G < 0 || G > 255 || B < 0 || B > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255");
    }
    if (shapeList.containsKey(name)) {
      throw new IllegalArgumentException("A shape with this name already exists");
    }

    IShape newShape;
    String add = "";
    if (type.equalsIgnoreCase("rectangle")) {
      add = "Created rectangle " + name + " with corner at ("
              + xposition + "," + yposition + "), width " + width + " and height " + height
              + " RGB values: " + R + G + B + "\n";
      //System.out.println(add);

      try {
        this.out.append(add);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      newShape = new Rectangle(name, xposition, yposition, width, height, R, G, B);
    }

    else {
      add = "Created oval " + name + " with center at ("
              + xposition + "," + yposition + "), x radius " + width + " and y radius " + height
              + " RGB values: " + R + G + B + "\n";
      //System.out.println(add);

      try {
        this.out.append(add);
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      newShape = new Oval(name, xposition, yposition, width, height, R, G, B);
    }
    shapeList.put(name, newShape);
  }

  /**
   * Move shape.
   *
   * @param shape the shape
   * @param Xmove the xmove
   * @param Ymove the ymove
   */
  public void moveShape(IShape shape, double Xmove, double Ymove) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    try {
      this.out.append(shape.getName() + " moves to (" + shape.getXposition() + Xmove + ","
              + shape.getYposition() + Ymove + ")\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    shape.moveShape(Xmove, Ymove);
    shapeList.put(shape.getName(), shape);
  }

  /**
   * Change width.
   *
   * @param shape    the shape
   * @param newWidth the new width
   */
  public void changeWidth(Rectangle shape, double newWidth) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (newWidth <= 0) {
      throw new IllegalArgumentException("New width must be positive");
    }
    try {
      this.out.append(shape.getName() + " changes width from "
              + shape.getWidth() + " to " + newWidth + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    shape.changeWidth(newWidth);
    shapeList.put(shape.getName(), shape);
  }

  /**
   * Change height.
   *
   * @param shape     the shape
   * @param newHeight the new height
   */
  public void changeHeight(Rectangle shape, double newHeight) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (newHeight <= 0) {
      throw new IllegalArgumentException("New height must be positive");
    }
    try {
      this.out.append(shape.getName() + " changes height from "
              + shape.getHeight() + " to " + newHeight + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    shape.changeHeight(newHeight);
    shapeList.put(shape.getName(), shape);
  }

  /**
   * Change xradius.
   *
   * @param shape      the shape
   * @param newXradius the new xradius
   */
  public void changeXradius(Oval shape, double newXradius) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (newXradius <= 0) {
      throw new IllegalArgumentException("New X radius must be positive");
    }
    try {
      this.out.append(shape.getName() + " changes X radius from "
              + shape.getXradius() + " to " + newXradius +"\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    shape.changeXradius(newXradius);
    shapeList.put(shape.getName(), shape);
  }

  /**
   * Change yradius.
   *
   * @param shape      the shape
   * @param newYradius the new yradius
   */
  public void changeYradius(Oval shape, double newYradius) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (newYradius <= 0) {
      throw new IllegalArgumentException("New Y radius must be positive");
    }
    try {
      this.out.append(shape.getName() + " changes Y radius from "
              + shape.getYradius() + " to " + newYradius +"\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    shape.changeYradius(newYradius);
    shapeList.put(shape.getName(), shape);
  }

  /**
   * Scales the shape to a certain percentage.
   * For example, if percentage == 200, then the width and height will both be doubled.
   * If percentage == 50, then the width and height will both be halved.
   *
   * @param shape      the shape
   * @param percentage the percentage
   */
  public void scaleShape(IShape shape, double percentage) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (percentage <= 0) {
      throw new IllegalArgumentException("Percentage must be positive");
    }
    try {
      this.out.append(shape.getName() + " scaled to " + percentage + "% of previous size\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    shape.scaleShape(percentage);
    shapeList.put(shape.getName(), shape);
  }

  /**
   * Change color.
   *
   * @param shape    the shape
   */
  public void changeColor(IShape shape, double R, double G, double B) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (R < 0 || R > 255 || G < 0 || G > 255 || B < 0 || B > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255");
    }
    try {
      this.out.append(shape.getName() + " changes from " + shape.getR() + ", " + shape.getG() + ", "
              + shape.getB() + " to " + R + ", " + G + ", " + B + "\n");
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    shape.changeColor(R, G, B);
    shapeList.put(shape.getName(), shape);
  }

  /**
   * Deepcopy shape.
   *
   * @param shape the shape
   * @return the shape
   */
  public IShape deepcopy(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Invalid shape");
    }
    if (shape.getType().equalsIgnoreCase("oval")) {
      return new Oval(shape.getName(), shape.getXposition(), shape.getYposition(), shape.getWidth(),
              shape.getHeight(), shape.getR(), shape.getG(), shape.getB());
    }
    return new Rectangle(shape.getName(), shape.getXposition(), shape.getYposition(), shape.getWidth(),
            shape.getHeight(), shape.getR(), shape.getG(), shape.getB());
  }

  /**
   * Takes a snapshot of the shapes as they currently are.
   *
   * @param description snapshot description
   */
  public void snapshot(String description) {
    if (description == null) {
      description = "";
    }
    try {
      List<IShape> snapshotShapes = new ArrayList<>();
      for (IShape shape : shapeList.values()) {
        //System.out.println(shape);
        snapshotShapes.add(deepcopy(shape));
      }
      snapshotCount += 1;
      String ID = "Snapshot " + snapshotCount;
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
      this.out.append("Took a snapshot at " + timestamp + ". Snapshot ID: " + ID + "\n");
      Snapshot snapshot = new Snapshot(timestamp, description, snapshotShapes);
      snapshots.put(ID, snapshot);
      //System.out.println("Took a snapshot at " + timestamp + ". Snapshot ID: " + ID + "\n"
              //+ "Description: " + description);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Appends each snapshot to the Appendable.
   */
  public void printSnapshots() {
    for (Snapshot s : snapshots.values()) {
      try {
        this.out.append(s.toString() + "\n");
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
