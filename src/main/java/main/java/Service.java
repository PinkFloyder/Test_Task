package main.java;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Service {

    public final static String FILE_ADDRESS = "//Users//a19215190//Desktop//text.txt";
    private final List<City> list = new LinkedList<>();

    public void readFile() throws FileNotFoundException {
        File file = new File(FILE_ADDRESS);
        Scanner scanner = new Scanner(file).useDelimiter(";");
        while (scanner.hasNext()) {
            list.add(new City(scanner.next(),
                    scanner.next(),
                    scanner.next(),
                    scanner.nextInt(),
                    scanner.next()));
        }
    }

    public List<City> sortByName() {
        return list.stream()
                .sorted(Comparator.comparing(x -> x.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<City> sortByDistrict() {
        return list.stream()
                .sorted(Comparator.comparing(City::getDistrict)
                        .thenComparing(City::getName))
                .collect(Collectors.toList());
    }

     public @NotNull int getMax() {
        if (list.size() == 0)
            return -1;
        City[] array = list.toArray(new City[0]);
        int index = -1;
        int maxPopulation = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (array[i].getPopulation() > maxPopulation) {
                maxPopulation = array[i].getPopulation();
                index = i;
            }
        }
        return index;
    }

     public Map<String, Long> groupByRegion() {
        return  list.stream()
                .collect(Collectors.groupingBy(City::getRegion, Collectors.counting()));

    }

    public List<City> getList() {
        return list;
    }
}
