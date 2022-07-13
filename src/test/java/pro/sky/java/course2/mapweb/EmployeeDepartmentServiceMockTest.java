package pro.sky.java.course2.mapweb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.mapweb.exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeDepartmentServiceMockTest {


    @Mock
    private EmployeeBookService employeeBookService;

    @InjectMocks
    private EmployeeDepartmentService employeeDepartmentService;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Ivanov", "Ivan", 200000, 1);
        Employee employee2 = new Employee("Smirnov", "Oleg", 150000, 1);
        Employee employee3 = new Employee("Sidorov", "Roman", 120000, 2);
        Employee employee4 = new Employee("Petrov", "Semen", 100000, 2);

        Map<String, Employee> employees = new HashMap<>();
        employees.put("IvanovIvan", employee1);
        employees.put("SmirnovOleg", employee2);
        employees.put("SidorovRoman", employee3);
        employees.put("PetrovSemen", employee4);


        when(employeeBookService.getAll()).thenReturn(employees);
    }

    @MethodSource("giveEmployeesWithMaxSalary")
    @ParameterizedTest
    public void employeeWithMaxSalary(int department, Employee expected) {
        Assertions.assertThat(employeeDepartmentService.getEmployeeWithMaxSalary(department)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMaxSalaryNotFoundDepartment() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeDepartmentService.getEmployeeWithMaxSalary(3));
    }

    @MethodSource("giveEmployeesWithMinSalary")
    @ParameterizedTest
    public void employeeWithMinSalary(int department, Employee expected) {
        Assertions.assertThat(employeeDepartmentService.getEmployeeWithMinSalary(department)).isEqualTo(expected);
    }

    @Test
    public void employeeWithMinSalaryNotFoundDepartment() {
        Assertions.assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeDepartmentService.getEmployeeWithMinSalary(4));
    }

    @MethodSource("allEmployeesInDepartment")
    @ParameterizedTest
    public void allEmployeesInDepartmentPositiveTest(int department, List<Employee> expected) {
        Assertions.assertThat(employeeDepartmentService.getAllEmployeesInDepartment(department)).containsExactlyElementsOf(expected);
    }

    @MethodSource("employeesByDepartments")
    @ParameterizedTest
    public void EmployeeByDepartments(List<Employee> expected) {
        Assertions.assertThat(employeeDepartmentService.getAllEmployees()).containsExactlyElementsOf(expected);
    }

    private static Stream<Arguments> giveEmployeesWithMaxSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("Ivanov", "Ivan", 200000, 1)),
                Arguments.of(2, new Employee("Sidorov", "Roman", 120000, 2))
        );
    }

    private static Stream<Arguments> giveEmployeesWithMinSalary() {
        return Stream.of(
                Arguments.of(1, new Employee("Smirnov", "Oleg", 150000, 1)),
                Arguments.of(2, new Employee("Petrov", "Semen", 100000, 2))
        );
    }

    private static Stream<Arguments> allEmployeesInDepartment() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee("Ivanov", "Ivan", 200000, 1),
                        new Employee("Smirnov", "Oleg", 150000, 1))),
                Arguments.of(2, List.of(new Employee("Petrov", "Semen", 100000, 2),
                        new Employee("Sidorov", "Roman", 120000, 2))),
                Arguments.of(3, Collections.emptyList())
        );
    }

    private static Stream<Arguments> employeesByDepartments() {
        return Stream.of(
                Arguments.of(List.of(new Employee("Ivanov", "Ivan", 200000, 1),
                        new Employee("Smirnov", "Oleg", 150000, 1),
                        new Employee("Petrov", "Semen", 100000, 2),
                        new Employee("Sidorov", "Roman", 120000, 2)))
        );
    }
}




