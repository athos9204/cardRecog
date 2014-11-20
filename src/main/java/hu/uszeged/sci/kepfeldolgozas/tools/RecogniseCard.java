package hu.uszeged.sci.kepfeldolgozas.tools;

import hu.uszeged.sci.kepfeldolgozas.HaarClassifier;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class RecogniseCard {
	private static final Logger log = Logger.getLogger(RecogniseCard.class.getName());

	public static MatOfRect recognise1Card(HaarClassifier haarClassifier, File inputImage) {
		Mat image = Highgui.imread(inputImage.getPath());
		Mat dst_image = new Mat();
		Imgproc.cvtColor(image, dst_image, Imgproc.COLOR_RGB2GRAY);
		MatOfRect cardDecetions = new MatOfRect();
		haarClassifier.getClassifier().detectMultiScale(dst_image, cardDecetions, 1.1, 20, 5, new Size(40, 58), new Size(1000, 1000));
		return cardDecetions;
	}

	public static List<String> recogniseAll(List<HaarClassifier> haarClassifiers, File inputImage) {
		List<String> result = new LinkedList<String>();
		for (HaarClassifier haarClassifier : haarClassifiers) {
			MatOfRect recognitionResult = recognise1Card(haarClassifier, inputImage);
			if (recognitionResult.toArray().length > 0) {
				result.add(haarClassifier.getDescription());
				log.info(haarClassifier.getDescription() + " recognised");
			}
		}
		if (result.size() == 0) {
			result.add("No card detected");
			log.warning("No card detected!");
		}
		return result;
	}
}
