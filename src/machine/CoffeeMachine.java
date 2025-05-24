package machine;
import java.util.Scanner;
public class CoffeeMachine {

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

    public static final int DISPOSABLE_CUPS = 1;

    public static final Scanner scan = new Scanner(System.in);

    public int availWater = 0;
    public int availMilk = 0;
    public int availCoffeeBeans = 0;
    public int moneyEarned = 0;
    public int availDisposableCups = 0;

    public CoffeeMachine(int availMilk, int availWater, int availCoffeeBeans, int availDisposableCups) {
        this.availMilk = availWater;
        this.availWater = availMilk;
        this.availCoffeeBeans = availCoffeeBeans;
        this.availDisposableCups = availDisposableCups;
    }

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine(4000, 1000, 500, 30);

        coffeeMachine.displayResources();
        coffeeMachine.askForAction();
        System.out.println("");
        coffeeMachine.displayResources();

    }

    public void askForAction(){
        String command = "";
        System.out.println("Write action (buy, fill, take):");
        command = scan.next().toLowerCase();
        actionController(command);
    }

    public void actionController(String action){
        switch (action){
            case "buy":
                int coffeeType = displayCoffeeOptionsAndCollectOrder();
                String msg = collectMoneyAndServeCoffee(coffeeType);
//                System.out.println(msg);
                break;
            case "fill":
                collectAndFillIngredients();
                break;
            case "take":
                giveOutMoney();
                break;
            default:
                System.out.println("Invalid Service Entered");
        }
    }

    private void giveOutMoney() {
        System.out.println("I gave you "+ this.moneyEarned + "$\n");
        this.moneyEarned = 0;
    }

    private void collectAndFillIngredients() {
        System.out.println("Write how many ml of water you want to add:");
        this.availWater += scan.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        this.availMilk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.availCoffeeBeans += scan.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        this.availDisposableCups += scan.nextInt();
    }

    private String collectMoneyAndServeCoffee(int coffeeType) {

        String coffee = "";

        switch (coffeeType) {
            case 1:
                collectMoney(COST_OF_ESPRESSO);
                makeCoffee(WATER_FOR_ESPRESSO, MILK_FOR_ESPRESSO, COFFEE_BEANS_FOR_ESPRESSO);
                coffee = "Espresso";
                break;
            case 2:
                collectMoney(COST_OF_LATTE);
                makeCoffee(WATER_FOR_LATTE, MILK_FOR_LATTE, COFFEE_BEANS_FOR_LATTE);
                coffee = "Latte";
                break;
            case 3:
                collectMoney(COST_OF_CAPPUCCINO);
                makeCoffee(WATER_FOR_CAPPUCCINO, MILK_FOR_CAPPUCCINO, COFFEE_BEANS_FOR_CAPPUCCINO);
                coffee = "Cappuccino";
                break;
            default:
                return "Please choose from Available Options";
        }
        return "Here is your "+ coffee +", Have a Great Day!";
    }


    private static int displayCoffeeOptionsAndCollectOrder() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        return scan.nextInt();
    }

    public void makeCoffee(int water, int milk, int coffee){
            this.availWater -= water;
            this.availMilk -= milk;
            this.availCoffeeBeans -= coffee;
            this.availDisposableCups -= CoffeeMachine.DISPOSABLE_CUPS;
    }

    private void collectMoney(int price) {
        this.moneyEarned += price;
    }

    public void displayResources(){
        System.out.println("The coffee machine has:");
        System.out.println(this.availWater+" ml of water");
        System.out.println(this.availMilk+" ml of milk");
        System.out.println(this.availCoffeeBeans+" g of coffee beans");
        System.out.println(this.availDisposableCups+" disposable cups");
        System.out.println(this.moneyEarned + "$ of money \n");
    }

}