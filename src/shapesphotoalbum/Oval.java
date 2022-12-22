package shapesphotoalbum;

/**
 * A Java class representing an Oval.
 */
public class Oval extends AbstractShape implements IShape {
  public Oval(String name, double Xposition, double Yposition, double Xradius,
              double Yradius, double R, double G, double B) {
    super(name, Xposition, Yposition, R, G, B);
    if (Xradius <= 0 || Yradius <= 0) {
      throw new IllegalArgumentException("Invalid shape dimensions");
    }
      this.type = "oval";
      this.width = Xradius;
      this.height = Yradius;
  }

  /**
   * Gets xradius.
   *
   * @return the xradius
   */
  public double getXradius() {
    return this.width;
  }

  /**
   * Gets yradius.
   *
   * @return the yradius
   */
  public double getYradius() {
    return this.height;
  }

  /**
   * Change xradius.
   *
   * @param newX the new x
   */
  public void changeXradius(double newX) {
    this.width = newX;
  }

  /**
   * Change yradius.
   *
   * @param newY the new y
   */
  public void changeYradius(double newY) {
    this.height = newY;
  }

  @Override
  public String toString() {
    String tostring = "";
    tostring += "Name: " + this.getName() + "\n";
    tostring += "Type: " + this.getType() + "\n";
    tostring += "Center: (" + this.getXposition() + "," + this.getYposition() + "), ";
    tostring += "X radius: " + this.getXradius() + ", Y radius: " + this.getYradius() + ", ";
    tostring += "Color: " + this.getR() + ", " + this.getG() + ", " + this.getB() + "\n";
    return tostring;
  }
}
