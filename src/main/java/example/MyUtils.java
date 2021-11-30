package example;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyUtils {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1,2,2,3,-7);
            List<Integer> list = stream.collect(Collectors.toList());

        System.out.println(list.stream()
                    .distinct()
                    .filter(Objects::nonNull)
                    .filter(i -> Collections.frequency(list, i) > 1)
                    .sorted()
                    .collect(Collectors.toList()));
    }
}
