package hu.uszeged.sci.kepfeldolgozas.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Gui extends JPanel implements ActionListener {
	private static final long serialVersionUID = 7162958531133238631L;
	private static final int imageWidth = 250;
	private static final int imageHeight = 363;
	private static final int windowWidth = 300;
	private static final int windowHeight = 500;
	private JButton openButton;
	private JButton recogniseButton;
	private JFileChooser fc;
	private JLabel imageLbl;
	private JLabel textLabel;
	private JTextField resultField;

	public Gui() {
		super(new FlowLayout());

		fc = new JFileChooser();
		openButton = new JButton("Open image...");
		openButton.addActionListener(this);

		recogniseButton = new JButton("Recognise!");
		recogniseButton.addActionListener(this);
		recogniseButton.setEnabled(false);
		imageLbl = new JLabel();
		imageLbl.setSize(imageWidth, imageHeight);

		textLabel = new JLabel("Recognised card:");

		resultField = new JTextField(10);
		resultField.setEditable(false);

		add(openButton);
		add(recogniseButton);
		add(imageLbl);
		add(textLabel);
		add(resultField);

		loadIcon(loadImageFromFile(new File("resources/no-available-image.png")));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				loadIcon(loadImageFromFile(file));
				recogniseButton.setEnabled(true);
			} else if (e.getSource() == recogniseButton) {
				//TODO ide jön a felismerés meghívása
			}
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("FileChooserDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Gui());
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(windowWidth, windowHeight));
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});
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
