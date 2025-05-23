package machine;
import java.util.Scanner;
public class CoffeeMachine {

    /*
    For a cup of espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
     */

    public static final int WATER_FOR_ESPRESSO = 250;
    public static final int MILK_FOR_ESPRESSO = 0;
    public static final int COFFEE_BEANS_FOR_ESPRESSO = 16;
    public static final int COST_OF_ESPRESSO = 4;

    public static final int WATER_FOR_LATTE = 350;
    public static final int MILK_FOR_LATTE = 75;
    public static final int COFFEE_BEANS_FOR_LATTE = 20;
    public static final int COST_OF_LATTE = 7;

    public static final int WATER_FOR_CAPPUCCINO = 200;
    public static final int MILK_FOR_CAPPUCCINO = 100;
    public static final int COFFEE_BEANS_FOR_CAPPUCCINO = 12;
    public static final int COST_OF_CAPPUCCINO = 6;

    public static final Scanner scan = new Scanner(System.in);

    public int availWater = 0;
    public int availMilk = 0;
    public int availCoffeeBeans = 0;
    public int moneyEarned = 0;

    public static void main(String[] args) {

    }

    public static void askForAction(){
        System.out.println("Write action (buy, fill, take):");
        actionController(scan.next().toLowerCase());
    }

    public static void actionController(String action){
        switch (action){
            case "buy":
                System.out.println("Shown the options");
                System.out.println("Collect the Order");
                System.out.println("Make the Coffee");
                System.out.println("Offer Coffee");
                System.out.println("Collect Money");
                int coffeeType = displayCoffeeOptionsAndCollectOrder();
                serveCoffee(coffeeType);
                break;
            case "fill":
                System.out.println("Asked for ingredient quantities");
                System.out.println("Collect the ingredients");
                System.out.println("Update the ingredients");
                break;
            case "take":
                System.out.println("gave the collected money");
                break;
            default:
                System.out.println("Invalid Service Entered");
        }
    }

    private static String serveCoffee(int coffeeType) {

        String serveMsg = "";

        switch (coffeeType) {
            case 1:
                makeCoffee(WATER_FOR_ESPRESSO, MILK_FOR_ESPRESSO, COFFEE_BEANS_FOR_ESPRESSO, COST_OF_ESPRESSO);
                serveMsg = "Please Collect Your Espresso, Have a Great Day!";
                break;
            case 2:
                makeCoffee(WATER_FOR_LATTE, MILK_FOR_LATTE, COFFEE_BEANS_FOR_LATTE, COST_OF_LATTE);
                serveMsg = "Please Collect Your Latte, Have a Great Day!";
                break;
            case 3:
                makeCoffee(WATER_FOR_CAPPUCCINO, MILK_FOR_CAPPUCCINO, COFFEE_BEANS_FOR_CAPPUCCINO, COST_OF_CAPPUCCINO);
                serveMsg = "Please Collect Your Cappuccino, Have a Great Day!";
                break;
            default:

        }
        return serveMsg;
    }

    private static int displayCoffeeOptionsAndCollectOrder() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        System.out.println("> ");
        return scan.nextInt();
    }

    public static void makeCoffee(int water, int milk, int coffee, int price){

    }


//    private static void checkResourcesAndMeasurePossibleCups(){
//        int waterRequired = requestForResource("Write how many ml of water the coffee machine has:");
//        int milkRequired = requestForResource("Write how many ml of milk the coffee machine has:");
//        int coffeeBeansRequired = requestForResource("Write how many grams of coffee beans the coffee machine has:");
//        int requiredCups = requestForResource("Write how many cups of coffee you will need:");
//
//        int coffeeCupsPossible = getCoffeeCupsPossible(waterRequired, milkRequired, coffeeBeansRequired);
//
//        if(requiredCups == coffeeCupsPossible){
//            System.out.println("Yes, I can make that amount of coffee");
//        } else if(requiredCups > coffeeCupsPossible){
//            System.out.println("No, I can make only "+ coffeeCupsPossible +" cup(s) of coffee");
//        } else {
//            System.out.println("Yes, I can make that amount of coffee (and even "+(coffeeCupsPossible - requiredCups) +" more than that)");
//        }
//    }

//    private static int getCoffeeCupsPossible(int water, int milk, int coffeeBeans) {
//        int cupsFromWater = water / REQ_WATER_FOR_ONE_CUP;
//        int cupsFromMilk = milk / REQ_MILK_FOR_ONE_CUP;
//        int cupsFromCoffeeBeans = coffeeBeans / REQ_COFFEE_BEANS_FOR_ESPRESSO;
//
//        int coffeeCupsPossible = 0;
//        if(cupsFromWater <= cupsFromMilk && cupsFromWater <= cupsFromCoffeeBeans){
//            coffeeCupsPossible = cupsFromWater;
//        } else if (cupsFromMilk <= cupsFromWater && cupsFromMilk <= cupsFromCoffeeBeans){
//            coffeeCupsPossible = cupsFromMilk;
//        } else {
//            coffeeCupsPossible = cupsFromCoffeeBeans;
//        }
//        return coffeeCupsPossible;
//    }

    private static int requestForResource(String question) {
        Scanner sc = new Scanner(System.in);
        System.out.println(question);
        System.out.print("> ");
        int resource = sc.nextInt();
        return resource;
    }



}