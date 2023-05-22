import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws Exception {

        Menu input = new Menu();
        Menu.firstMenu();
        input.firstMenu();

    }
}

class Menu{
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
    private static void searchPart() {
    }
    // ----------------------------------------------------
    private static void designPart() {

    }
    // ----------------------------------------------------
}