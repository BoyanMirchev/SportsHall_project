package org.example;

/*
         Главната нишка:
        - Създава споделения обект SportsHall
        - Стартира всички входни нишки
        - Изчаква да приключат, като ползва join()
 */

public class Main {

    public static void main(String[] args) {


        // Споделен ресурс
        SportsHall hall = new SportsHall();

        //Правят се 4 нишки или Входа

        Entrance e1 = new Entrance("Entrance-1", hall);
        Entrance e2 = new Entrance("Entrance-2", hall);
        Entrance e3 = new Entrance("Entrance-3", hall);
        Entrance e4 = new Entrance("Entrance-4", hall);


        /*
           join() прави така, че главната нишка изчаква,
           докато всички входни нишки приключат изпълнението си.
         */
        try {
            e1.t.join();
            e2.t.join();
            e3.t.join();
            e4.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted");
        }

        // Извеждане на крайния резултат след приключване на всички нишки
        hall.printResult();
    }
}
