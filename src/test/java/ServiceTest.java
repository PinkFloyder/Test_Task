import main.java.City;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.*;

public class ServiceTest {

     private main.java.Service service;
     private List<City> list;

    @BeforeEach
    public void start() throws FileNotFoundException {
        service = new main.java.Service();
        service.readFile();
        list = service.getList();
    }

    @Test
    public void testReadFile() throws FileNotFoundException {
        List<City> cities = Arrays.asList(
                new City(1, "Адыгейск", "Адыгея", "Южный", 12248, "1973"),
                new City(2, "Екатеринбург", "Свердловская область", "Северный", 1000000, "1720"),
                new City(3, "Москва", "Московская область", "Западный", 10000000, "1670"));

        Assertions.assertEquals(cities, list);
    }

    @Test
    public void testSortByName() {
        List<City> sortedList = service.sortByName();
        City min = list.stream().min(Comparator.comparing(City::getName)).get();
        City max = list.stream().max(Comparator.comparing(City::getName)).get();

        Assertions.assertEquals(min, sortedList.get(0));
        Assertions.assertEquals(max, sortedList.get(sortedList.size() - 1));
    }

    @Test
    public void testSortByDistrict() {
        List<City> sortedList = service.sortByDistrict();
        City min = list.stream().min(Comparator.comparing(City::getDistrict).thenComparing(City::getName)).get();
        City max = list.stream().max(Comparator.comparing(City::getDistrict).reversed().thenComparing(City::getName).reversed()).get();

        Assertions.assertEquals(min, sortedList.get(0));
        Assertions.assertEquals(max, sortedList.get(sortedList.size() - 1));
    }

    @Test
    public void testMaxPopulation() {
        int index = service.getMax();

        City entity = list.stream()
                .max(Comparator.comparing(City::getPopulation))
                .get();

        Assertions.assertEquals(index, list.indexOf(entity));
        Assertions.assertEquals(list.get(index), entity);
    }

    @Test
    public void testGroupRegion() {
        Map<String, Long> map = new TreeMap<>();
        City entity;
        for (int i = 0; i < list.size(); i++) {
            entity = list.get(i);
            if (map.containsKey(entity.getRegion())) {
                map.put(entity.getRegion(), map.get(entity.getRegion()) + 1);
            } else {
                map.put(entity.getRegion(), (long) 1);
            }
        }
        Assertions.assertEquals(map, service.groupByRegion());
    }

}
