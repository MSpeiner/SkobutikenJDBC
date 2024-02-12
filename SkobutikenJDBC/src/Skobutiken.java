import POJOS.Customers;
import POJOS.Dates;
import POJOS.Products;
import Repos.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

public class Skobutiken {
    SkobutikenUtils sU = new SkobutikenUtils();
    Repository r = new Repository();
    Customers c;
    List<Products> productsList = sU.createCustomerFriendlyList();
    String yN = "";
    int dates = 0;
    int ordersId = 0;
    boolean firstLap = true;
    boolean datesInserted = false;

    public Skobutiken() throws IOException, SQLException, InputMismatchException {
        Customers i;
        do {
            c = sU.LoginMessage();
            i = r.checkCustomer(c.getCustomerNumber(), c.getPasswords());

            if (i == null) {
                System.out.println("This customer doesn't exist");
            } else {
                System.out.println("Welcome Customer " + c.getCustomerNumber());
                c.setCustomerId(i.getCustomerId());

                do {
                    sU.printProduct(productsList);

                    int customerChoice = sU.readCustomerChoice();
                    int customerAmount = sU.readCustomerAmount();


                    try {
                       yN = sU.askForAnotherOrder(yN);
                    } catch (InputMismatchException e) {
                        System.out.println("Only Y or N can be entered here!");
                    }

                    if (!datesInserted) {
                        Dates d = new Dates();
                        int years = d.setYears();
                        String months = d.setMonths();
                        int days = d.setDays();
                        dates = r.insertDates(years, months, days);
                        datesInserted = true;
                    }
                    if (!firstLap) {
                        ordersId = r.findLatestOrderId();
                    }
                    Products cC = sU.addCustomerChoice(customerChoice);
                    try {
                        int totalPrice = sU.countTotalPrice(cC, customerAmount);
                        r.callAddtoCart(ordersId, cC.getShoeId(), c.getCustomerId(), totalPrice, dates, customerAmount);
                        System.out.println("Your order was submitted correctly, thank you!");
                        System.out.println();
                    } catch (NullPointerException e) {
                        System.out.println("Something went wrong with your order. Please restart the order!");
                        System.exit(0);
                    }

                    firstLap = false;

                } while (yN.equals("Y"));
                System.out.println("Everything in your order was executed, thank you and welcome back!");
            }
        } while (i == null);
    }

    public static void main(String[] args) throws IOException, SQLException {
        Skobutiken s = new Skobutiken();
    }
}