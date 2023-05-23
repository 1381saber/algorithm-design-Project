import java.util.*;


public class Main {
    public static void main(String args[]) throws Exception {

        Menu input = new Menu();
        Menu.firstMenu();
        input.firstMenu();

    }
}

// ----------------------------------------------------
class Menu {
    public static void firstMenu() throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner in = new Scanner(System.in);
        // Display the menu
        System.out.println("--- KOOHDEL CARPET SHOP ----");
        System.out.println("****************************");
        System.out.println("* 1 : DESIGN CARPETS   --> *");
        System.out.println("* 2 : SALE PART        --> *");
        System.out.println("* 3 : PROGRAMMER INFO  --> *");
        System.out.println("* 4 : EXIT !           --> *");
        System.out.println("****************************");

        System.out.println("PLEASE ENTER YOUR CHOICE-->:");

        int choice = in.nextInt();

        switch (choice) {
            case 1:
                designPart();
                break;
            case 2:
                salePart();
                break;
            case 3:
                System.out.println("***Saber Sabzi 1400 UI Dr.Adibi Student***");
                break;
            case 4:
                System.out.println("!!!!!! GOOD BYE !!!!!!");
                return;
            default:
                System.out.println("INVALID CHOICE !");
        }
    }

    // ----------------------------------------------------
    private static void salePart() throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.flush();


        Scanner in = new Scanner(System.in);
        // Display the menu
        System.out.println("-------KOOHDEL CARPET SHOP------");
        System.out.println("********************************");
        System.out.println("* 1 : SEARCH (BASED ON MAP)--> *");
        System.out.println("* 2 : BUY (BASED ON MONEY) --> *");
        System.out.println("* 3 : CLOSEST MARKET       --> *");
        System.out.println("* 4 : FIRST MENU           --> *");
        System.out.println("********************************");

        System.out.println("PLEASE ENTER YOUR CHOICE-->:");

        int choice = in.nextInt();

        switch (choice) {
            case 1:
                searchPart();
                break;
            case 2:
                buyPart();
                break;
            case 3:
                ClosestPart();
                break;
            case 4:
                firstMenu();
                return;
            default:
                System.out.println("INVALID CHOICE !");
        }
    }

    // ----------------------------------------------------
    private static void ClosestPart() {
    }

    // ----------------------------------------------------
    private static void buyPart() {
    }

    // ----------------------------------------------------
    private static void searchPart() throws Exception {
        CarpetFactory input = new CarpetFactory();
        CarpetFactory.main();
    }

    // ----------------------------------------------------
    private static void designPart() {


        Scanner scan = new Scanner(System.in);

        System.out.println("Enter The Number Of Carpet Rows");

        int matrixRow = scan.nextInt();

        System.out.println("Enter The Number Of Carpet Columns");

        int matrixCol = scan.nextInt();

        //defining 2D array to hold matrix data
        int[][] matrix = new int[matrixRow][matrixCol];
        // Enter Matrix Data
        enterMatrixData(scan, matrix, matrixRow, matrixCol);

        int[] result = GraphColoring.graphColoring(matrix);
        System.out.println("Minimum number of colors required for carpet: " + result[0]);
        System.out.println("Colors assigned to regions: ");
        for (int i = 1; i < result.length; i++) {
            System.out.println("Region " + i + ": Color " + result[i]);
        }

    }

    // ----------------------------------------------------
    public static void enterMatrixData(Scanner scan, int[][] matrix, int matrixRow, int matrixCol) {
        System.out.println("Enter Carpet Matrix Data : ");
        for (int i = 0; i < matrixRow; i++) {
            for (int j = 0; j < matrixCol; j++) {
                matrix[i][j] = scan.nextInt();
            }
        }
    }

    // ----------------------------------------------------
}

// ----------------------------------------------------
class GraphColoring {
    // تابع رنگ کردن گراف
    public static int[] graphColoring(int[][] graph) {
        int n = graph.length; // تعداد گره‌ها (نواحی)
        int[] colors = new int[n]; // آرایه‌ای برای نگه‌داری رنگ‌های اختصاص داده شده به گره‌ها

        // آرایه‌ای برای نگه‌داری رنگ‌های مجازی هر گره
        int[] availableColors = new int[n];
        Arrays.fill(availableColors, 0);

        // اختصاص رنگ به گره اول
        colors[0] = 1;

        // اختصاص رنگ به سایر گره‌ها
        for (int i = 1; i < n; i++) {
            Arrays.fill(availableColors, 0); // پاک کردن رنگ‌های مجازی

            // پیدا کردن رنگ‌های مجازی همسایه‌های گره فعلی و اضافه کردندن آن‌ها به آرایه رنگ‌های مجازی
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1 && colors[j] != 0) {
                    availableColors[colors[j] - 1] = 1;
                }
            }

            // پیدا کردن اولین رنگ مجازی برای گره فعلی
            int color = 1;
            for (int j = 0; j < n; j++) {
                if (availableColors[j] == 0) {
                    color = j + 1;
                    break;
                }
            }

            colors[i] = color; // اختصاص رنگ به گره فعلی
        }

        // پیدا کردن تعداد رنگ‌های مجازی استفاده شده برای رنگ کردن گراف
        int numColors = 0;
        for (int i = 0; i < n; i++) {
            if (colors[i] > numColors) {
                numColors = colors[i];
            }
        }
        // برگرداندن تعداد کمترین رنگ‌های مورد نیاز و رنگ‌های اختصاص داده شده به هر یک از نواحی
        int[] result = new int[n + 1];
        result[0] = numColors; // تعداد کمترین رنگ‌های مورد نیاز
        for (int i = 0; i < n; i++) {
            result[i + 1] = colors[i]; // رنگ اختصاص داده شده به هر یک از نواحی
        }
        return result;
    }
}

// ----------------------------------------------------
class CarpetFactory {
    public static void main() throws Exception {
        // Get the input carpet map
        int[][] inputMap = getInputMap();
        Scanner in = new Scanner(System.in);
        // Get the available carpet maps in the system
        int[][][] availableMaps = getAvailableMaps();

        // Calculate the similarity between the input carpet map and the available carpet maps
        double[] similarities = new double[availableMaps.length];
        for (int i = 0; i < availableMaps.length; i++) {
            similarities[i] = calculateSimilarity(inputMap, availableMaps[i]);
        }

        // Sort the carpet maps based on their similarity to the input carpet map
        Integer[] sortedIndices = getSortedIndices(similarities);
        // Display the three most similar carpet maps
        int numToShow = 3;
        for (int i = 0; i < numToShow; i++) {
            int index = sortedIndices[i];
            System.out.println("Similarity with map " + (index + 1) + ": " + similarities[index]);
            printMap(availableMaps[index]);
        }
        System.out.println("****************************");
        System.out.println("BACK TO MAIN MENU 1 ------>:");
        int choice = in.nextInt();

        switch (choice) {
            case 1:
                Menu.firstMenu();
                break;
            default:
                System.out.println("INVALID CHOICE !");
        }
    }

    // ----------------------------------------------------
    private static int[][] getInputMap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimensions of the carpet map (rows, columns):");
        int rows = sc.nextInt();
        int cols = sc.nextInt();

        int[][] map = new int[rows][cols];
        System.out.println("Enter the elements of the carpet map (one row at a time):");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        return map;
    }

    // ----------------------------------------------------
    private static void printMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // ----------------------------------------------------
    public static Integer[] getSortedIndices(double[] arr) {
        Integer[] indices = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, Comparator.comparingInt(a -> (int) arr[a]));
        return indices;
    }

    // ----------------------------------------------------
    private static double calculateSimilarity(int[][] map1, int[][] map2) {
        int dotProduct = 0;
        int norm1 = 0;
        int norm2 = 0;

        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[0].length; j++) {
                dotProduct += map1[i][j] * map2[i][j];
                norm1 += map1[i][j] * map1[i][j];
                norm2 += map2[i][j] * map2[i][j];
            }
        }

        double similarity = dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));

        return similarity;
    }

    // ----------------------------------------------------
    private static int[][][] getAvailableMaps() {
        int[][][] maps = {
                {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}},
                {{1, 1, 0}, {0, 0, 1}, {0, 1, 0}},
                {{1, 1, 1}, {1, 0, 1}, {0, 0, 0}},
                {{0, 0, 1}, {0, 1, 0}, {1, 0, 1}}
        };

        return maps;
    }
}