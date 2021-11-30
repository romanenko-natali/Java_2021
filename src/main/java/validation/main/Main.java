package main;

import model.Employee;
import model.enums.Genre;
import org.apache.commons.lang3.StringUtils;
import utility.ExWithFuncInt;
import validation.model.Book;

import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        try{
//            Book book = new Book.Builder("0-12-34567ч8-9", "Moby-Dick")
//                    .genre(Genre.ADVENTURE_FICTION)
//                    .author("Herman Melville")
//                    .published(Year.of(1851))
//                    .description("description omitted for brevity").build();
//        }
//        catch (IllegalArgumentException e){
//            System.out.println(e.getMessage());
//        }
        //System.out.println(book);

            try{
                Book book2 = new Book.Builder("0-12-345679y8-9", "Moby-Dick")
                        .genre(Genre.ADVENTURE_FICTION)
                        .author("Herman Melville")
                        .published(Year.of(2021))
                        .description("description omitted for brevity").build();
                System.out.println(book2);
            }
            catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

//       System.out.println(book2);

        Employee empl1 = Employee.builder().firstName("first Empl")
                .lastName("first ...")
                .birthDate(LocalDate.now().minusYears(5))
                .build();
        Employee empl2 = Employee.builder().firstName("Seconds empl")
                .lastName("Last name of second")
                .birthDate(LocalDate.now().minusYears(20))
                .build();
        //System.out.println(ExWithFuncInt.filterEmployees(Arrays.asList(empl1, empl2), ExWithFuncInt.isAdult()));
        System.out.println("OR");
        System.out.println(ExWithFuncInt.filterEmployees(Arrays.asList(empl1, empl2), ExWithFuncInt.isFirstNameContains("first")
                .or(ExWithFuncInt.isLastNameContains("Last"))));

        //Stream.of(empl1, empl2);


       Arrays.asList(empl1, empl2).stream().forEach(employee -> {
               employee.setFirstName(employee.getFirstName().substring(0, 1).toUpperCase() + employee.getFirstName().substring(1));
           });

       List<Employee> copyEmployee = Arrays.asList(empl1, empl2);
       ExWithFuncInt.modifyAllFirstName.accept(copyEmployee);

        //System.out.println(copyEmployee);


        //System.out.println("prefix _");
        copyEmployee.stream().forEach(empl -> ExWithFuncInt.modifyFirstNameWithPrefix.accept(empl, "_"));
        //System.out.println(copyEmployee);

        //---------------------------------------------------------

        copyEmployee.stream().map(element -> {
            element.setFirstName("prefix"+element.getFirstName());
            return null;
        }).collect(Collectors.toList());

        StringUtils.capitalize("jjj");

        //copyEmployee.stream().map(Employee::getFirstName).forEach(System.out::println);


        //System.out.println("SORTED");
        //System.out.println(copyEmployee.stream().sorted());
        //System.out.println("End of sorted");


        copyEmployee.stream().sorted(Comparator.comparing(Employee::getBirthDate, Comparator.reverseOrder()));
        copyEmployee.stream().sorted(Comparator.nullsFirst(Comparator.comparing(Employee::getFirstName)));


        //-----------------------------------------

        Map<Integer, Employee> employeesMap = copyEmployee.stream().collect(Collectors.toMap(Employee::hashCode, Function.identity()));
        System.out.println(employeesMap);
        employeesMap.put(1, empl2);
        employeesMap.put(2,empl1);
        System.out.println(employeesMap.getOrDefault(5, new Employee("FN", "LN", LocalDate.now())));
        System.out.println("First");
        employeesMap.values().stream().forEach(System.out::println);
        System.out.println("Second");
        employeesMap.keySet().stream().forEach(key -> System.out.println(employeesMap.get(key)));

        Optional<Employee> employeeOptional = employeesMap.values().stream().filter(empl ->
                StringUtils.containsIgnoreCase(empl.getLastName(), "first")).findFirst();

        //employeeOptional.isPresent();
        //System.out.println("First with first in last name " + employeeOptional.get());


        Employee empl3 =  Employee.builder().firstName("Third empl name")
                .lastName("Third empl surname")
                .birthDate(LocalDate.now().minusYears(20))
                .build();


        Map<Integer, List<Employee>> mapByYear = Arrays.asList(empl1, empl2, empl3).stream()
                .collect(Collectors
                .groupingBy(employee -> employee.getBirthDate().getYear()));
        System.out.println("Map by year: " + mapByYear);

        Map<Integer, Long> mapByYearAndCount = Arrays.asList(empl1, empl2, empl3).stream()
                .collect(Collectors.groupingBy(employee -> employee.getBirthDate().getYear(),
                                Collectors.counting()));

        System.out.println("Map by year: " + mapByYearAndCount);

        Map<Boolean, List<Employee>> mapByAdult = Arrays.asList(empl1, empl2, empl3).stream()
                .collect(Collectors.partitioningBy(ExWithFuncInt.isAdult()));
        System.out.println(mapByAdult);





    }

}
