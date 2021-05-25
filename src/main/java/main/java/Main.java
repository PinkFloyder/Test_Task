package main.java;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public final static String FILE_ADDRESS = "//Users//a19215190//Desktop//text.txt";
    private final List<Entity> list = new LinkedList<>();

    public void readFile() throws FileNotFoundException {
        File file = new File(FILE_ADDRESS);
        Scanner scanner = new Scanner(file).useDelimiter(";");
        while (scanner.hasNext()) {
            list.add(new Entity(scanner.next(),
                    scanner.next(),
                    scanner.next(),
                    scanner.nextInt(),
                    scanner.next()));
        }
    }

    public List<Entity> sortByName() {
        return list.stream()
                .sorted(Comparator.comparing(x -> x.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Entity> sortByDistrict() {
        return list.stream()
                .sorted(Comparator.comparing(Entity::getDistrict)
                        .thenComparing(Entity::getName))
                .collect(Collectors.toList());
    }

     public @NotNull int getMax() {
        if (list.size() == 0)
            return -1;
        Entity[] array = list.toArray(new Entity[0]);
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
                .collect(Collectors.groupingBy(Entity::getRegion, Collectors.counting()));

    }

    public List<Entity> getList() {
        return list;
    }
}
