package Animals;

public class Cat extends Animal {
    private static int catCount = 0;
    private boolean isSatiety;

    public Cat(String namesCat) {
        super(namesCat, 200, 0);
        catCount++;
    }

    @Override
    public void eat(Bowl bowl, int amount) {
        System.out.println(name + " подошел к миске...");
        if (bowl.takeFood(amount)) {
            System.out.println(name + "поел" + amount + " еды");
            isSatiety = true;
        } else {
            System.out.println(name + " не смог поесть");
            isSatiety = false;
        }
    }

    public boolean isSatiety() {
        return isSatiety;
    }

    public String getSatietyInfo() {
        if (isSatiety) {
            return "сытый";
        } else {
            return "голодный";
        }
    }

    public static int getCatCount() {
        return catCount;
    }

    public static void printCatsSatiety(Cat[] cats) {
        System.out.println("Состояние сытости котов:");
        for (int i = 0; i < cats.length; i++) {
            System.out.println(cats[i].name + " - " + cats[i].getSatietyInfo());
        }
    }
}
