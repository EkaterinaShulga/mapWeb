package pro.sky.java.course2.mapweb;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Service
public class EmployeeBookService {
    Map<String, Employee> employeesMap = new HashMap<>();

    public Employee getEmployee(String surnameEmployee, String nameEmployee) {
        Employee employee = new Employee(surnameEmployee,nameEmployee);
        if (!employeesMap.containsKey(employee.getInformaitioForKey(surnameEmployee,nameEmployee ))) {
            throw new RuntimeException("Сотрудника с таким номером нет в списке");
        }
        return employeesMap.get(employee.getInformaitioForKey(surnameEmployee, nameEmployee));
    }

    public Employee putEmployee(String surnameEmployee, String nameEmployee) {
        Employee employee = new Employee(surnameEmployee, nameEmployee);

        if (employeesMap.containsKey(employee.getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new RuntimeException("Сотрудник " + surnameEmployee + " " + nameEmployee + " уже есть в списке");
        }
        employeesMap.put(employee.getInformaitioForKey(surnameEmployee, nameEmployee), employee);
        return  employee;


    }

    public Employee removeEmployee(String surnameEmployee, String nameEmployee) {
        Employee employee = new Employee(surnameEmployee, nameEmployee);
        if (!employeesMap.containsKey(employee.getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new RuntimeException("Сотрудника с таким номером нет в списке");
        }
        return employeesMap.remove(employee.getInformaitioForKey(surnameEmployee, nameEmployee));
    }

    public Map<String, Employee> getAll() {
        return new HashMap<>(employeesMap);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeBookService that = (EmployeeBookService) o;
        return Objects.equals(employeesMap, that.employeesMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeesMap);
    }

}
