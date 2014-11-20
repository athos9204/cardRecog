package hu.uszeged.sci.kepfeldolgozas.view;

import hu.uszeged.sci.kepfeldolgozas.Main;
import hu.uszeged.sci.kepfeldolgozas.tools.RecogniseCard;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Gui extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7162958531133238631L;
	private static final int imageWidth = 250;
	private static final int imageHeight = 363;
	private static final int windowWidth = 300;
	private static final int windowHeight = 580;
	private JButton openButton;
	private JButton recogniseButton;
	private JFileChooser fc;
	private JLabel imageLbl;
	private JLabel textLabel;
	private JScrollPane scrollPane;
	private JTextArea resultField;
	private File inputFile;

	public Gui() {
		super(new FlowLayout());

		fc = new JFileChooser();
		fc.setSelectedFile(new File("resources/PNG-cards-1.3").getAbsoluteFile());
		openButton = new JButton("Open image...");
		openButton.addActionListener(this);

		recogniseButton = new JButton("Recognise!");
		recogniseButton.addActionListener(this);
		recogniseButton.setEnabled(false);
		imageLbl = new JLabel();
		imageLbl.setSize(imageWidth, imageHeight);

		textLabel = new JLabel("Recognised card:");

		resultField = new JTextArea(6, 15);
		resultField.setEditable(false);
		scrollPane = new JScrollPane(resultField, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setSize(400, 70);
		
		add(openButton);
		add(recogniseButton);
		add(imageLbl);
		add(textLabel);
		add(scrollPane);

		loadIcon(loadImageFromFile(new File("resources/no-available-image.png")));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				inputFile = fc.getSelectedFile();
				loadIcon(loadImageFromFile(inputFile));
				recogniseButton.setEnabled(true);
			}
		} else if (e.getSource() == recogniseButton) {
			List<String> results = RecogniseCard.recogniseAll(Main.getHaarClassifiers(), inputFile);
			resultField.setText("");
			for (String string : results) {
				resultField.append(string + "\n");
			}
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public void createAndShowGUI() {
		JFrame frame = new JFrame("Card recogniser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Gui());
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		frame.pack();
		frame.setVisible(true);
	}

	private void loadIcon(BufferedImage image) {
		ImageIcon icon = new ImageIcon(resize(image, imageWidth, imageHeight));
		imageLbl.setIcon(icon);
	}

	private BufferedImage loadImageFromFile(File file) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(file);
		} catch (IOException e) {
		}
		return img;
	}

	private static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return dimg;
	}
}
