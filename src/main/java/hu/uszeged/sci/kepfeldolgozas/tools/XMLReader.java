package hu.uszeged.sci.kepfeldolgozas.tools;

import hu.uszeged.sci.kepfeldolgozas.HaarClassifier;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class XMLReader {
	private static final Logger log = Logger.getLogger(XMLReader.class.getName());
	public static List<HaarClassifier> loadCascadeListFromDirectory(String dirctory) {
		List<HaarClassifier> haarCascadList = new LinkedList<HaarClassifier>();
		try {
			Files.walk(Paths.get(dirctory)).forEach(filePath -> {
				if (filePath.toString().endsWith("xml")) {
					haarCascadList.add(new HaarClassifier(new File(filePath.toString())));
					System.out.println(filePath);
				}
			});
		} catch (IOException e) {
			log.warning("No XML found in " + dirctory+"!");
		}
		return haarCascadList;
	}

}
