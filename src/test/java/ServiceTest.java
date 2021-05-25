import main.java.City;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void ReadFileTest() throws FileNotFoundException {
        List<City> cities = Arrays.asList(
                new City(1, "Адыгейск", "Адыгея", "Южный", 12248, "1973"),
                new City(2, "Екатеринбург", "Свердловская область", "Северный", 1000000, "1720"),
                new City(3, "Москва", "Московская область", "Западный", 10000000, "1670"));

        assertEquals(cities, list);
    }

    @Test
    public void sortByNameTest() {
        List<City> sortedList = service.sortByName();
        List<City> expectedList = Arrays.asList(
                new City(1, "Адыгейск", "Адыгея", "Южный", 12248, "1973"),
                new City(2, "Екатеринбург", "Свердловская область", "Северный", 1000000, "1720"),
                new City(3, "Москва", "Московская область", "Западный", 10000000, "1670"));

        assertEquals(sortedList, expectedList);
    }

    @Test
    public void sortByDistrictTest() {
        List<City> sortedList = service.sortByDistrict();
        List<City> expectedList = Arrays.asList(
                new City(3, "Москва", "Московская область", "Западный", 10000000, "1670"),
                new City(2, "Екатеринбург", "Свердловская область", "Северный", 1000000, "1720"),
                new City(1, "Адыгейск", "Адыгея", "Южный", 12248, "1973"));

        assertEquals(sortedList, expectedList);
    }

    @Test
    public void MaxPopulationTest() {
        int expectedIndex = 2;
        int expectedPopulation = 10000000;

        int index = service.MaxPopulation();

        assertEquals(expectedIndex, index);
        assertEquals(expectedPopulation, list.get(index).getPopulation());
    }

    @Test
    public void GroupRegionTest() {
        Map<String, Long> map = new TreeMap<>();
        map.put("Адыгея", (long) 1);
        map.put("Свердловская область", (long) 1);
        map.put("Московская область", (long) 1);

        assertEquals(map, service.groupByRegion());
    }

}
