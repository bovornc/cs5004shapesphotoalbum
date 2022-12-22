package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Set;

import javax.swing.*;

import shapesphotoalbum.IShape;
import shapesphotoalbum.PhotoAlbumModel;

import static java.lang.Integer.parseInt;

public class GraphicalView {
  private View view;
  private PhotoAlbumModel model;
  private int windowWidth;
  private int windowHeight;

  public GraphicalView(PhotoAlbumModel model, int windowWidth, int windowHeight) {
    this.view = new View(model, windowWidth, windowHeight);
    this.model = model;
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    view.setNextSnapshotListener(new NextSnapshot(this.model, this.view));
    view.setPrevSnapshotListener(new PrevSnapshot(this.model, this.view));
    view.setSelectSnapshotListener(new SelectSnapshot(this.model, this.view));
    this.view.setVisible(true);
  }
}

class View extends JFrame {
  private PhotoAlbumModel model;
  private JSplitPane mainPane;
  private JPanel mainPanel;
  private JPanel rightPanel;
  private JPanel buttons;
  private JButton createShape;
  private JButton nextSnapshot;
  private JButton previousSnapshot;
  private JComboBox selectSnapshot;
  private static int windowWidth;
  private static int windowHeight;
  private static int currentSnap;

  public View(PhotoAlbumModel model, int windowWidth, int windowHeight) {
    this.model = model;
    this.currentSnap = 1;
    this.windowWidth = windowWidth;
    this.windowHeight = windowHeight;
    mainPane = new JSplitPane();
    mainPanel = new JPanel();
    rightPanel = new JPanel();
    setSize(this.windowWidth, this.windowHeight);
    setLocation(250,0);
    setTitle("Photo Model Album");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new GridLayout());
    getContentPane().add(mainPane);
    mainPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
    mainPane.setDividerLocation(windowWidth - 200);
    mainPane.setTopComponent(mainPanel);
    mainPane.setRightComponent(rightPanel);

    buttons = new JPanel();
    buttons.setLayout(new GridLayout(3,1));
    Dimension pref = new Dimension(80, 30);
    nextSnapshot = new JButton("Next");
    nextSnapshot.setMaximumSize(pref);
    previousSnapshot = new JButton("Previous");
    previousSnapshot.setMaximumSize(pref);
    Set<String> keys = model.getSnapshots().keySet();
    String[] selects = keys.toArray(new String[keys.size()]);
    selectSnapshot = new JComboBox(selects);
    selectSnapshot.setMaximumSize(pref);
    buttons.add(nextSnapshot);
    buttons.add(selectSnapshot);
    buttons.add(previousSnapshot);
    rightPanel.setLayout(new GridBagLayout());
    rightPanel.add(buttons);
  }

  public void setCurrentSnap(int newSnap) throws Exception {
    if ((getCurrentSnap() + newSnap) <= 0 || (getCurrentSnap() + newSnap) > model.getSnapshots().size()) {
      throw new IllegalArgumentException("Index out of bounds");
    }
      this.currentSnap += newSnap;
  }

  public int getCurrentSnap() {
    return this.currentSnap;
  }

  public String getSelectedSnap() {
    return (String)selectSnapshot.getSelectedItem();
  }

  public void setNextSnapshotListener(ActionListener listener) {
    nextSnapshot.addActionListener(listener);
  }

  public void setPrevSnapshotListener(ActionListener listener) {
    previousSnapshot.addActionListener(listener);
  }

  public void setSelectSnapshotListener(ItemListener listener) {
    selectSnapshot.addItemListener(listener);
  }

  public void paint(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(Color.WHITE);
    g2.fillRect(0,0, this.windowWidth - 200, this.windowHeight - 200);
    List<IShape> shapes = model.getSnapshots().get("Snapshot " + getCurrentSnap()).getSnapshot();
    for (int i = 0; i < shapes.size(); i++) {
      g2.setPaint(new Color((int) shapes.get(i).getR(), (int) shapes.get(i).getG(), (int) shapes.get(i).getB()));
      if (shapes.get(i).getType().equalsIgnoreCase("rectangle")) {
        g2.fillRect((int) shapes.get(i).getXposition(), (int) shapes.get(i).getYposition(),
                (int) shapes.get(i).getWidth(), (int) shapes.get(i).getHeight());
      }
      else {
        g2.fillOval((int) shapes.get(i).getXposition(), (int) shapes.get(i).getYposition(),
                (int) shapes.get(i).getWidth(), (int) shapes.get(i).getHeight());
      }
    }
    g2.setColor(Color.WHITE);
    g2.fillRect(0, 800, 1000, 100);
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("ComicSans", Font.PLAIN, 20));
    g2.drawString(model.getSnapshots().get("Snapshot " + getCurrentSnap()).getTimestamp().toString(), 10, 825);
    g2.setFont(new Font("ComicSans", Font.PLAIN, 16));
    g2.drawString("Description: " + model.getSnapshots().get("Snapshot " + getCurrentSnap()).getDescription(), 10, 855);
  }
}

class NextSnapshot implements ActionListener {
  PhotoAlbumModel model;
  View view;

  public NextSnapshot(PhotoAlbumModel model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      view.setCurrentSnap(1);
    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Already viewing the last Snapshot.", "Message", 1);
    }
    view.repaint();
  }
}

class PrevSnapshot implements ActionListener {
  PhotoAlbumModel model;
  View view;

  public PrevSnapshot(PhotoAlbumModel model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      view.setCurrentSnap(-1);
    }
    catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Already viewing the first Snapshot.", "Message", 1);
    }
    view.repaint();
  }
}

class SelectSnapshot implements ItemListener {
  PhotoAlbumModel model;
  View view;

  public SelectSnapshot(PhotoAlbumModel model, View view) {
    this.model = model;
    this.view = view;
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    try {
      int selectedInt = parseInt(this.view.getSelectedSnap().substring(this.view.getSelectedSnap().length() - 1));
      if (selectedInt == view.getCurrentSnap()) {
        return;
      }
      view.setCurrentSnap(selectedInt - view.getCurrentSnap());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    view.repaint();
  }
}