package Repos;

import POJOS.Customers;
import POJOS.Products;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    Properties p = new Properties();

    public Repository() throws IOException {
        p.load(new FileInputStream("src/Repos/Settings.properties"));
    }

    public int insertDates(int years, String months, int days) {
        String query = "INSERT INTO dates (years,months,days) VALUES(?,?,?) ";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, years);
            stmt.setString(2, months);
            stmt.setInt(3, days);
            stmt.executeUpdate();

            try (Statement getIdStatement = con.createStatement()) {
                ResultSet rs = getIdStatement.executeQuery("SELECT LAST_INSERT_ID()");
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void callAddtoCart(int ordersId, int shoeId, int customerId, int totalPrice, int dateId, int amount) {
        String query = "CALL AddToCart(?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             CallableStatement stmt = con.prepareCall(query)) {

            stmt.setInt(1, ordersId);
            stmt.setInt(2, shoeId);
            stmt.setInt(3, customerId);
            stmt.setInt(4, totalPrice);
            stmt.setInt(5, dateId);
            stmt.setInt(6, amount);

            stmt.executeUpdate();
            stmt.executeQuery();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Customers checkCustomer(int CustomerNumber, int Password) {
        String query = "SELECT CustomersId, CustomerNumber, Passwords FROM Customers WHERE CustomerNumber = ? AND Passwords = ?";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, CustomerNumber);
            stmt.setInt(2, Password);
            ResultSet rs = stmt.executeQuery();

            Customers c = null;

            while (rs.next()) {
                c = new Customers(rs.getInt("CustomerNumber"),
                        rs.getInt("Passwords"),
                        rs.getInt("CustomersId"));
            }
            return c;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Products> createProductList() throws SQLException {
        String query = "SELECT ShoeId,Price, Inventory, sizeId, brandId, colourId, ShoeCategoryId FROM shoe";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement()) {


            ResultSet rs = stmt.executeQuery(query);

            List<Products> productList = new ArrayList<>();
            int aTemp = 100;

            while (rs.next()) {
                Products pr = new Products();

                pr.setArticleNumber(aTemp);
                int shoeId = rs.getInt("ShoeId");
                pr.setShoeId(shoeId);
                int price = rs.getInt("Price");
                pr.setPrice(price);
                int inventory = rs.getInt("Inventory");
                pr.setInventory(inventory);
                int size = rs.getInt("sizeId");
                pr.setSizeId(size);
                int brand = rs.getInt("brandId");
                pr.setBrandId(brand);
                int colour = rs.getInt("colourId");
                pr.setColourId(colour);
                int ShoeCategory = rs.getInt("ShoeCategoryId");
                pr.setShoecategoryId(ShoeCategory);
                aTemp++;
                productList.add(pr);
            }
            return productList;
        }

    }

    public int findLatestOrderId() throws SQLException {
        String query = "SELECT OrdersId FROM orders";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement()) {


            ResultSet rs = stmt.executeQuery(query);

            List<Integer> orderList = new ArrayList<>();

            while (rs.next()) {
                int OrdersId = rs.getInt("OrdersId");
                orderList.add(OrdersId);
            }

            int maxOrderNumber = orderList.stream().mapToInt(Integer::intValue).max().orElse(0);
            if (maxOrderNumber == 0) {
                System.out.println("Något gick fel vid orderskapandet");
            }
            return maxOrderNumber;
        }
    }
}
