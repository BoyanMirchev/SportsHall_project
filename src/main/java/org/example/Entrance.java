package org.example;

 /*
       Входът представлява един вход към залата.
       Всеки вход се изпълнява в отделна нишка.
 */

public class Entrance implements Runnable {

 // Да се добави спецификатора за достъп Private

    //Вече добавените константи за бройката!!!
    private static final int VIP_GROUP_SIZE = 5;
    private static final int REGULAR_GROUP_SIZE = 10;
    private static final int ECONOMY_GROUP_SIZE = 20;

    private final SportsHall hall;

     // Конструктора, който създава и стартира нова нишка.


    public Entrance(SportsHall hall) {
        this.hall = hall;
    }

    /*
         Входна точка на нишката.
         Всеки вход обработва съответните групи Вип, Нормален и economy.
     */

    @Override
    public void run() {
        hall.seatGroup("VIP", VIP_GROUP_SIZE);
        hall.seatGroup("REGULAR", REGULAR_GROUP_SIZE);
        hall.seatGroup("ECONOMY", ECONOMY_GROUP_SIZE);
    }
}