package Animals;

public class Dog extends Animal {
    private static int dogCount = 0;

    public Dog(String nameDog) {
        super(nameDog, 500, 10);
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

}
