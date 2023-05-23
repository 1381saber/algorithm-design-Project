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

        NavigationSystem.main();
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
//class NavigationSystem1 {
//
//    // Define the graph as an adjacency list
//    static Map<Integer, List<Edge>> graph = new HashMap<>();
//
//    // Define the Edge class
//    static class Edge {
//        int to;
//        double weight;
//
//        Edge(int to, double weight) {
//            this.to = to;
//            this.weight = weight;
//        }
//    }
//
//    // Dijkstra's algorithm implementation
//    static Map<Integer, Double> dijkstra(int start) {
//        Map<Integer, Double> dist = new HashMap<>();
//        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));
//        pq.offer(new Edge(start, 0));
//        while (!pq.isEmpty()) {
//            Edge curr = pq.poll();
//            if (dist.containsKey(curr.to)) continue;
//            dist.put(curr.to, curr.weight);
//            for (Edge e : graph.getOrDefault(curr.to, Collections.emptyList())) {
//                if (!dist.containsKey(e.to)) {
//                    pq.offer(new Edge(e.to, curr.weight + e.weight));
//                }
//            }
//        }
//        return dist;
//    }
//
//    public static void main() {
//        // Create thegraph as an adjacency list
//        graph.put(0, Arrays.asList(new Edge(1, 4), new Edge(2, 2)));
//        graph.put(1, Arrays.asList(new Edge(0, 4), new Edge(2, 5), new Edge(3, 2)));
//        graph.put(2, Arrays.asList(new Edge(0, 2), new Edge(1, 5), new Edge(3, 1)));
//        graph.put(3, Arrays.asList(new Edge(1, 2), new Edge(2, 1)));
//
//        // Get the user's current location
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter your current location (intersection number): ");
//        int start = scanner.nextInt();
//
//        // Find the shortest path to all factory stores using Dijkstra's algorithm
//        Map<Integer, Double> distances = dijkstra(start);
//
//        // Find the nearest factory store
//        double minDistance = Double.MAX_VALUE;
//        int nearestStore = -1;
//        for (int store : new int[]{4, 5, 6}) { // Assume there are three factory stores with IDs 4, 5, and 6
//            if (distances.containsKey(store) && distances.get(store) < minDistance) {
//                minDistance = distances.get(store);
//                nearestStore = store;
//            }
//        }
//
//        // Print the nearest factory store and its path
//        if (nearestStore != -1) {
//            System.out.println("The nearest factory store is " + nearestStore + " with a distance of " + minDistance + ".");
//            System.out.println("The path to the nearest store is: ");
//            int curr = nearestStore;
//            List<Integer> path = new ArrayList<>();
//            path.add(curr);
//            while (curr != start) {
//                for (Edge e : graph.get(curr)) {
//                    if (distances.containsKey(e.to) && Math.abs(distances.get(curr) - distances.get(e.to) - e.weight) < 1e-9) {
//                        curr = e.to;
//                        path.add(curr);
//                        break;
//                    }
//                }
//            }
//            Collections.reverse(path);
//            System.out.println("Start from intersection " + start + ".");
//            for (int i = 0; i < path.size() - 1; i++) {
//                System.out.println("Go to intersection " + path.get(i) + " and then to intersection " + path.get(i + 1) + ".");
//            }
//        } else {
//            System.out.println("There are no factory stores nearby.");
//        }
//
//        // Close the scanner
//        scanner.close();
//    }
//}


class NavigationSystem {
    static double[][] graph = {
            {0, 2.5, 3.2, 0, 0},
            {2.5, 0, 0, 1.8, 0},
            {3.2, 0, 0, 2.1, 4.7},
            {0, 1.8, 2.1, 0, 2.3},
            {0, 0, 4.7, 2.3, 0}
    };

    public static void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your current location coordinates (x,y):");
        double startX = scanner.nextDouble();
        double startY = scanner.nextDouble();

        // Find the closest intersection to the user's location
        int startIdx = findClosestIntersection(startX, startY);

        // Find the shortest path from the closest intersection to the factory
        int endIdx = findClosestBranch();
        List<Integer> shortestPath = dijkstra(graph, startIdx, endIdx);

        // Print the result
        System.out.println("The closest branch to your location is branch " + (endIdx + 1));
        System.out.println("The shortest path to get there is: ");
        for (int i = 0; i < shortestPath.size(); i++) {
            System.out.print(shortestPath.get(i) + 1);
            if (i != shortestPath.size() - 1) {
                System.out.print(" -> ");
            }
        }

    }

    // Find the closest intersection to the user's location
    public static int findClosestIntersection(double startX, double startY) {
        int closestIdx = 0;
        double closestDist = Double.MAX_VALUE;

        for (int i = 0; i < graph.length; i++) {
            double dist = Math.sqrt(Math.pow(startX - i, 2) + Math.pow(startY - graph[i][0], 2));
            if (dist < closestDist) {
                closestIdx = i;
                closestDist = dist;
            }
        }

        return closestIdx;
    }

    // Find the closest branch to the user's location
    public static int findClosestBranch() {
        // In this example, we assume that the nearest branch is vertex 4
        return 3;
    }

    // Find the shortest path between two vertices using Dijkstra's algorithm
    public static List<Integer> dijkstra(double[][] graph, int start, int end) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        double[] dist = new double[n];
        int[] prev = new int[n];
        PriorityQueue<Integer> pq = new PriorityQueue<>(n, (a, b) -> Double.compare(dist[a], dist[b]));

        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;
        pq.offer(start);

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            visited[curr] = true;

            for (int neighbor = 0; neighbor < n; neighbor++) {
                double weight = graph[curr][neighbor];
                if (weight > 0 && !visited[neighbor]) {
                    double newDist = dist[curr] + weight;
                    if (newDist < dist[neighbor]) {
                        dist[neighbor] = newDist;
                        prev[neighbor] = curr;
                        pq.offer(neighbor);
                    }
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        int curr = end;
        while (curr != -1) {
            path.add(0, curr);
            curr = prev[curr];
        }

        return path;
    }
}