package paint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class DrawApp extends JFrame {

	private static final long serialVersionUID = 1L;

	private DrawingArea DrawingArea;
	private JButton brushButton, saveButton, clearButton, colorButton;

	private JSlider brushSize;

	
	Color brushColor = Color.BLACK;

	DrawApp(int w, int h, String title) {
		super(title);
		this.setSize(w, h);
		this.setLocation(400, 200);
		Container mainContainer = this.getContentPane();
		mainContainer.setLayout(new BorderLayout(5, 5));
		this.getRootPane().setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

		DrawingArea = new DrawingArea();

		// JSlider parameters : Min, Max, Current
		brushSize = new JSlider(0, 50, 0);

		brushButton = createButton("./images/brushicon.png");
		colorButton = createButtonColor("./images/coloricon.png");
		saveButton = createButton("./images/saveicon.png");
		clearButton = createButton("./images/clearicon.png");

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BufferedImage img = new BufferedImage(DrawingArea.getWidth(), DrawingArea.getHeight(),
						BufferedImage.TYPE_INT_RGB);
				DrawingArea.paint(img.getGraphics());
				try {
					ImageIO.write(img, "png", new File("drawing2.png"));
					System.out.println("Saved");

				} catch (Exception ex) {
					System.out.println("Not saved" + ex.getMessage());
				}
			}
		});

		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clear");
				DrawingArea.resetArea();
			}
		});

		JLabel brushSizeLabel = new JLabel("BRUSH SIZE : 1");

		brushSize.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int size = brushSize.getValue();
				DrawingArea.setBrushRadius(size);
				brushSizeLabel.setText("Brush Size : " + size);
			}
		});

		;

		;

		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(saveButton);
		topPanel.add(clearButton);
		mainContainer.add(topPanel, BorderLayout.NORTH);

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		JPanel gridPanel = new JPanel();
		// GridLayout( # rows , # cols , Horizontal Gap, Vertical Gap )
		gridPanel.setLayout(new GridLayout(1, 10, 1, 1));

		gridPanel.add(brushButton);
		gridPanel.add(colorButton);
		gridPanel.add(brushSizeLabel);
		gridPanel.add(brushSize);

		
		southPanel.add(gridPanel);
		mainContainer.add(DrawingArea);
		mainContainer.add(southPanel, BorderLayout.SOUTH);

	}

	public JButton createButton(String iconFile) {
		JButton button = new JButton();
		Icon buttonIcon = new ImageIcon(iconFile);
		button.setIcon(buttonIcon);
		return button;
	}

	// Creates the button for choosing color
	public JButton createButtonColor(String iconFile) {
		JButton button = new JButton();
		Icon buttonIcon = new ImageIcon(iconFile);
		button.setIcon(buttonIcon);

		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				brushColor = JColorChooser.showDialog(null, "Paint Color", Color.BLACK);
				DrawingArea.setbrushColor(brushColor);

			}
		});

		return button;
	}

	public static void main(String[] args) {
		DrawApp window = new DrawApp(1080, 720, "Paint");
		window.setVisible(true);
	}

}