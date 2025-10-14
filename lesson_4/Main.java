import Animals.Animal;
import Animals.Bowl;
import Animals.Cat;
import Animals.Dog;

public class Main {
    public static void main(String[] args) {
        //Ex1
        {
            System.out.println("Задание 1:");
            System.out.println("Изначальное количество животных:");
            System.out.println("Животные: " + Animal.getAnimalsCount());
            System.out.println("Котов: " + Cat.getCatCount());
            System.out.println("Собак: " + Dog.getDogCount());
            System.out.println();
            System.out.println("Кот:");
            Cat catBarsik = new Cat("Барсик");
            Cat catSima = new Cat("Сима");
            Cat catMurzik = new Cat("Мурзик");
            Cat catVeterok = new Cat("Ветерок");
            System.out.println("Бег котов: ");
            catBarsik.run(0);
            catBarsik.run(150);
            catBarsik.run(250);
            catMurzik.run(0);
            catMurzik.run(150);
            catMurzik.run(250);
            catSima.run(0);
            catSima.run(150);
            catSima.run(250);
            catVeterok.run(0);
            catVeterok.run(150);
            catVeterok.run(250);
            System.out.println("\nПлаванье котов: ");
            catBarsik.swim(100);
            catBarsik.swim(200);
            catSima.swim(100);
            catSima.swim(200);
            catMurzik.swim(100);
            catMurzik.swim(200);
            catVeterok.swim(100);
            catVeterok.swim(200);

            System.out.println("\nСобака:");
            Dog dogSharik = new Dog("Шарик");
            Dog dogBobik = new Dog("Бобик");
            Dog dogTypa = new Dog("Тяпа");
            Dog dogFantik = new Dog("Фантик");
            System.out.println("Бег собак: ");
            dogSharik.run(0);
            dogSharik.run(150);
            dogSharik.run(500);
            dogSharik.run(600);
            dogBobik.run(0);
            dogBobik.run(150);
            dogBobik.run(500);
            dogBobik.run(600);
            dogTypa.run(0);
            dogTypa.run(150);
            dogTypa.run(500);
            dogTypa.run(600);
            dogFantik.run(0);
            dogFantik.run(150);
            dogFantik.run(500);
            dogFantik.run(600);
            System.out.println("\nПлаванье собак: ");
            dogSharik.swim(0);
            dogSharik.swim(5);
            dogSharik.swim(10);
            dogSharik.swim(15);
            dogBobik.swim(0);
            dogBobik.swim(5);
            dogBobik.swim(10);
            dogBobik.swim(15);
            dogTypa.swim(0);
            dogTypa.swim(5);
            dogTypa.swim(10);
            dogTypa.swim(15);
            dogFantik.swim(0);
            dogFantik.swim(5);
            dogFantik.swim(10);
            dogFantik.swim(15);
            System.out.println("\nПосле созданных животных:");
            System.out.println("Животные: " + Animal.getAnimalsCount());
            System.out.println("Котов: " + Cat.getCatCount());
            System.out.println("Собак: " + Dog.getDogCount());

            Bowl bowl = new Bowl(50, 25);
            System.out.println("Создана миска. Вместимность: " + bowl.getCapacity() + ", еды: " + bowl.getFoodCount());
            System.out.println();

            Cat[] cats = {
                    new Cat("Барсик"),
                    new Cat("Сима"),
                    new Cat("Васька"),
                    new Cat("Мурзик"),
                    new Cat("Пушок"),
            };
            System.out.println("Информация перед едой о котах");
            Cat.printCatsSatiety(cats);

            System.out.println("\n Коты пытаются поесть, по 10 еды каждый");
            for (int i = 0; i < cats.length; i++) {
                cats[i].eat(bowl, 10);
            }

            System.out.println("\nСытость котов, первая попытка");
            Cat.printCatsSatiety(cats);

            System.out.println("\nДобавляем еду в миску");
            bowl.addFood(30);

            System.out.println("\n Голодные коты, которые пытаются поесть снова");
            for (int i = 0; i < cats.length; i++) {
                if (!cats[i].isSatiety()) {
                    cats[i].eat(bowl, 10);
                }
            }

            System.out.println("Завершающая часть сытости котов");
            Cat.printCatsSatiety(cats);
        }
    }
}