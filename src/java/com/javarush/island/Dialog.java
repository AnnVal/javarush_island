package com.javarush.island;

import java.util.Scanner;

public class Dialog {
    private Island island;
    public static Scanner scanner = new Scanner(System.in);
    public static Scanner intScanner = new Scanner(System.in);

    public Dialog(Island island) {
        this.island = island;
    }

    public void start(){
        System.out.println("Вы в программе, которая имитирует жизнь на острове, состоящем из квадратов");
        System.out.println("Изначальный размер острова 20 на 20 квадратов");
        if(askAboutChangingSettings()){
            askWidth();
            askHeight();
        }
        askToStartSimulation();
        System.out.println("Пока!=)");
    }
//По идее, Диалог не должен запускать симуляцию, но как тогда связать пользователя и запуск жизни на остров?
    private void askToStartSimulation() {
        System.out.println("Вы хотите запустить симуляцию? y/n");
        String answer = scanner.nextLine();
        if("y".equals(answer)){
            System.out.println( "Поехали! \n");
            island.watchLifeOnIsland();
        }


    }

    private void askHeight() {
        System.out.println("Введете новую высоту");
        int height = intScanner.nextInt();
        if (height <=0){
            System.out.println("Остров не может быть с отрицательными параметрами. Повторите ввод.");
            askHeight();
        }
        else if(height > 20) {
            System.out.println("Остров будет слишком большим. Вычисления займут много времени. Введите высоту <=20");
            askHeight();
        }
        else
            island.setFieldHeight(height);
        System.out.println("Для острова задана высота "+ height);
    }

    private void askWidth() {
        System.out.println("Введете новую ширину");
       int width = intScanner.nextInt();
        if (width <=0){
            System.out.println("Остров не может быть с отрицательными параметрами. Повторите ввод.");
            askWidth();
        }
        else if(width > 100) {
            System.out.println("Остров будет слишком большим. Вычисления займут много времени. Введите ширину <=100");
            askWidth();
        }
        else
            island.setFieldWidth(width);
        System.out.println("Для острова задана ширина "+ width);

    }
    

    private boolean askAboutChangingSettings() {
        System.out.println("Вы хотите поменять параметры? y/n");
        String answer = scanner.nextLine();
        if("y".equals(answer))
            return true;
        else
            return false;
    }
}
