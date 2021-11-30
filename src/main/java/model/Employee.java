package model;

import model.enums.Position;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Position position;

    public Employee(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    private Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "employee: " + firstName + "," + lastName + " ";
    }

    public static class EmployeeBuilder{
        private String firstName;
        private String lastName;
        private LocalDate birthDate;

        public EmployeeBuilder firstName(String firstName){
            this.firstName = firstName; return  this;
        }

        public EmployeeBuilder lastName(String lastName){
            this.lastName = lastName; return  this;
        }

        public EmployeeBuilder birthDate(LocalDate birthDate){
            this.birthDate = birthDate; return  this;
        }

        public Employee build(){
            return  new Employee(this.firstName, this.lastName, this.birthDate);
        }
    }

    public static EmployeeBuilder builder(){
        return new EmployeeBuilder();
    }
}
