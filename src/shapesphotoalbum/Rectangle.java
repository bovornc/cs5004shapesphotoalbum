package shapesphotoalbum;

/**
 * A Java class representing a Rectangle.
 */
public class Rectangle extends AbstractShape implements IShape {
  public Rectangle(String name, double Xposition, double Yposition, double width,
                   double height, double R, double G, double B) {
    super(name, Xposition, Yposition, R, G, B);
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid shape dimensions");
    }
    this.type = "rectangle";
    this.width = width;
    this.height = height;
  }

  /**
   * Change width.
   *
   * @param newWidth the new width
   */
  public void changeWidth(double newWidth) {
    this.width = newWidth;
  }

  /**
   * Change height.
   *
   * @param newHeight the new height
   */
  public void changeHeight(double newHeight) {
    this.height = newHeight;
  }

  @Override
  public String toString() {
    String tostring = "";
    tostring += "Name: " + this.getName() + "\n";
    tostring += "Type: " + this.getType() + "\n";
    tostring += "Min corner: (" + this.getXposition() + "," + this.getYposition() + "), ";
    tostring += "Width: " + this.getWidth() + ", Height: " + this.getHeight() + ", ";
    tostring += "Color: " + this.getR() + ", " + this.getG() + ", " + this.getB() + "\n";
    return tostring;
  }
}
