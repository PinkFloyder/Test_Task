package main.java;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class ActiveProgram {

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();
        main.readFile();
        System.out.println("Что нужно, коллега?\n" +
                "1 - отсортировать по наименованию\n" +
                "2 - отсортировать по региональному округу\n" +
                "3 - поиск города с наибольшем кол-вом жителей\n" +
                "4 - количества городов в разрезе регионов");

        Scanner scanner = new Scanner(System.in);
        int response = scanner.nextInt();
        switch (response) {
            case 1 : {
                main.sortByName().forEach(System.out::println);
                break;
            }
            case 2: {
                main.sortByDistrict().forEach(System.out::println);
                break;
            }
            case 3: {
                System.out.println(main.getList().get(main.getMax()));
                break;
            }
            case 4: {
                Map<String, Long> map = main.groupByRegion();
                for (Map.Entry<String, Long> temp : map.entrySet()) {
                    System.out.println(temp.getKey() + " - " + temp.getValue());
                }
            }
            default:
                break;
        }
    }
}