package shapesphotoalbum;

/**
 * The interface Shape.
 */
public interface IShape {
  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName();

  /**
   * Gets type.
   *
   * @return the type
   */
  public String getType();

  /**
   * Gets width.
   *
   * @return the width
   */
  public double getWidth();

  /**
   * Gets height.
   *
   * @return the height
   */
  public double getHeight();

  /**
   * Move shape.
   *
   * @param Xmove the xmove
   * @param Ymove the ymove
   */
  public void moveShape(double Xmove, double Ymove);

  /**
   * Scale shape.
   *
   * @param percentage the percentage
   */
  public void scaleShape(double percentage);

  /**
   * Change color.
   */
  public void changeColor(double R, double G, double B);

  public double getXposition();
  public double getYposition();
  public double getR();
  public double getG();
  public double getB();
}
