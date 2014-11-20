package hu.uszeged.sci.kepfeldolgozas;

import java.io.File;

import org.opencv.objdetect.CascadeClassifier;

public class HaarClassifier {
	private String description;
	private CascadeClassifier classifier;
	public HaarClassifier(File file) {
		description = file.getName().replace(".xml", "").replace("_", " ");
		classifier = new CascadeClassifier(file.getPath());
	}

	public String getDescription() {
		return description;
	}

	public CascadeClassifier getClassifier() {
		return classifier;
	}

	

}
