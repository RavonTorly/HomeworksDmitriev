public class Product {
    private String name;
    private String productionDate;
    private String manufacturer;
    private String country;
    private int price;
    private boolean statusBooking;

    public Product(String name, String productionDate, String manufacturer, String country, int price, boolean statusBooking) {
        this.name = name;
        this.productionDate = productionDate;
        this.manufacturer = manufacturer;
        this.country = country;
        this.price = price;
        this.statusBooking = statusBooking;
    }

    public void resultProduct() {
        System.out.println(name);
        System.out.println(productionDate);
        System.out.println(manufacturer);
        System.out.println(country);
        System.out.println(price);
        System.out.println(statusBooking);
    }
}

