package POJOS;

public class Customers {

   int CustomerNumber;
    //private int PersonId;
    int Passwords;
    int customerId;


    public Customers(int customerNumber, int passwords, int customerId) {
        CustomerNumber = customerNumber;
        Passwords = passwords;
        this.customerId = customerId;
    }

    public Customers(int customerNumber, int passwords) {
        this.CustomerNumber = customerNumber;
        this.Passwords = passwords;
    }
    public int getCustomerNumber() {
        return CustomerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        CustomerNumber = customerNumber;
    }

    public int getPasswords() {
        return Passwords;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
