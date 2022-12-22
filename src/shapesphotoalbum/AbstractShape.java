package shapesphotoalbum;

import java.util.Arrays;
import java.util.Objects;

/**
 * AbstractShape class extended by all concrete shape classes.
 */
public abstract class AbstractShape implements IShape {
  protected String name;
  protected String type;
  protected double width;
  protected double height;
  protected double Xposition;
  protected double Yposition;
  protected double R;
  protected double G;
  protected double B;


  public AbstractShape(String name, double Xposition, double Yposition,
                       double R, double G, double B) {
    this.name = name;
    this.Xposition = Xposition;
    this.Yposition = Yposition;
    this.R = R;
    this.G = G;
    this.B = B;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType() {
    return this.type;
  }

  /**
   * Gets width.
   *
   * @return the width
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Gets height.
   *
   * @return the height
   */
  public double getHeight() {
    return this.height;
  }

  /**
   * Move shape.
   */
  public void moveShape(double newX, double newY) {
    this.Xposition = newX;
    this.Yposition = newY;
  }

  /**
   * Scales shape.
   *
   * @param percentage the percentage
   */
  public void scaleShape(double percentage) {
    double Xchange = this.getWidth() * ((percentage - 100) / 100);
    double Ychange = this.getHeight() * ((percentage - 100) / 100);
    this.width += Xchange;
    this.height += Ychange;
  }

  /**
   * Change color.
   */
  public void changeColor(double R, double G, double B) {
    if (R < 0 || R > 255 || G < 0 || G > 255 || B < 0 || B > 255) {
      throw new IllegalArgumentException("RGB values must be between 0 and 255");
    }
    this.R = R;
    this.G = G;
    this.B = B;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractShape that = (AbstractShape) o;
    return Double.compare(that.width, width) == 0 && Double.compare(that.height, height) == 0 && Double.compare(that.Xposition, Xposition) == 0 && Double.compare(that.Yposition, Yposition) == 0 && Double.compare(that.R, R) == 0 && Double.compare(that.G, G) == 0 && Double.compare(that.B, B) == 0 && name.equals(that.name) && type.equals(that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, width, height, Xposition, Yposition, R, G, B);
  }

  public double getXposition() {
    return this.Xposition;
  }

  public double getYposition() {
    return this.Yposition;
  }

  public double getR() {
    return this.R;
  }

  public double getG() {
    return this.G;
  }

  public double getB() {
    return this.B;
  }
}
