package CatchPlagiarist;

/**
 * A FileObject is an object that includes the file's name and the binary search
 * tree of the file's n-grams
 *
 * @author Ari Chan
 * @version December 5, 2015
 */

public class FileObject {
    private String fileName;
    private BinarySearchTree<Integer> gramTree;

    /**
     * Constructs a new FileObject
     *
     * @param fileName
     *            the name of a file
     */
    public FileObject(String fileName) {
	this.fileName = fileName;
	this.gramTree = new BinarySearchTree<Integer>();
    }

    /**
     * A getter method for the file name
     *
     * @return the name of the file
     */
    public String getFileName() {
	return this.fileName;
    }

    /**
     * A getter method for the binary search tree of n-grams
     *
     * @return gramTree a tree of n-grams
     */
    public BinarySearchTree<Integer> getGramTree() {
	return this.gramTree;
    }
}