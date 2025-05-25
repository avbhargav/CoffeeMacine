package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public boolean isAlive = true;
    public static final Scanner scan = new Scanner(System.in);
    private final MoneyManager moneyManager = new MoneyManager();
    private final ResourceManager resourceManager = new ResourceManager(400, 540, 120, 9);
    private Coffee coffeeType;


    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        do {
            coffeeMachine.askForAction();
        } while (coffeeMachine.isAlive);

    }

    public void askForAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        actionController(scan.next().toLowerCase());
    }

    public void actionController(String action) {
        switch (action) {
            case "buy":
                int coffeeType = this.displayCoffeeOptionsAndCollectOrder();
                String msg = this.collectMoneyAndServeCoffee(coffeeType);
                System.out.println(msg);
                break;
            case "fill":
                resourceManager.collectAndFillResources();
                break;
            case "take":
                moneyManager.giveOutMoney();
                break;
            case "remaining":
                resourceManager.displayResources();
                moneyManager.displayMoneyEarned();
                break;
            case "exit":
                this.stopCoffeeMachine();
                break;
            default:
                System.out.println("Invalid Service Entered");
        }
    }

    private String collectMoneyAndServeCoffee(int userChoice) {

        String msg = "";

        switch (userChoice) {
            case 1:
                coffeeType = new Coffee("espresso",250, 0, 16, 1);
                this.checkResourcesAndMakeCoffee(coffeeType);
                break;
            case 2:
                coffeeType = new Coffee("latte",350, 75, 20, 7);
                this.checkResourcesAndMakeCoffee(coffeeType);
                break;
            case 3:
                coffeeType = new Coffee("cappuccino",200, 100, 12, 6);
                this.checkResourcesAndMakeCoffee(coffeeType);
                break;
            default:
                return "Please choose from Available Options";
        }
        return "Here is your " + coffeeType.getCOFFEE_NAME() + ", Have a Great Day!";
    }

    public void checkResourcesAndMakeCoffee(Coffee coffeeType){
        if(resourceManager.isHasSufficientResources()){
            System.out.println("I have enough resources, making you a coffee!");
            moneyManager.collectMoney(coffeeType.getCOST_OF_COFFEE());
            makeCoffee(coffeeType);
        } else {
            System.out.println("Sorry, not enough "+resourceManager.limitedResource(coffeeType)+"!");
        }
    }

    private int displayCoffeeOptionsAndCollectOrder() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
        return scan.nextInt();
    }

    public void makeCoffee(Coffee coffee) {
        this.availWater -= water;
        this.availMilk -= milk;
        this.availCoffeeBeans -= coffeeBeans;
        this.availDisposableCups -= CoffeeMachine.DISPOSABLE_CUPS;
    }

    private void stopCoffeeMachine() {
        this.isAlive = false;
    }
}


class MoneyManager {

    public static final int COST_OF_ESPRESSO = 4;
    public static final int COST_OF_LATTE = 7;
    public static final int COST_OF_CAPPUCCINO = 6;

    public int moneyEarned = 550;

    public void displayMoneyEarned() {
        System.out.println(this.moneyEarned +"$ of money\"");
    }

    public void giveOutMoney() {
        System.out.println("I gave you " + this.moneyEarned + "$\n");
        this.moneyEarned = 0;
    }
    public void collectMoney(int coffeeCost) {
        this.moneyEarned += coffeeCost;
    }

}

class ResourceManager {

    private int availWater;
    private int availMilk;
    private int availCoffeeBeans;
    private int availDisposableCups;

    public boolean isHasSufficientResources() {
        return hasSufficientResources;
    }

    private boolean hasSufficientResources;
    private Scanner scan = new Scanner(System.in);

    public ResourceManager(int availMilk, int availWater, int availCoffeeBeans, int availDisposableCups) {
        this.availMilk = availWater;
        this.availWater = availMilk;
        this.availCoffeeBeans = availCoffeeBeans;
        this.availDisposableCups = availDisposableCups;
    }

    public String limitedResource(Coffee coffeeType){
        String lowStockItem = "";
        if(this.availWater < coffeeType.getWATER_NEEDED()){
            lowStockItem = "water";
            this.hasSufficientResources = false;
        } else if(this.availMilk < coffeeType.getMILK_NEEDED()){
            lowStockItem = "milk";
            this.hasSufficientResources = false;
        } else if(this.availCoffeeBeans < coffeeType.getCOFFEE_BEANS_NEEDED()){
            lowStockItem = "coffee beans";
            this.hasSufficientResources = false;
        } else if(this.availDisposableCups < coffeeType.getDISPOSABLE_CUPS()){
            lowStockItem = "disposable cups";
            this.hasSufficientResources = false;
        } else {
            this.hasSufficientResources = true;
        }
        return lowStockItem;
    }


    public void getResources(String coffeeType){

    }

    public void collectAndFillResources() {
        System.out.println("Write how many ml of water you want to add:");
        this.availWater += scan.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        this.availMilk += scan.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        this.availCoffeeBeans += scan.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        this.availDisposableCups += scan.nextInt();
    }

    public void displayResources() {
        System.out.println("The coffee machine has:");
        System.out.println(this.availWater + " ml of water");
        System.out.println(this.availMilk + " ml of milk");
        System.out.println(this.availCoffeeBeans + " g of coffee beans");
        System.out.println(this.availDisposableCups + " disposable cups");
    }
}

class Coffee {

    private final String COFFEE_NAME;
    private final int WATER_NEEDED;
    private final int MILK_NEEDED;
    private final int COFFEE_BEANS_NEEDED;
    private final int DISPOSABLE_CUPS = 1;
    private final int COST_OF_COFFEE;

    public Coffee(String coffeeName, int water, int milk, int coffeeBeans, int costOfCoffee){
        this.COFFEE_NAME = coffeeName;
        this.WATER_NEEDED = water;
        this.MILK_NEEDED = milk;
        this.COFFEE_BEANS_NEEDED = coffeeBeans;
        this.COST_OF_COFFEE = costOfCoffee;
    }

    public String getCOFFEE_NAME() {
        return COFFEE_NAME;
    }

    public int getWATER_NEEDED() {
        return WATER_NEEDED;
    }

    public int getMILK_NEEDED() {
        return MILK_NEEDED;
    }

    public int getCOFFEE_BEANS_NEEDED() {
        return COFFEE_BEANS_NEEDED;
    }

    public int getDISPOSABLE_CUPS() {
        return DISPOSABLE_CUPS;
    }

    public int getCOST_OF_COFFEE() {
        return COST_OF_COFFEE;
    }
}

