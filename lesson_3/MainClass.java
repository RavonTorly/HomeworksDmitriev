public class MainClass {
    public static void main(String[] args) {
        //Ex1
        {
            System.out.println("Задание 1:");
            Product product = new Product("Stone", "11-11-2025", "Dmitriev", "Russia", 3000, true);
            product.resultProduct();
        }
        //Ex2
        {
            System.out.println("\nЗадание 2:");
            Product[] productsArray = new Product[5];
            productsArray[0] = new Product("Samsung S25 Ultra", "01.02.2025", "Samsung Corp.", "Korea", 5599, true);
            productsArray[1] = new Product("Xiaomi 11T Pro", "08.05.2025", "Xiaomi Corp.", "Chine", 2200, false);
            productsArray[2] = new Product("iPhone 12 Pro", "10.07.2025", "Apple Corp.", "USA", 8000, true);
            productsArray[3] = new Product("MacBook Air M6", "11.01.2025", "Apple Corp.", "USA", 7000, false);
            productsArray[4] = new Product("Xiaomi 13T Pro", "16.12.2025", "Xiaomi Corp.", "Chine", 3300, true);
            for (int i = 0; i < productsArray.length; i++) {
                System.out.println("\nТовар №" + (i + 1));
                productsArray[i].resultProduct();
            }
        }
        //Ex3
        {
            System.out.println("\nЗадание 3:");
            Park park = new Park("Dmitri Park","c 8:00 до 19:00", 5);
            park.addAttraction(new Attraction("Горки", "c 8:30 до 18:30", 200));
            park.addAttraction(new Attraction("Карусель", "c 8:30 до 18:30", 150));
            park.addAttraction(new Attraction("Колесо обозрения", "c 8:30 до 18:30", 300));
            park.addAttraction(new Attraction("UFO", "c 8:30 до 18:30", 250));
            park.printParkInfo();
        }
    }
}
