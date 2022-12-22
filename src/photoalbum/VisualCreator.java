package photoalbum;

/*
public class VisualCreator {
  public static void main(String[] args) {
    SwingView view = new SwingView();
    LabelController label = new LabelController(view);
    CreateShapeController controller = new CreateShapeController(view);
    controller.go();
  }
}

class SwingView extends JFrame {
  private JButton createButton;
  private JComboBox typeInput;
  private JTextField nameInput;
  private JComboBox colorInput;
  private JLabel widthLabel;
  private JTextField widthInput;
  private JLabel heightLabel;
  private JTextField heightInput;
  private JLabel errorMessage;

  public SwingView() {
    setSize(450, 225);
    setLocation(200,200);
    setTitle("Create Shape");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(0,2));

    this.add(new JLabel("    Shape type: "));
    String[] types = {"Rectangle", "Oval"};
    typeInput = new JComboBox(types);
    this.add(typeInput);

    this.add(new JLabel("    Shape name: "));
    nameInput = new JTextField();
    this.add(nameInput);

    this.add(new JLabel("    Shape color: "));
    Color[] colors = {Color.RED, Color.GREEN, Color.BLUE};
    colorInput = new JComboBox(colors);
    this.add(colorInput);

    widthLabel = new JLabel("    Shape width: ");
    this.add(widthLabel);
    widthInput = new JTextField();
    this.add(widthInput);

    heightLabel = new JLabel("    Shape height: ");
    this.add(heightLabel);
    heightInput = new JTextField();
    this.add(heightInput);

    createButton = new JButton("Create shape");
    this.add(createButton);
    errorMessage = new JLabel("");
    this.add(errorMessage);
  }

  String getSelectedType() {
    return typeInput.getSelectedItem().toString();
  }

  String getNameInput() {
    return nameInput.getText();
  }

  Color getColorInput() {
    return (Color) colorInput.getSelectedItem();
  }

  String getWidthInput() {
    return widthInput.getText();
  }

  String getHeightInput() {
    return heightInput.getText();
  }

  void changeWidthLabel(String text) {
    widthLabel.setText(text);
  }

  void changeHeightLabel(String text) {
    heightLabel.setText(text);
  }

  void changeErrorMessage(String text) {
    errorMessage.setText(text);
  }

  public void setItemListener(ItemListener listener) {
    typeInput.addItemListener(listener);
  }

  public void setActionListener(ActionListener listener) {
    createButton.addActionListener(listener);
  }
}

class LabelController implements ItemListener {
  SwingView view;

  public LabelController(SwingView view) {
    this.view = view;
    this.view.setItemListener(this);
  }

  @Override
  public void itemStateChanged(ItemEvent e) {
    switch(this.view.getSelectedType()) {
      case "Rectangle":
        this.view.changeHeightLabel("    Shape height:");
        this.view.changeWidthLabel("    Shape width:");
        break;
      case "Oval":
        this.view.changeHeightLabel("    Shape X radius:");
        this.view.changeWidthLabel("    Shape Y radius:");
        break;
    }
  }
}

class CreateShapeController implements ActionListener {
  StringBuilder log;
  PhotoAlbumModel model;
  SwingView view;

  public CreateShapeController(SwingView view) {
    this.log = new StringBuilder();
    this.model = new PhotoAlbumModel(log);
    this.view = view;
    this.view.setActionListener(this);
  }

  public void go() {
    this.view.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    double [] testPos = new double[2];
    testPos[0] = 50;
    testPos[1] = 50;
    try {
      double width = Double.parseDouble(this.view.getWidthInput());
      double height = Double.parseDouble(this.view.getHeightInput());
      //this.model.createShape(this.view.getSelectedType(), this.view.getNameInput(),
              //this.view.getColorInput(), testPos, width, height);
      String[] lines = log.toString().split("\n");
      System.out.println(lines[lines.length - 1]);
    }
    catch (NumberFormatException error) {
      this.view.changeErrorMessage("Shape dimensions must be positive");
    }
    catch (IllegalArgumentException error) {
      this.view.changeErrorMessage("Invalid input parameters");
    }
  }
}*/