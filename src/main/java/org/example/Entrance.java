package org.example;

 /*
       Входът представлява един вход към залата.
       Всеки вход се изпълнява в отделна нишка.
 */

public class Entrance implements Runnable {

    String name;
    SportsHall hall;
    Thread t;

     // Конструкторът създава и стартира нова нишка.


    Entrance(String name, SportsHall hall) {
        this.name = name;
        this.hall = hall;

        // Създава се нова нишка с Runnable
        t = new Thread(this, name);
        t.start();
    }

    /*
         Входна точка на нишката.
         Всеки вход обработва съответните групи Вип, Нормален и economy.
     */

    @Override
    public void run() {
        hall.seatGroup("VIP", 5);
        hall.seatGroup("REGULAR", 10);
        hall.seatGroup("ECONOMY", 20);
    }
}