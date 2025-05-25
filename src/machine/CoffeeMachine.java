package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public boolean isAlive = true;

    private int userCoffeeChoice;
    public static final Scanner scan = new Scanner(System.in);
    private final MoneyManager moneyManager = new MoneyManager();
    private final ResourceManager resourceManager = new ResourceManager(400, 540, 120, 9);


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
            case "buy" -> {
                this.takeOrder();
                this.collectMoneyAndServeCoffee(this.userCoffeeChoice);
            }
            case "fill" -> resourceManager.collectAndFillResources();
            case "take" -> moneyManager.giveOutMoney();
            case "remaining" -> {
                resourceManager.displayResources();
                moneyManager.displayMoneyEarned();
            }
            case "exit" -> this.stopCoffeeMachine();
            default -> System.out.println("Invalid Service Entered");
        }
    }

    private void takeOrder() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String input = scan.next();
        // Try to parse the input as an integer
        try {
            this.userCoffeeChoice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            if(input.equals("back")){
                this.askForAction();
            }
        }
    }

    private void collectMoneyAndServeCoffee(int userChoice) {

        Coffee coffeeType;
        switch (userChoice) {
            case 1 -> {
                coffeeType = new Coffee("espresso", 250, 0, 16, 1, 4);
                this.checkResourcesAndMakeCoffee(coffeeType);
            }
            case 2 -> {
                coffeeType = new Coffee("latte", 350, 75, 20, 1, 7);
                this.checkResourcesAndMakeCoffee(coffeeType);
            }
            case 3 -> {
                coffeeType = new Coffee("cappuccino", 200, 100, 12, 1, 6);
                this.checkResourcesAndMakeCoffee(coffeeType);
            }
            default -> {
                System.out.println("Please choose from Available Options");
            }
        }

    }

    public void checkResourcesAndMakeCoffee(Coffee coffeeType){

        resourceManager.findScarceResource(coffeeType);

        if(resourceManager.hasSufficientResources()){
            System.out.println("I have enough resources, making you a coffee!");
            moneyManager.collectMoney(coffeeType.getCOST_OF_COFFEE());
            makeCoffee(coffeeType);
            System.out.println("Here is your " + coffeeType.getCOFFEE_NAME() + ", Have a Great Day! \n");
        } else {
            System.out.println("Sorry, not enough "+ resourceManager.getScarceItem() +"! \n");
        }
    }

    public void makeCoffee(Coffee coffee) {
        resourceManager.setAvailWater(resourceManager.getAvailWater() - coffee.getWATER_NEEDED());
        resourceManager.setAvailMilk(resourceManager.getAvailMilk() - coffee.getMILK_NEEDED());
        resourceManager.setAvailCoffeeBeans(resourceManager.getAvailCoffeeBeans() - coffee.getCOFFEE_BEANS_NEEDED());
        resourceManager.setAvailDisposableCups(resourceManager.getAvailDisposableCups() - coffee.getDISPOSABLE_CUPS());
    }

    private void stopCoffeeMachine() {
        this.isAlive = false;
    }
}


class MoneyManager {
    public int moneyEarned = 550;

    public void displayMoneyEarned() {
        System.out.println(this.moneyEarned +"$ of money\n");
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

    public int getAvailWater() {
        return availWater;
    }

    public void setAvailWater(int availWater) {
        this.availWater = availWater;
    }

    public int getAvailMilk() {
        return availMilk;
    }

    public void setAvailMilk(int availMilk) {
        this.availMilk = availMilk;
    }

    public int getAvailCoffeeBeans() {
        return availCoffeeBeans;
    }

    public void setAvailCoffeeBeans(int availCoffeeBeans) {
        this.availCoffeeBeans = availCoffeeBeans;
    }

    public int getAvailDisposableCups() {
        return availDisposableCups;
    }

    public void setAvailDisposableCups(int availDisposableCups) {
        this.availDisposableCups = availDisposableCups;
    }

    public String getScarceItem() {
        return scarceItem;
    }

    private String scarceItem;
    private boolean hasSufficientResources;
    private final Scanner scan = new Scanner(System.in);

    public ResourceManager(int availMilk, int availWater, int availCoffeeBeans, int availDisposableCups) {
        this.availMilk = availWater;
        this.availWater = availMilk;
        this.availCoffeeBeans = availCoffeeBeans;
        this.availDisposableCups = availDisposableCups;
    }

    public void findScarceResource(Coffee coffeeType){

        if(this.availWater < coffeeType.getWATER_NEEDED()){
            this.scarceItem = "water";
            this.hasSufficientResources = false;
        } else if(this.availMilk < coffeeType.getMILK_NEEDED()){
            this.scarceItem = "milk";
            this.hasSufficientResources = false;
        } else if(this.availCoffeeBeans < coffeeType.getCOFFEE_BEANS_NEEDED()){
            this.scarceItem = "coffeeBeans";
            this.hasSufficientResources = false;
        } else if(this.availDisposableCups < coffeeType.getDISPOSABLE_CUPS()){
            this.scarceItem = "disposable cups";
            this.hasSufficientResources = false;
        } else {
            this.hasSufficientResources = true;
        }
    }

    public boolean hasSufficientResources() {
        return hasSufficientResources;
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
    private final int DISPOSABLE_CUPS;
    private final int COST_OF_COFFEE;

    public Coffee(String coffeeName, int water, int milk, int coffeeBeans, int disposableCups, int costOfCoffee){
        this.COFFEE_NAME = coffeeName;
        this.WATER_NEEDED = water;
        this.MILK_NEEDED = milk;
        this.COFFEE_BEANS_NEEDED = coffeeBeans;
        DISPOSABLE_CUPS = disposableCups;
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

