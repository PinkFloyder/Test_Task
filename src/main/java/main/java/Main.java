package main.java;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {
        Service service = new Service();
        try {
            service.readFile();
        } catch (FileNotFoundException e ) {
            System.out.println("Файла нет");
            e.printStackTrace();
        }
        while (true) {
            System.out.println("Что нужно, коллега?\n" +
                    "1 - отсортировать по наименованию\n" +
                    "2 - отсортировать по региональному округу\n" +
                    "3 - поиск города с наибольшем кол-вом жителей\n" +
                    "4 - количества городов в разрезе регионов");

            Scanner scanner = new Scanner(System.in);
            int response;
            try {
                response = scanner.nextInt();
            } catch (InputMismatchException e) {
                return;
            }
            switch (response) {
                case 1: {
                    service.sortByName().forEach(System.out::println);
                    break;
                }
                case 2: {
                    service.sortByDistrict().forEach(System.out::println);
                    break;
                }
                case 3: {
                    System.out.println(service.getList().get(service.getMax()));
                    break;
                }
                case 4: {
                    Map<String, Long> map = service.groupByRegion();
                    for (Map.Entry<String, Long> temp : map.entrySet()) {
                        System.out.println(temp.getKey() + " - " + temp.getValue());
                    }
                    break;
                }
                default:
                    return;
            }
        }
    }
}
