public abstract class Item {
    private String name;
    private String UPC;
    private String company;
    private float price;
    private int count;

    public Item(String newName, String newUPC, 
               String newCompany, float newPrice
               int newCount) {
      name = newName;
      UPC = newUPC;
      company = newCompany;
      price = newPrice;
      count = newCount;
  
    }

    public String getName() {
        return this.name;
    }
   public String UPC() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}