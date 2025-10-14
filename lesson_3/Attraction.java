public class Attraction {
    private String attractionName;
    private String workSchedule;
    private double price;

    public Attraction(String attractionName, String workSchedule, double price) {
        this.attractionName = attractionName;
        this.workSchedule = workSchedule;
        this.price = price;
    }

    public String getAttractionName() {
        return attractionName;
    }

    public String getWorkSchedule() {
        return workSchedule;
    }

    public double getPrice() {
        return price;
    }
}
