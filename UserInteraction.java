import java.io.File;
import java.util.Scanner;

public class UserInteraction {
    public static String path;
    private static final String FORCED_PROGRAM_ENDING_INFO = "\nВ связи с этим, программа завершает свою работу.";
    
    /**
     * This method calls methods to print start info
     * @return is path to root directory
     */
    public static String startInfo() {
        printGreeting();
        setPath();
        return path;
    }

    public static void endInfo() {
        printResultInfo();
        printGoodbye();
    }

    public static void printFolderFound() {
        System.out.println("Ура, папка найдена. Приступаю к работе.");
        System.out.println();
    }

    public static void printWrongDirective(String directive, String fileName) {
        System.out.println("К сожалению, директива " + directive + " в файле " + fileName + " неверна." +
                "\nСкорее всего, написание директивы некорректно, либо она ссылается на текущий или " +
                "на несуществующий файл." + FORCED_PROGRAM_ENDING_INFO);
        System.exit(0);
    }

    public static void printWrongPath() {
        System.out.println("К сожалению, по указанному тобою пути не было найдено " +
                "ни одного файла или папки." + FORCED_PROGRAM_ENDING_INFO);
        System.exit(0);
    }

    public static void printNoTxtFilesFound() {
        System.out.println("К сожалению, по указанному тобою пути не было найдено ни одного текстового " +
                "файла." + FORCED_PROGRAM_ENDING_INFO);
        System.out.println("Напоминаю, текстовым файлом считается файл с одним из следующих расширений: " +
                ".txt/.doc/.docx/.rtf");
        System.exit(0);
    }

    public static void printReadingError() {
        System.out.println("К сожалению, при чтении одного из файлов произошла ошибка." +
                FORCED_PROGRAM_ENDING_INFO);
    }

    public static void printCyclesFound() {
        System.out.println("К сожалению, были найдены циклические зависимости в следующих файлах:");
    }

    public static void printEmergencyEnd() {
        System.out.println(FORCED_PROGRAM_ENDING_INFO);
        System.exit(0);
    }

    public static void printUnknownError(String firstFileName, String secondFileName) {
        System.out.println("К сожалению, в указанной тобой папке были найдены файлы " + firstFileName +
                " и " + secondFileName + " с неопределёнными ошибками в директивах." +
                FORCED_PROGRAM_ENDING_INFO);
        System.exit(0);
    }

    public static void printSortedFiles(File[] files) {
        System.out.println("На всякий случай, если вдруг ты захочешь проверить корректность моих действий, " +
                "вот получившийся отсортированный список файлов:");
        for (File file : files) {
            System.out.println(file);
        }
        System.out.println();
    }

    public static void printResultPath(String rootPath) {
        System.out.println("Полный путь до файла с результатом работы: " + rootPath + "/result.txt");
    }

    public static void printResultError() {
        System.out.println("К сожалению, возникла ошибка, связанная с файлом, содержащим результат." +
                FORCED_PROGRAM_ENDING_INFO);
    }

    private static void printGreeting() {
        System.out.println("Привет! Обработчик файлов готов к использованию.");
    }

    private static void setPath() {
        Scanner in = new Scanner(System.in);
        askDesireInput();
        getDesireInput(in.nextLine());
    }

    private static void askDesireInput() {
        System.out.println("""
                Ты хочешь ввести путь до входной папки самостоятельно?
                  
                 "да"/"+"/"yes" -- хочу ввести путь самостоятельно
                "нет"/"-"/"no"  -- хочу использовать тестовый пример (задан по умолчанию)
                                
                Введи команду:""");
    }

    private static void getDesireInput(String answer) {
        switch (answer) {
            case "да", "yes", "+" -> askPath();
            case "нет", "no", "-" -> setDefaultPath();
            default -> {
                printIncorrectInput();
                setDefaultPath();
            }
        }
    }

    private static void askPath() {
        askInputPath();
        Scanner in = new Scanner(System.in);
        path = in.nextLine();
    }

    private static void askInputPath() {
        System.out.println("Введи путь до входной папки: ");
    }

    private static void setDefaultPath() {
        path = "BasicExample";
    }

    private static void printIncorrectInput() {
        System.out.println("""
                К сожалению, введённая команда была некорректна :(
                По умолчанию она обработается, как команда "нет".""");
    }

    private static void printResultInfo() {
        System.out.println("""
                Всё готово! Результат находится в корневой папке в файле "result.txt".
                Наслаждайся!
                """);
    }

    private static void printGoodbye() {
        System.out.println("""
                Надеюсь, я сумел тебе помочь.
                Обращайся ещё :)
                Пока!""");
    }
}
