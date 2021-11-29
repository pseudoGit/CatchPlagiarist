package CatchPlagiarist;

/**
 * This class creates an object that represents a pair of files and the number
 * of matches between them.
 *
 * @author Ari Chan
 * @version December 5, 2015
 */

public class MatchedFiles {
    private int numMatches;
    private String refFile; // The reference file
    private String matFile; // The file that matches the reference file

    /**
     * Creates an object that is two files with their number of hits
     *
     * @param refFile
     * @param matFile
     */
    public MatchedFiles(String refFile, String matFile) {
	this.refFile = refFile;
	this.matFile = matFile;
	this.numMatches = 0;
    }

    /**
     * A getter for the reference file
     *
     * @return the file used as the reference for comparison
     */
    public String getRefFile() {
	return this.refFile;
    }

    /**
     * A getter for the matching file
     *
     * @return the file used for comparing
     */
    public String getMatFile() {
	return this.matFile;
    }

    /**
     * A getter method for the number of matches
     *
     * @return the number of matching n-grams between two files
     */
    public int getNumMatches() {
	return this.numMatches;
    }

    /**
     * A setter method for the number of matches
     *
     * @param numMatches
     *            the new number of matches
     */
    public void setNumMatches(int numMatches) {
	this.numMatches = numMatches;
    }

    /**
     * Returns a string with the matches and the files as: "# of hits " +
     * refFile + ":" + matFile
     *
     * @return returns a string with the numberMatches, refFile, and matFile
     */
    public String toString() {
	return this.numMatches + " " + refFile + " : " + matFile;
    }
}