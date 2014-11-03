package hu.uszeged.sci.kepfeldolgozas.test;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

//teszt2
class SimpleSample {

	// static {
	// System.loadLibrary("opencv_java249");
	// }

	public static BufferedImage Mat2BufferedImage(Mat m) {
		// source:
		// http://answers.opencv.org/question/10344/opencv-java-load-image-to-gui/
		// Fastest code
		// The output can be assigned either to a BufferedImage or to an Image

		int type = BufferedImage.TYPE_BYTE_GRAY;
		if (m.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		int bufferSize = m.channels() * m.cols() * m.rows();
		byte[] b = new byte[bufferSize];
		m.get(0, 0, b); // get all the pixels
		BufferedImage image = new BufferedImage(m.cols(), m.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		System.arraycopy(b, 0, targetPixels, 0, b.length);
		return image;

	}

	public static void displayImage(Image img2) {
		// BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
		ImageIcon icon = new ImageIcon(img2);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(img2.getWidth(null) + 50, img2.getHeight(null) + 50);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		// System.loadLibrary("opencv_java244");

		// System.out.println(ClassLoader.getSystemResource("resources/haarcascade.xml").getPath());
		// ClassLoader loader = Thread.currentThread().getContextClassLoader();
//		System.out.println(new File("src/main/resources/2_of_hearts.png")
//				.getAbsolutePath());
//		System.out.println(SimpleSample.class.getResource("2_of_hearts.png"));
		// File input = new
		// File(SimpleSample.class.getResource("\\2_of_hearts.png").getPath().substring(1));
		File input = new File("src/main/resources/2_of_hearts.png");
//		File input = new File("src/main/resources/test_input.png");
		System.out.println(input.getPath());
		System.out.println(input.isFile());

		File cascadePath = new File("src/main/resources/haarcascade-inter.xml");
		System.out.println(cascadePath.getPath());
		System.out.println(cascadePath.getAbsolutePath());
		System.out.println(input.isFile());
		System.out.println(input.canRead());

		CascadeClassifier classifier = new CascadeClassifier(
				cascadePath.getPath());
		System.out.println("classifier empty: " + classifier.empty());

		Mat image = Highgui.imread(input.getPath());
		Mat dst_image = new Mat();
		Imgproc.cvtColor(image, dst_image, Imgproc.COLOR_RGB2GRAY);

		MatOfRect cardDecetions = new MatOfRect();

		classifier.detectMultiScale(dst_image, cardDecetions, 1.1, 4, 0, new Size(
				40, 58), new Size(2000, 3000));
//		 classifier.detectMultiScale(dst_image, cardDecetions);
		System.out.println(String.format("Detected %s cards",
				cardDecetions.toArray().length));
		System.out.println(cardDecetions.empty());

		for (Rect rect : cardDecetions.toArray()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
					+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}

		// Save the visualized detection.
		String filename = "cardDetection.png";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename, image);

		displayImage(Mat2BufferedImage(image));

		// Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
		// System.out.println("OpenCV Mat: " + m);
		// Mat mr1 = m.row(1);
		// mr1.setTo(new Scalar(1));
		// Mat mc5 = m.col(5);
		// mc5.setTo(new Scalar(5));
		// System.out.println("OpenCV Mat data:\n" + m.dump());
	}

}