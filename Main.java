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

        System.out.println("PLEASE ENTER YOUR CHOICE: -->");

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
        ShortestPath.main();
    }

    // ----------------------------------------------------
    private static void buyPart() {
        CarpetShop.main();
    }

    // ----------------------------------------------------
    private static void searchPart() throws Exception {
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

        return similarity * 100;
    }

    // ----------------------------------------------------
    private static int[][][] getAvailableMaps() {
        int[][][] maps = {
                {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}},
                {{1, 1, 0}, {0, 0, 1}, {0, 1, 0}},
                {{1, 1, 1}, {1, 0, 1}, {0, 0, 0}}
        };

        return maps;
    }
}

// ----------------------------------------------------
class CarpetShop {

    public static class Carpet {

        private String name;
        private int price;
        private int area;

        //---------------------------
        public Carpet(String name, int area, int price) {
            this.name = name;
            this.area = area;
            this.price = price;
        }

        //---------------------------
        public void setPrice(int price) {
            this.price = price;
        }

        //---------------------------
        public int getPrice() {
            return price;
        }

        //---------------------------
        public void setArea(int area) {
            this.area = area;
        }

        //---------------------------
        public int getArea() {
            return area;
        }

        //---------------------------
        public String getName() {
            return name;
        }
        //---------------------------
    }

    public static List<Carpet> findAffordableCarpets(int money, List<Carpet> carpets) {
        int n = carpets.size();
        int[] prices = new int[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            Carpet carpet = carpets.get(i);
            prices[i] = carpet.getPrice();
            values[i] = carpet.getArea();
        }
        //---------------------------
        int[][] dp = new int[n + 1][money + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= money; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (prices[i - 1] <= j) {
                    dp[i - 1][j] = values[i - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        //---------------------------
        List<Carpet> affordableCarpets = new ArrayList<>();
        int j = money;
        for (int i = n; i > 0 && j > 0; i--) {
            if (dp[i][j] != dp[i - 1][j]) {
                Carpet carpet = carpets.get(i - 1);
                affordableCarpets.add(carpet);
                j -= carpet.getPrice();
            }
        }
        return affordableCarpets;
    }

    public static void main() {

        // Get the user's budget
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your budget: ---> ");
        int money = scanner.nextInt();
        List<Carpet> carpets = new ArrayList<>();
        carpets.add(new Carpet("Carpet A", 2, 100)); // id, area, price
        carpets.add(new Carpet("Carpet B", 3, 150));
        carpets.add(new Carpet("Carpet C", 4, 200));
        carpets.add(new Carpet("Carpet D", 5, 250));
        carpets.add(new Carpet("Carpet E", 6, 280));
        carpets.add(new Carpet("Carpet f", 8, 300));


        List<Carpet> affordableCarpets = findAffordableCarpets(money, carpets);
        if (affordableCarpets.isEmpty()) {
            System.out.println("You cannot afford any carpets.");
        } else {
            System.out.println("You can afford the following carpets:");
            for (Carpet carpet : affordableCarpets) {
                System.out.println(carpet.getName() + "- Price: " + carpet.getPrice() + " Area: " + carpet.getArea());
            }
        }
    }
}

// ----------------------------------------------------
class ShortestPath {

    static final int V = 9;

    class eachNode {
        int vertexNum;
        Boolean isDepartment;
        static ArrayList<ShortestPath.eachNode> vertices = new ArrayList<>();

        public eachNode(int vertexNum, Boolean isDepartment) {
            this.vertexNum = vertexNum;
            this.isDepartment = isDepartment;


            ShortestPath.eachNode v0 = new ShortestPath.eachNode(0, false);
            ShortestPath.eachNode v1 = new ShortestPath.eachNode(1, true);
            ShortestPath.eachNode v2 = new ShortestPath.eachNode(2, false);
            ShortestPath.eachNode v3 = new ShortestPath.eachNode(3, true);
            ShortestPath.eachNode v4 = new ShortestPath.eachNode(4, false);
            ShortestPath.eachNode v5 = new ShortestPath.eachNode(5, true);
            ShortestPath.eachNode v6 = new ShortestPath.eachNode(6, false);
            ShortestPath.eachNode v7 = new ShortestPath.eachNode(7, true);
            ShortestPath.eachNode v8 = new ShortestPath.eachNode(8, false);

            vertices.add(v0);
            vertices.add(v1);
            vertices.add(v2);
            vertices.add(v3);
            vertices.add(v4);
            vertices.add(v5);
            vertices.add(v6);
            vertices.add(v7);
            vertices.add(v8);


        }
    }

    int minDistance(ArrayList<eachNode> dist, Boolean[] sptSet) {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++)
            if (sptSet[v] == false && dist.get(v).vertexNum <= min) {
                min = dist.get(v).vertexNum;
                min_index = v;
            }

        return min_index;
    }

    // A utility function to print the constructed distance
    // array
    void printSolution(ArrayList<eachNode> dist) {
        System.out.println(
                "Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i + " \t\t " + dist.get(i).vertexNum);
    }

    // Function that implements Dijkstra's single source
    // shortest path algorithm for a graph represented using
    // adjacency matrix representation
    void dijkstra(int graph[][], int src) {
        ArrayList<ShortestPath.eachNode> dist = eachNode.vertices;

        Boolean sptSet[] = new Boolean[V];

        // Initialize all distances as INFINITE and stpSet[]
        // as false
        for (int i = 0; i < V; i++) {
            dist.get(i).vertexNum = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist.get(src).vertexNum = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set
            // of vertices not yet processed. u is always
            // equal to src in first iteration.
            int u = minDistance(dist, sptSet);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of
            // the picked vertex.
            for (int v = 0; v < V; v++)

                // Update dist[v] only if is not in sptSet,
                // there is an edge from u to v, and total
                // weight of path from src to v through u is
                // smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0
                        && dist.get(u).vertexNum != Integer.MAX_VALUE
                        && dist.get(u).vertexNum + graph[u][v] < dist.get(v).vertexNum)
                    dist.get(v).vertexNum = dist.get(u).vertexNum + graph[u][v];
        }

        // print the constructed distance array
        printSolution(dist);
    }

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your current location :");
        int src = scanner.nextInt();

        int graph[][]
                = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        ShortestPath t = new ShortestPath();

        // Function call
        t.dijkstra(graph, src);
    }
}