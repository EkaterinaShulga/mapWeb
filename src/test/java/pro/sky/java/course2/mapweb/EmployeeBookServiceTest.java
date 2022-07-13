package pro.sky.java.course2.mapweb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.mapweb.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.mapweb.exception.EmployeeIncorrectInputStringData;
import pro.sky.java.course2.mapweb.exception.EmployeeNotFoundException;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeBookServiceTest {

    @MethodSource("employeeSurname")
    @ParameterizedTest
    public void upperCaseSurnameLetter(String surname, String expected) {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        org.assertj.core.api.Assertions.assertThat(employeeBookService.checkSurname(surname)).isEqualTo(expected);
    }

    @Test
    public void verifySurname() {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        Employee employee = new Employee("Ivanov", "Ivan", 100000, 1);
        Assertions.assertThrows(EmployeeIncorrectInputStringData.class, () -> employeeBookService.checkSurname("I0anov"));
    }

    @MethodSource("employeeName")
    @ParameterizedTest
    public void upperCaseNameLetter(String name, String expected) {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        org.assertj.core.api.Assertions.assertThat(employeeBookService.checkName(name)).isEqualTo(expected);
    }

    @Test
    public void verifyName() {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        Employee employee = new Employee("Ivanov", "Ivan", 100000, 1);
        Assertions.assertThrows(EmployeeIncorrectInputStringData.class, () -> employeeBookService.checkName("Iv3n"));
    }

    @Test
    public void putNotDouble() {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        employeeBookService.putEmployee("Ivanov", "Ivan", 100000, 1);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () ->
                employeeBookService.putEmployee("Ivanov", "Ivan", 100000, 1));
    }

    @Test
    public void getEmployeeNotFound() {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        employeeBookService.putEmployee("Ivanov", "Ivan", 100000, 1);
        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeBookService.getEmployee("Smirnov", "Oleg"));

    }

    @Test
    public void getEmployeePositive() {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        Employee employee1 = new Employee("Ivanov", "Ivan", 100000, 1);
        employeeBookService.putEmployee("Ivanov", "Ivan", 100000, 1);
        org.assertj.core.api.Assertions.assertThat(employeeBookService.getEmployee("Ivanov", "Ivan")).isEqualTo(employee1);

    }


    @Test
    public void remove() {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        Employee employee1 = new Employee("Ivanov", "Ivan", 100000, 1);
        employeeBookService.putEmployee("Ivanov", "Ivan", 100000, 1);
        org.assertj.core.api.Assertions.assertThat(employeeBookService.removeEmployee("Ivanov", "Ivan")).isEqualTo(employee1);
        Assertions.assertThrows(EmployeeNotFoundException.class, () ->
                employeeBookService.removeEmployee("Smirnov", "Oleg"));

    }

    @Test
    public void getAllEmployee() {
        Employee employee1 = new Employee("Ivanov", "Ivan", 100000, 1);
        Employee employee2 = new Employee("Smirnov", "Oleg", 150000, 2);
        Employee employee3 = new Employee("Sidorov", "Roman", 120000, 3);

        EmployeeBookService employeeBookService = new EmployeeBookService();

        employeeBookService.putEmployee(employee1.getSurnameEmployee(), employee1.getNameEmployee(), employee1.getSalary(), employee1.getDepartment());
        employeeBookService.putEmployee(employee2.getSurnameEmployee(), employee2.getNameEmployee(), employee2.getSalary(), employee2.getDepartment());
        employeeBookService.putEmployee(employee3.getSurnameEmployee(), employee3.getNameEmployee(), employee3.getSalary(), employee3.getDepartment());

        Map<String, Employee> expectedEmployeesMap = employeeBookService.getAll();

        Map<String, Employee> actualEmployeesMap = new HashMap<>();
        actualEmployeesMap.put("IvanovIvan", employee1);
        actualEmployeesMap.put("SmirnovOleg", employee2);
        actualEmployeesMap.put("SidorovRoman", employee3);

        assertEquals(expectedEmployeesMap, actualEmployeesMap);


    }

    @Test
    public void getAllEmployeeNotNull() {
        EmployeeBookService employeeBookService = new EmployeeBookService();
        Map<String, Employee> expectedEmployeesMap = employeeBookService.getAll();
        assertNotNull(expectedEmployeesMap);
    }


    private static Stream<Arguments> employeeSurname() {
        return Stream.of(
                Arguments.of("Smirnov", "Smirnov"),
                Arguments.of("sidorov", "Sidorov")
        );
    }

    private static Stream<Arguments> employeeName() {
        return Stream.of(
                Arguments.of("oleg", "Oleg"),
                Arguments.of("Semen", "Semen")
        );
    }
}
