package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import shapesphotoalbum.IShape;
import shapesphotoalbum.PhotoAlbumModel;
import shapesphotoalbum.Snapshot;

public class WebView {
  private PhotoAlbumModel model;
  private String inputFile;
  private String outputFile;
  private int windowWidth;
  private int windowHeight;
  private int currentSnap;

  public WebView(PhotoAlbumModel model, String inputFile, String outputFile, int width, int height) {
    this.model = model;
    this.inputFile = inputFile;
    this.outputFile = outputFile;
    this.windowWidth = width;
    this.windowHeight = height;
    this.currentSnap = 1;

    File html = new File(outputFile);
    try {
      FileWriter writer = new FileWriter(html);
      writer.write("<!DOCTYPE html>\n" + "<html>\n" + "<body>\n"
              + "<h1>Photo Album Web View</h1>\n");
      LinkedHashMap<String, Snapshot> snapshots = this.model.getSnapshots();
      for (Snapshot snap : snapshots.values()) {
        writer.write("<div>\n" + "<h2>Snapshot " + this.currentSnap + "</h2>\n"
                + "<h3>" + snap.getTimestamp() + "</h3>\n" + "<h4> Description: "
                + snap.getDescription() + "</h4>\n"
                + "<svg width=" + this.windowWidth + " height=" + this.windowHeight + ">\n");
        List<IShape> shapes = snapshots.get("Snapshot " + this.currentSnap).getSnapshot();
        for (IShape s : shapes) {
          if (s.getType().equalsIgnoreCase("rectangle")) {
            writer.write("<rect id=" + s.getName() + " x=" + s.getXposition() + " y=" + s.getYposition()
                    + " width=" + s.getWidth() + " height=" + s.getHeight() + " fill = rgb("
                    + s.getR() + "," + s.getG() + "," + s.getB() + ")>\n" + "</rect>\n");
          }
          else {
            writer.write("<ellipse id=" + s.getName() + " cx=" + s.getXposition() + " cy=" + s.getYposition()
                    + " rx=" + s.getWidth() + " ry=" + s.getHeight() + " fill = rgb("
                    + s.getR() + "," + s.getG() + "," + s.getB() + ")>\n" + "</ellipse>\n");
          }
        }
        writer.write("</svg>\n" + "</div>\n");
        this.currentSnap += 1;
      }
      writer.write("</body>\n" + "</html>");
      writer.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
