package CatchPlagiarist;

import java.util.Collections;

/**
 * The class accepts command line arguments and prints out the files that match
 * and number of matches. Requires a folder path, the n-grams of words, and the
 * threshold for relevant number of matches.
 *
 * @author Ari Chan
 * @version December 5, 2015
 */

public class FileProcDemo {
    /**
     * The main method calls method to read a file, sort the matches,
     * and prints them
     *
     * @param args
     *            a file path, n of n-gram, threshold
     */
    public static void main(String[] args) {
	// Assures that the correct number of initial arguments are given
	if (args.length != 3) {
	    System.out.println("Wrong number of arguments,");
	    System.exit(1);
	}

	try {
	    int nGrams = Integer.parseInt(args[1]);
	    int threshold = Integer.parseInt(args[2]);

	    ReadFile rFile = new ReadFile(args[0], nGrams, threshold);
	    rFile.readFolder();
	    rFile.compareBST();

	    //Sorts the matched files
	    Collections.sort(rFile.getListMatched(), new FileComparable());

	    //Prints the files
	    for (int i = 0; i < rFile.getListMatched().size(); i++) {
		System.out.println(((rFile.getListMatched()).get(i)).toString());
	    }
	} catch (IllegalArgumentException ex) {
	    // Catches an exception if nGrams or threshold are not of type int
	    System.out.println("Illegal arguments were inputted.");
	    System.exit(1);
	}
    }
}