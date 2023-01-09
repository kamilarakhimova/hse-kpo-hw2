import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.Charset.defaultCharset;

public class FileHandler {
    private static String longRootPath;
    private static final List<File> notSortedFiles = new ArrayList<>();
    private static File[] sortedFiles;
    private static int[][] dependenciesMatrix;
    private static int matrixLength = 0;

    /**
     * This method calls basic methods for necessary actions with files
     */
    public static void handleFiles() {
        String shortRootPath = UserInteraction.startInfo();
        File folder = new File(shortRootPath);
        if (Checker.doesDirectoryExist(folder)) {
            longRootPath = folder.getAbsolutePath();
        }
        searchTextFiles(folder);
        formDependenciesMatrix();
        Checker.checkFilesCyclic(matrixLength, dependenciesMatrix, notSortedFiles);
        sortFiles();
        UserInteraction.printSortedFiles(sortedFiles);
        printResult();
        UserInteraction.endInfo();
    }

    /**
     * This method searches text files in root directory
     * @param directory is root directory
     */
    private static void searchTextFiles(File directory) {
        File[] allFolders = directory.listFiles();
        assert allFolders != null : "Ошибка! Программа принудительно завершает работу.";
        for (File file : allFolders) {
            if (file.isFile() && (file.getAbsolutePath().endsWith(".txt") ||
                                    file.getAbsolutePath().endsWith(".rtf") ||
                                        file.getAbsolutePath().endsWith(".doc") ||
                                            file.getAbsolutePath().endsWith(".docx"))) {
                if (!String.valueOf(file).contains("result.txt")) {
                    notSortedFiles.add(file);
                }
            }
            if (file.isDirectory()) {
                searchTextFiles(file);
            }
        }
        Checker.checkEmptiness(notSortedFiles);
    }

    /**
     * This method forms matrix with dependencies of each file
     */
    private static void formDependenciesMatrix() {
        matrixLength = notSortedFiles.size();
        dependenciesMatrix = new int[matrixLength][matrixLength];
        sortedFiles = new File[matrixLength];
        fillMatrix();
    }

    /**
     * This method fills matrix of dependencies according to the data specified in files
     */
    private static void fillMatrix() {
        for (int i = 0; i < matrixLength; ++i) {
            try {
                List<String> lines = Files.readAllLines(Path.of(String.valueOf(notSortedFiles.get(i))),
                        defaultCharset());
                for (String line : lines) {
                    if (line.startsWith("require")) {
                        String parentPath = line.substring(9, line.length() - 1);
                        String parentFullPath = longRootPath + '/' + parentPath;
                        File parentFile = new File(parentFullPath);
                        if (parentFile.exists()) {
                            int j = 0;
                            for (File file: notSortedFiles) {
                                if (String.valueOf(file).endsWith(parentPath)) {
                                    dependenciesMatrix[i][j] = 1;
                                    break;
                                }
                                j++;
                            }
                        } else {
                            UserInteraction.printWrongDirective(parentPath, String.valueOf(notSortedFiles.get(i)));
                        }
                    }
                }
            } catch (IOException ex) {
                UserInteraction.printReadingError();
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method sorts files as specified in the task condition
     */
    private static void sortFiles() {
        for (int i = 0; i < matrixLength; ++i) {
            int counter = 0;
            for (int j = 0; j < matrixLength; ++j) {
                if (dependenciesMatrix[i][j] != 0) {
                    ++counter;
                }
            }
            if (sortedFiles[counter] != null) {
                UserInteraction.printUnknownError(String.valueOf(sortedFiles[counter]),
                        String.valueOf(notSortedFiles.get(i)));
            } else {
                sortedFiles[counter] = notSortedFiles.get(i);
            }
        }
    }

    /**
     * This method prints contents of sorted files in the result file
     */
    private static void printResult() {
        try {
            FileWriter fileWriter = new FileWriter(longRootPath + "/result.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (File file : sortedFiles) {
                FileReader fileReader = new FileReader(file.toString());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while (bufferedReader.ready()) {
                    bufferedWriter.write(bufferedReader.readLine());
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            UserInteraction.printResultPath(longRootPath);
        } catch (Exception ex){
            UserInteraction.printResultError();
            ex.printStackTrace();
        }
    }
}
