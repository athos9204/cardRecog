package hu.uszeged.sci.kepfeldolgozas;

import hu.uszeged.sci.kepfeldolgozas.tools.XMLReader;
import hu.uszeged.sci.kepfeldolgozas.view.Gui;

import java.util.List;

import org.opencv.core.Core;

public class Main {
	private static List<HaarClassifier> haarClassifiers;
	private static Gui GUI = new Gui();

	public static void main(String[] args) {
		init();
		GUI.createAndShowGUI();
	}

	private static void init() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		haarClassifiers = XMLReader.loadCascadeListFromDirectory("resources/learning_resources/XMLs");

	}

	public static List<HaarClassifier> getHaarClassifiers() {
		return haarClassifiers;
	}
}
