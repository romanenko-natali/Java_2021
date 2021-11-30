package utility;

import model.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.concurrent.CompletionService;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExWithFuncInt {

    public static Predicate<Employee> isAdult(){
        return empl -> Period.between(empl.getBirthDate(), LocalDate.now()).getYears() >=18;
    }

    public static Predicate<Employee> isFirstNameContains(String name){
        return employee -> employee.getFirstName().contains(name);
    }

    public static Predicate<Employee> isLastNameContains(String name){
        return employee -> employee.getLastName().contains(name);
    }

    public static List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> condition){
        return employees.stream().filter(condition).collect(Collectors.toList());
    }

    public static Consumer<List<Employee>> modifyAllFirstName = employees -> {
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            String modifyingName = employee.getFirstName().substring(0, 1).toUpperCase() + employee.getFirstName().substring(1);
            employee.setFirstName(modifyingName);
        }
    };

    public static BiConsumer<Employee, String> modifyFirstNameWithPrefix = (employee, prefix) ->{
       employee.setFirstName(prefix+employee.getFirstName());
    };
}
