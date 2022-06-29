package pro.sky.java.course2.mapweb;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.mapweb.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.mapweb.exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeDepartmentService {

    static EmployeeBookService employeeBookService;

    public EmployeeDepartmentService(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    public Employee getEmployeeWithMaxSalary(int number) {
        List<Employee> list = new ArrayList<>(employeeBookService.getAll().values());
        List<Employee> filterDepartment = list.stream()
                .filter(s -> s.getDepartment() == number)
                .collect(Collectors.toList());
        return filterDepartment.stream().max(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee getEmployeeWithMinSalary(int number) {
        List<Employee> list = new ArrayList<>(employeeBookService.getAll().values());
        List<Employee> filterDepartment = list.stream()
                .filter(s -> s.getDepartment() == number)
                .collect(Collectors.toList());
        return filterDepartment.stream().min(Comparator.comparing(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getAllEmployeesInDepartment(int number) {
        List<Employee> list = new ArrayList<>(employeeBookService.getAll().values());
        return list.stream()
                .filter(s -> s.getDepartment() == number)
                .collect(Collectors.toList());

    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>(employeeBookService.getAll().values());
        Comparator<Employee> comparator = new DepartmentComparator();
        list.sort(comparator);
        return list;
    }
}

    class DepartmentComparator implements Comparator<Employee> {
        @Override
        public int compare(Employee o1, Employee o2) {
            return o1.getDepartment() - o2.getDepartment();
        }
    }
