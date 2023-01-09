import java.io.File;
import java.util.HashSet;
import java.util.List;

public class Checker {
    private static int length;
    private static int[][] matrix;
    private static List<File> files;
    private static boolean shouldFilesNamesBePrinted = false;

    /**
     * This method checks does root directory exist
     * @param directory is root directory
     * @return is verification result (true/false)
     */
    public static boolean doesDirectoryExist(File directory) {
        if (directory.exists()) {
            UserInteraction.printFolderFound();
            return true;
        } else {
            UserInteraction.printWrongPath();
        }
        return false;
    }

    /**
     * This method checks is there any text file in the root directory
     * @param files is list of text files from the root directory
     */
    public static void checkEmptiness(List<File> files) {
        if (files.isEmpty()) {
            UserInteraction.printNoTxtFilesFound();
        }
    }

    /**
     * This method checks are there any cycle files
     * @param gotLength is length of original matrix of dependencies
     * @param gotMatrix is original matrix of dependencies
     * @param gotFiles is original list of text files
     */
    public static void checkFilesCyclic(int gotLength, int[][] gotMatrix, List<File> gotFiles) {
        length = gotLength;
        matrix = gotMatrix;
        files = gotFiles;
        for (int i = 0; i < length; ++i) {
            for (int j = 0; j < length; ++j) {
                shouldFilesNamesBePrinted = false;
                if (matrix[i][j] != 0 && isFileCycle(i, j, new HashSet<>())) {
                    UserInteraction.printCyclesFound();
                    shouldFilesNamesBePrinted = true;
                    isFileCycle(i, j, new HashSet<>());
                    UserInteraction.printEmergencyEnd();
                }
            }
        }
    }

    /**
     * This method checks is file in a cycle
     * @param prev is number of the last file viewed earlier
     * @param now is number of the file currently being viewed
     * @param repeaters is set of numbers of the files viewed earlier
     * @return is verification result (true/false)
     */
    private static boolean isFileCycle(int prev, int now, HashSet<Integer> repeaters) {
        repeaters.add(prev);
        if (repeaters.contains(now)) {
            if (shouldFilesNamesBePrinted) {
                System.out.println(files.get(now));
            }
            return true;
        }
        for (int j = 0; j < length; ++j) {
            if (matrix[now][j] != 0) {
                if (shouldFilesNamesBePrinted) {
                    System.out.println(files.get(now));
                }
                return isFileCycle(now, j, repeaters);
            }
        }
        return false;
    }
}
