package CatchPlagiarist;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class creates an object that stores every string of each file from a
 * folder into an ArrayList with n-grams for the list of words. It requires the
 * file path and number of words per gram from the main method. Then it makes a
 * list of matching files.
 *
 * @author Ari Chan
 * @version December 5, 2015
 */

public class ReadFile {
    private ArrayList<FileObject> fileObjList; // An ArrayList of FileObject
    private ArrayList<MatchedFiles> listMatched; // An ArrayList of MatchedFiles
    private String dirName;
    private int nGram;
    private int threshold;

    /**
     * Creates a read file object
     *
     * @param dirName
     *            the path
     * @param nGram
     *            the number of words per gram
     * @param threshold
     *            the required number of matches to be relevant
     */
    public ReadFile(String dirName, int nGram, int threshold) {
	this.fileObjList = new ArrayList<FileObject>();
	this.listMatched = new ArrayList<MatchedFiles>();
	this.dirName = dirName;
	this.nGram = nGram;
	this.threshold = threshold;
    }

    /**
     * Returns the ArrayList of FileObjects
     *
     * @return fileObjList an ArrayList containing a FileObject
     */
    public ArrayList<FileObject> getFileObjList() {
	return this.fileObjList;
    }

    /**
     * Returns the ArrayList of listMatched
     *
     * @return fileObjList an ArrayList containing a listMatched
     */
    public ArrayList<MatchedFiles> getListMatched() {
	return this.listMatched;
    }

    /**
     * Returns the path name
     *
     * @return dirName the folder path
     */
    public String getDirName() {
	return this.dirName;
    }

    /**
     * Returns the number of Strings in a gram
     *
     * @return nGrams the number of strings in each group
     */
    public int getNGrams() {
	return this.nGram;
    }

    /**
     * Open an entire folder and process all the files
     */
    public void readFolder() {
	try {
	    File folder = new File(dirName);
	    File[] allFiles = folder.listFiles();
	    for (int i = 0; i < allFiles.length; i++) {
		// Pass a single file to be processed
		fileObjList.add(readFile(allFiles[i]));
	    }
	} catch (NullPointerException ex) {
	    System.out.println("Invalid path.");
	    System.exit(1);
	}
    }

    /**
     * Reads a file, creates n-grams, creates a hash code, and stores the the
     * hashes into an ArrayList
     *
     * @param procFile
     *            the file in the folder that is being processed
     * @return bstGrams a binary search tree containing the n-grams of a file
     */
    private FileObject readFile(File procFile) {
	ArrayList<String> wordList = new ArrayList<String>();
	FileObject file = new FileObject(procFile.getName());

	try {
	    Scanner input = new Scanner(procFile);
	    // Stores all of the strings into an ArrayList
	    while (input.hasNext()) {
	   	wordList.add(input.next());

	    }
	    input.close(); // Close scanner
	    for (int i = 0; i < wordList.size() - nGram; i++) {
		String wordGram = "";
		for (int j = 0; j < nGram; j++) {
		    wordGram = wordGram + wordList.get(i + j);
		    if (j < nGram - 1) {
			wordGram = wordGram + " ";
		    }
		}
		// Add the n-gram to a binary search tree of a file
		(file.getGramTree()).insert(wordGram.hashCode());
	    }
	} catch (FileNotFoundException ex) {
	    System.out.println("File not found.");
	    System.exit(1); // Exit the system
	}

	return file;
    }

    /**
     * Creates a MatchedFiles object and calls a recursive method to return the
     * number of matching n-grams between two binary search trees
     */
    public void compareBST() {
	// Choose which files to compare
	for (int i = 0; i < this.fileObjList.size(); i++) {
	    for (int j = i + 1; j < this.fileObjList.size(); j++) {
		MatchedFiles match = new MatchedFiles(this.fileObjList.get(i).getFileName(),
			this.fileObjList.get(j).getFileName());
		match.setNumMatches(compareBST(this.fileObjList.get(i).getGramTree().getRoot(),
			this.fileObjList.get(j).getGramTree()));
		// Adds only the relevant matched sets
		if (match.getNumMatches() >= threshold) {
		    this.listMatched.add(match);
		}
	    }
	}
    }

    /**
     * Internal method. Traverses the reference tree to try and find a match in
     * the matching tree.
     *
     * @return the number of matches
     */
    private int compareBST(BinaryNode<Integer> refNode,
	    BinarySearchTree<Integer> matBST) {
	if (refNode == null) {
	    return 0;
	} else if (matBST.find(refNode.getElement()) == null) {
	    return compareBST(refNode.getLeft(), matBST) +
		    compareBST(refNode.getRight(), matBST);
	} else {
	    return 1 + compareBST(refNode.getLeft(), matBST) +
		    compareBST(refNode.getRight(), matBST);
	}
    }
}