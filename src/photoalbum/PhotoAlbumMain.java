package photoalbum;

import controller.InputReader;
import shapesphotoalbum.PhotoAlbumModel;
import view.GraphicalView;
import view.WebView;

public class PhotoAlbumMain {
  public static void main (String[] args) {
    String inputFile = null;
    String outputFile = null;
    String viewType = null;
    int windowWidth = 1000;
    int windowHeight = 1000;
    for (int i = 0 ; i < args.length; i++) {
      try {
        int width = Integer.parseInt(args[i]);
        int height = Integer.parseInt(args[i + 1]);
        if (width <= 0 || height <= 0) {
          System.out.println("Window dimensions must be positive");
          System.exit(1);
        }
        windowWidth = width;
        windowHeight = height;
        i += 1;
      }
      catch (NumberFormatException err) {
        switch (args[i]) {
          case "-in":
            inputFile = args[i + 1];
            i += 1;
            break;
          case "-v":
            viewType = args[i + 1];
            i += 1;
            break;
          case "-view":
            viewType = args[i + 1];
            i += 1;
            break;
          case "-out":
            outputFile = args[i + 1];
            i += 1;
            break;
        }
      }
    }
    if (inputFile == null) {
      System.out.println("Missing input file name");
      System.exit(1);
    }
    if (viewType == null) {
      System.out.println("Missing view type (graphical or web)");
      System.exit(1);
    }
    if (viewType.equalsIgnoreCase("web") && outputFile == null) {
      System.out.println("Web view requires an output file name");
      System.exit(1);
    }

    StringBuilder log = new StringBuilder();
    PhotoAlbumModel model = new PhotoAlbumModel(log);
    new InputReader(model, inputFile);

    if (viewType.equalsIgnoreCase("web")) {
      new WebView(model, inputFile, outputFile, windowWidth, windowHeight);
    }
    else {
      new GraphicalView(model, windowWidth, windowHeight);
    }
  }
}