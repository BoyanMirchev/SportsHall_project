package org.example;

/*
  SportsHall представлява споделения ресурс.
  Всички входове - нишки имат достъп до SportsHall едновременно.
  Синхронизацията се използва, за да се избегнат условия на състезание помежду им.
 */

public class SportsHall {

    // Задава се максимален брой налични места по категория

    int vipSeats = 20;
    int regularSeats = 50;
    int economySeats = 100;

    /*
      Тук е синхронизиран метода, защото
      само една нишка може да изпълнява този метод едновременно.
      Така се гарантира правилно актуализиране на споделените данни.
     */

    synchronized void seatGroup(String category, int count) {

        System.out.println(Thread.currentThread().getName()
                + " trying to accommodate a group of "
                + count + " people in a category " + category);

        // Симулиране на времето за обработка
        try {
            Thread.sleep(300); // малко изчакване
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        // Проверява се наличността и се актуализират местата тук
        if (category.equals("VIP") && vipSeats >= count) {
            vipSeats -= count;
        } else if (category.equals("REGULAR") && regularSeats >= count) {
            regularSeats -= count;
        } else if (category.equals("ECONOMY") && economySeats >= count) {
            economySeats -= count;
        } else {
            System.out.println("Not enough space for category " + category);
        }
    }

    /*
      Отпечатването на крайното положение на залата
      Извиква се след като всички нишки приключат изпълнение.
     */

    void printResult() {
        System.out.println("\nFINAL CONDITION OF THE HALL:");
        System.out.println("VIP seats taken: " + (20 - vipSeats));
        System.out.println("REGULAR seats taken: " + (50 - regularSeats));
        System.out.println("ECONOMY seats taken: " + (100 - economySeats));
    }
}