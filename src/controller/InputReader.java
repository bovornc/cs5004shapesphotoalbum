package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import shapesphotoalbum.IShape;
import shapesphotoalbum.Oval;
import shapesphotoalbum.PhotoAlbumModel;
import shapesphotoalbum.Rectangle;

public class InputReader {
  private PhotoAlbumModel model;

  public InputReader(PhotoAlbumModel model, String fileName) {
    this.model = model;
    File file = new File(fileName);
    StringBuilder instructions = new StringBuilder();
    try {
      Scanner reader = new Scanner(file);
      while (reader.hasNextLine()) {
        String line = reader.nextLine();
        instructions.append(line).append("\n");
      }
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    String[] lines = instructions.toString().split("\n");
    for (int i = 0; i < lines.length; i++) {
      String[] words = lines[i].split(" ");

      if (words[0].equalsIgnoreCase("shape")) {
        try {
          model.createShape(words[1], words[2], Double.parseDouble(words[3]), Double.parseDouble(words[4]),
                  Double.parseDouble(words[5]), Double.parseDouble(words[6]), Double.parseDouble(words[7]),
                  Double.parseDouble(words[8]), Double.parseDouble(words[9]));
        }
        catch (NumberFormatException e) {
          System.out.println("Invalid shape information");
        }
      }

      else if (words[0].equalsIgnoreCase("move")) {
        try {
          model.moveShape(model.getShapeList().get(words[1]), Double.parseDouble(words[2]), Double.parseDouble(words[3]));
        }
        catch (NumberFormatException e) {
          System.out.println("Invalid move coordinate(s)");
        }
      }

      else if (words[0].equalsIgnoreCase("color")) {
        try {
          model.changeColor(model.getShapeList().get(words[1]), Double.parseDouble(words[2]),
                  Double.parseDouble(words[3]), Double.parseDouble(words[4]));
        }
        catch (NumberFormatException e) {
          System.out.println("Invalid RGB value(s)");
        }
      }

      else if (words[0].equalsIgnoreCase("resize")) {
        try {
          IShape shape = model.getShapeList().get(words[1]);
          if (shape.getType().equalsIgnoreCase("rectangle")) {
            model.changeWidth((Rectangle)shape, Double.parseDouble(words[2]));
            model.changeHeight((Rectangle)shape, Double.parseDouble(words[3]));
          }
          else if (shape.getType().equalsIgnoreCase("oval")) {
            model.changeXradius((Oval)shape, Double.parseDouble(words[2]));
            model.changeYradius((Oval)shape, Double.parseDouble(words[3]));
          }
        }
        catch (NumberFormatException e) {
          System.out.println("Invalid new dimension(s)");
        }
      }

      else if (words[0].equalsIgnoreCase("remove")) {
        model.getShapeList().remove(words[1]);
      }

      else if (words[0].equalsIgnoreCase("snapshot")) {
        String description = "";
        if (words.length > 1) {
          for (int j = 1; j < words.length; j++) {
            description += words[j];
            if (j + 1 != words.length) {
              description += " ";
            }
          }
        }
        model.snapshot(description);
      }
    }
  }
}

