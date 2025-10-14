public class Park {
    private String namePark;
    private String workingHour;
    private Attraction[] attractions;
    private int attractionCount;

    public Park(String namePark, String workingHour, int maxAttractions) {
        this.namePark = namePark;
        this.workingHour = workingHour;
        this.attractions = new Attraction[maxAttractions];
        this.attractionCount = 0;
    }
    public void addAttraction (Attraction attraction){
        if(attractionCount < attractions.length){
            attractions[attractionCount] = attraction;
            attractionCount++;
        }else {
            System.out.println("Нельзя добавить больше аттракционов! Максимум: " + attractions.length);
        }
    }

    public void printParkInfo() {
        System.out.println(namePark);
        System.out.println(workingHour);
        for (int i = 0; i < attractionCount; i++) {
            Attraction attraction = attractions[i];
            System.out.println((i + 1) + ". " + attractions[i].getAttractionName() + " - " + attractions[i].getWorkSchedule() + " - " + attractions[i].getPrice() + "руб.");
        }
    }


}
