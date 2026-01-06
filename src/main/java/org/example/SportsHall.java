package org.example;

import java.util.HashMap;
import java.util.Map;

/*
  SportsHall представлява споделения ресурс.
  Всички входове (нишки) имат достъп до този обект.
  Синхронизацията се използва, за да се избегнат условия на състезание (race condition).
*/
public class SportsHall {

    /*
      Map, който пази максималния брой места за всяка категория.
      Използването на Map прави решението по-гъвкаво и разширяемо.
    */
    private final Map<String, Integer> maxSeatsByCategory = new HashMap<>();

    /*
      Map, който пази текущо заетите места за всяка категория.
      Това е споделено състояние между нишките.
    */
    private final Map<String, Integer> occupiedSeatsByCategory = new HashMap<>();

    /*
      Конструктор, в който се инициализират категориите
      и максималният брой места за всяка от тях.
    */
    public SportsHall() {
        maxSeatsByCategory.put("VIP", 20);
        maxSeatsByCategory.put("REGULAR", 50);
        maxSeatsByCategory.put("ECONOMY", 100);

        // Първоначално няма заети места
        for (String category : maxSeatsByCategory.keySet()) {
            occupiedSeatsByCategory.put(category, 0);
        }
    }

    /*
      Синхронизиран метод за настаняване на група зрители.
      Само една нишка може да изпълнява този метод в даден момент.
      Така се предотвратява препълване на залата.
    */
    public synchronized boolean seatGroup(String category, int count) {

        System.out.println(Thread.currentThread().getName()
                + " trying to accommodate a group of "
                + count + " people in category " + category);

        // Симулиране на време за обработка
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        int occupied = occupiedSeatsByCategory.get(category);
        int capacity = maxSeatsByCategory.get(category);

        /*
          Проверка дали има достатъчно свободни места.
          Ако няма – групата не се допуска.
        */
        if (occupied + count > capacity) {
            System.out.println("Not enough seats available in category " + category);
            return false;
        }

        // Актуализиране на заетите места
        occupiedSeatsByCategory.put(category, occupied + count);

        System.out.println("Group accommodated successfully in category " + category);
        return true;
    }

    /*
      Метод за отпечатване на крайното състояние на залата.
      Извиква се след като всички нишки са приключили работа.
    */
    public void printResult() {
        System.out.println("\nFINAL CONDITION OF THE HALL:");

        for (String category : maxSeatsByCategory.keySet()) {
            System.out.println(category + " seats taken: "
                    + occupiedSeatsByCategory.get(category));
        }
    }
}
