package Animals;

public abstract class Animal {
    protected String name;
    protected int runLimit;
    protected int swimLimit;
    private static int countAnimals = 0;

    public Animal(String name, int runLimit, int swimLimit) {
        this.name = name;
        this.runLimit = runLimit;
        this.swimLimit = swimLimit;
        countAnimals++;
    }

    public void run(int distance) {
        if (distance <= 0) {
            System.out.println(name + " - не может бежать");
        } else if (distance <= runLimit) {
            System.out.println(name + " - пробежал растояние: " + distance + "м.");
        } else {
            System.out.println(name + " - не может бежать " + distance + " на полную" + runLimit + " м.");
        }
    }

    public void swim(int distance) {
        if (swimLimit == 0) {
            System.out.println(name + " - не может плыть");
        } else if (distance <= swimLimit) {
            System.out.println(name + " - проплыл растояние: " + distance + "м.");
        } else {
            System.out.println(name + " - не может плыть " + distance + " на полную" + swimLimit + " м.");
        }
    }

    public static int getAnimalsCount() {
        return countAnimals;
    }

    public void eat(Bowl bowl, int amountTake) {
        System.out.println(name + " пытается поесть... ");
    }
}

