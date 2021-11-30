package utility;

import model.Employee;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class ExamplesParallelStream {
    private static void timingTest(Stream<Employee> employeeStream){
        long startTime = System.nanoTime();
        employeeStream.forEach(e -> doSlowOp());
        long endTime = System.nanoTime();
        System.out.println("start time: " + startTime);
        System.out.println("execution time: " + deltaSeconds(startTime, endTime));

    }
     private static double deltaSeconds(long start, long end){
        return ((end-start)/1000000000);
     }

    static void doSlowOp(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException exc){
            // nothing
        }
    }

    public static void main(String[] args) {
        Employee empl1 = Employee.builder().firstName("first Empl")
                .lastName("first ...")
                .birthDate(LocalDate.now().minusYears(5))
                .build();
        Employee empl2 = Employee.builder().firstName("Seconds empl")
                .lastName("Last name of second")
                .birthDate(LocalDate.now().minusYears(20))
                .build();

        int numProcessors = Runtime.getRuntime().availableProcessors();
        timingTest(Arrays.asList(empl1, empl2).stream());

        System.out.println("Parallel version on "+numProcessors + "s-core machine");
        timingTest(Arrays.asList(empl1, empl2).stream().parallel());

    }
}
