package hu.uszeged.sci.kepfeldolgozas.test;

public class HelloOpenCV {
	  public static void main(String[] args) {
	    System.out.println("Hello, OpenCV");

	    // Load the native library.
	    System.loadLibrary("opencv_java249");
	    new DetectFaceDemo().run();
	  }
	}
