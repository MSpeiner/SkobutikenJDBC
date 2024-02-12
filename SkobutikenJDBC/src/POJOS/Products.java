package POJOS;

public class Products {
    int shoeId;
    int articleNumber;
    int price;
    int inventory;
    int sizeId;
    int brandId;
    int colourId;
    int shoecategoryId;
    int size;
    String brand;
    String colour;
    String shoecategory;


    public Products() {
    }

    public Products(int shoeId, int articleNumber, int price, int inventory, int sizeId, int brandId, int colourId, int shoecategoryId) {
        this.shoeId = shoeId;
        this.articleNumber = articleNumber;
        this.price = price;
        this.inventory = inventory;
        this.sizeId = sizeId;
        this.brandId = brandId;
        this.colourId = colourId;
        this.shoecategoryId = shoecategoryId;
    }

    public Products(int articleNumber, int price, int inventory, int size, String brand, String colour, String shoecategory) {
        this.articleNumber = articleNumber;
        this.price = price;
        this.inventory = inventory;
        this.size = size;
        this.brand = brand;
        this.colour = colour;
        this.shoecategory = shoecategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getColourId() {
        return colourId;
    }

    public void setColourId(int colourId) {
        this.colourId = colourId;
    }

    public int getShoecategoryId() {
        return shoecategoryId;
    }

    public void setShoecategoryId(int shoecategoryId) {
        this.shoecategoryId = shoecategoryId;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public int getSize() {
        return size;
    }


    public String getBrand() {
        return brand;
    }


    public String getColour() {
        return colour;
    }


    public String getShoecategory() {
        return shoecategory;
    }

    public int getShoeId() {
        return shoeId;
    }

    public void setShoeId(int shoeId) {
        this.shoeId = shoeId;
    }
}
