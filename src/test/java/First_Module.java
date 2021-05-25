import main.java.Entity;
import main.java.Main;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class First_Module {

     private Main main;
     private List<Entity> list;

    @BeforeEach
    public void start() throws FileNotFoundException {
        main = new Main();
        main.readFile();
        list = main.getList();
    }

    @Test
    public void firstTask() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(Main.FILE_ADDRESS)).useDelimiter(";");
        int count = 0;
        while (scanner.hasNext()) {
            scanner.next();
            count++;
        }
        Assertions.assertEquals((count / 5), list.size());
    }

    @Test
    public void secondTask1() {
        List<Entity> sortedList = main.sortByName();
        Entity min = list.stream().min(Comparator.comparing(Entity::getName)).get();
        Entity max = list.stream().max(Comparator.comparing(Entity::getName)).get();

        Assertions.assertEquals(min, sortedList.get(0));
        Assertions.assertEquals(max, sortedList.get(sortedList.size() - 1));
    }

    @Test
    public void secondTask2() {
        List<Entity> sortedList = main.sortByDistrict();
        Entity min = list.stream().min(Comparator.comparing(Entity::getDistrict).thenComparing(Entity::getName)).get();
        Entity max = list.stream().max(Comparator.comparing(Entity::getDistrict).reversed().thenComparing(Entity::getName).reversed()).get();

        Assertions.assertEquals(min, sortedList.get(0));
        Assertions.assertEquals(max, sortedList.get(sortedList.size() - 1));
    }

    @Test
    public void threeTask() {
        int index = main.getMax();

        Entity entity = list.stream()
                .max(Comparator.comparing(Entity::getPopulation))
                .get();

        Assertions.assertEquals(index, list.indexOf(entity));
        Assertions.assertEquals(list.get(index), entity);
    }

    @Test
    public void fourTask() {
        Map<String, Long> map = new TreeMap<>();
        Entity entity;
        for (int i = 0; i < list.size(); i++) {
            entity = list.get(i);
            if (map.containsKey(entity.getRegion())) {
                map.put(entity.getRegion(), map.get(entity.getRegion()) + 1);
            } else {
                map.put(entity.getRegion(), (long) 1);
            }
        }
        Assertions.assertEquals(map, main.groupByRegion());
    }

}
