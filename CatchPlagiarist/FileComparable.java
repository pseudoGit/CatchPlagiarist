package CatchPlagiarist;

import java.util.Comparator;

/**
 *  The class allows MatchedFiles to be sorted by the difference in each
 *  files number of matches
 *
 *  @author Ari Chan
 *  @version December 5, 2015
 */

public class FileComparable implements Comparator<MatchedFiles> {
    /**
     * Compares two MatchedFiles by their number of matches
     *
     * @param file1
     *            a file that is being compared
     * @param file2
     *            another file that is used to be compared to
     *
     * @return the difference of the two matches
     */
    public int compare(MatchedFiles file1, MatchedFiles file2) {
	return file2.getNumMatches() - file1.getNumMatches();
    }
}