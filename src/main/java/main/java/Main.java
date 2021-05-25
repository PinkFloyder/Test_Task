package main.java;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Map.Entry;
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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Что нужно, коллега?\n" +
                "1 - отсортировать по наименованию\n" +
                "2 - отсортировать по региональному округу\n" +
                "3 - поиск города с наибольшем кол-вом жителей\n" +
                "4 - количества городов в разрезе регионов");

        while (!scanner.hasNext("q")) {
            int response = scanner.nextInt();
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
                    int index = service.getMax();
                    if (index == -1) {
                        System.out.println("Список пуст");
                    } else {
                        System.out.println("[" + index + "] = " + service.getList().get(index).getPopulation());
                    }
                    break;
                }
                case 4: {
                    Map<String, Long> map = service.groupByRegion();
                    for (Entry<String, Long> temp : map.entrySet()) {
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
