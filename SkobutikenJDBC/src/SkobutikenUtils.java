import POJOS.Customers;
import POJOS.Products;
import Repos.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SkobutikenUtils {
    Scanner scan = new Scanner(System.in);
    Repository r = new Repository();
    List<Products> pL = r.createProductList();

    public SkobutikenUtils() throws IOException, SQLException {
    }

    public Customers LoginMessage() {
        int kundnummer = 0;
        int password = 0;
        try {
            System.out.println("Write your customer number as username: ");
            kundnummer = scan.nextInt();
            System.out.println("Write your password: ");
            password = scan.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Username and password can only be digits, please restart the program!");
            System.exit(0);
        }
        return new Customers(kundnummer, password);
    }

    public List<Products> createCustomerFriendlyList() throws IOException, SQLException {
        return pL.stream().map(products -> {
            int articleNumber = products.getArticleNumber();
            switch (articleNumber) {
                case 100:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 37, "Converse", "Blue", "Casual");
                case 101:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 40, "Converse", "Blue", "Casual");
                case 102:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 41, "Lacoste", "Purple", "Casual");
                case 103:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 42, "Crocs", "Lime Green", "Slippers");
                case 104:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 43, "HugoBoss", "Black", "Dress Shoes");
                case 105:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 44, "Gucci", "Black", "Dress Shoes");
                case 106:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 38, "Ecco", "Beige", "Winter boots");
                case 107:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 41, "Adidas", "Pink", "Sport shoes");
                case 108:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 38, "Puma", "Blue", "Sport shoes");
                case 109:
                    return new Products(articleNumber, products.getPrice(), products.getInventory(), 38, "Ecco", "Black", "Slippers");
                default:
                    return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }


    public int readCustomerChoice() {
        int productOfChoice = 0;
        try {
            System.out.println("\nWhat shoe do you want to order? Write the article number: ");
            productOfChoice = scan.nextInt();
            System.out.println("You picked: " + productOfChoice);
        } catch (InputMismatchException e) {
            System.out.println("You can only write the article number here!");
            System.exit(0);
        }
        return productOfChoice;
    }

    public int readCustomerAmount() {
        int amounts = 0;
        try {
            System.out.println("How many shoes do you want? Write the amount: ");
            amounts = scan.nextInt();
            System.out.println("You choose to buy: " + amounts + " shoes.");
            scan.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("You may only write the amount in digits!");
            System.exit(0);
        }

        return amounts;
    }

    public Products addCustomerChoice(int productOfChoice) {
        return pL.stream()
                .filter(p -> p.getArticleNumber() == productOfChoice)
                .findFirst()
                .map(p -> {
                    return new Products(
                            p.getShoeId(),
                            p.getArticleNumber(),
                            p.getPrice(),
                            p.getInventory(),
                            p.getSizeId(),
                            p.getBrandId(),
                            p.getColourId(),
                            p.getShoecategoryId());
                })
                .orElse(null);
    }

    public int countTotalPrice(Products chosenShoe, int shoeAmount) {
        return shoeAmount * chosenShoe.getPrice();
    }

    public void printProduct(List<Products> productsList) {
        productsList.forEach(e -> System.out.println(
                "ArticleNumber " + e.getArticleNumber()
                        + "     Price: " + e.getPrice()
                        + "     Inventory: " + e.getInventory()
                        + "     Size: " + e.getSize()
                        + "     Brand: " + e.getBrand()
                        + "     Colour: " + e.getColour()
                        + "     Shoecategory: " + e.getShoecategory()));
    }
    public String askForAnotherOrder(String yN) {
        System.out.println("Do you want to place another order?: (Y/N)");
        yN = scan.nextLine().toUpperCase().trim();
        return yN;
    }
}

