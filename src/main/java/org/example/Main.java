package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
  Главен клас (Main):
  - Създава споделения ресурс SportsHall
  - Създава ExecutorService с фиксиран брой нишки = 4 (4 входа)
  - Пуска минимум 10 задачи (групи), които се изпълняват
  - Изчаква всички задачи да приключат
  - Накрая отпечатва броя на заетите места по категории
*/
public class Main {

    public static void main(String[] args) {

        // 1) Създава се споделеният ресурс (залата)
        SportsHall hall = new SportsHall();

        /*
          2) Създава се пул от нишки с фиксиран размер 4.
             Това отговаря на изискването: 4 входа (4 нишки).
        */
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        /*
          3) Пускат се минимум 10 задачи (групи).
             Всяка задача представлява една група зрители, която трябва да бъде настанена.
             Задачите се разпределят автоматично между 4-те нишки от пула.
        */
        int tasksCount = 10;
        for (int i = 1; i <= tasksCount; i++) {
            executorService.submit(new Entrance(hall));
        }

        /*
          4) shutdown() казва на ExecutorService, че няма да се подават повече задачи.
             Той ще довърши вече подадените и тогава ще спре.
        */
        executorService.shutdown();

        /*
          5) Изчакваме всички задачи да приключат.
             awaitTermination() чака до определено време в случая 10 секунди.
             Ако задачите са малки, те ще приключат много по-бързо.
        */
        try {
            boolean finished = executorService.awaitTermination(10, TimeUnit.SECONDS);

            // Ако не са приключили в рамките на времето, може да прекратим принудително
            if (!finished) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            // Ако главната нишка е прекъсната, прекратяваме опашката от задачи
            executorService.shutdownNow();
            System.out.println("The main thread was shutted down.");
        }

        // 6) След като всички задачи са приключили, отпечатваме крайния резултат
        hall.printResult();
    }
}
