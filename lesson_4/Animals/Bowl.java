package Animals;

public class Bowl {
    private int foodCount;
    private final int capacity;

    public Bowl(int capacity) {
        this.capacity = capacity;
        this.foodCount = capacity;
    }

    public Bowl(int initialFood, int capacity) {
        this.capacity = capacity;
        this.foodCount = Math.min(initialFood, capacity);
    }

    public boolean takeFood(int amountТake) {
        if (amountТake < 0) {
            System.out.println("Нельзя взять с миски " + amountТake + " еды");
            return false;
        }
        if (foodCount >= amountТake) {
            foodCount -= amountТake;
            System.out.println("Взято " + amountТake + " еды. Осталось еды в миске " + foodCount);
            return true;
        } else {
            System.out.println("Недостаточно еды. Нужно больше" + amountТake + " , но в миске только " + foodCount);
            return false;
        }
    }

    public void addFood(int amount) {
        if (amount <= 0) {
            System.out.println("Нельзя добавить " + amount + " еды");
        }
        int newAmount = foodCount + amount;
        if (newAmount > capacity) {
            foodCount = capacity;
            System.out.println("Миска переполнена! Добавлено " + (capacity - foodCount) + ", миска полная");
        } else {
            foodCount = newAmount;
            System.out.println("Добавлено " + amount + " еды. Теперь в миске: " + foodCount);
        }
    }

    public int getFoodCount() {
        return foodCount;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return foodCount == 0;
    }

    public void fill() {
        foodCount = capacity;
        System.out.println("Миска наполнена до краев! Еды: " + foodCount);
    }
}
