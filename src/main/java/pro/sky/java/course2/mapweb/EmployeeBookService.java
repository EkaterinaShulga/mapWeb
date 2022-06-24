package pro.sky.java.course2.mapweb;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.mapweb.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.mapweb.exception.EmployeeNotFoundException;

import java.util.*;

@Service
public class EmployeeBookService {

    Map<String, Employee> employeesMap = new HashMap<>();

    public Employee putEmployee(String surnameEmployee, String nameEmployee, Integer salary, int department) {
        Employee employee = new Employee(surnameEmployee, nameEmployee, salary, department);

        if (employeesMap.containsKey(employee.getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new EmployeeAlreadyAddedException();
        }
        employeesMap.put(employee.getInformaitioForKey(surnameEmployee, nameEmployee), employee);
        return employee;
    }

    public Employee getEmployee(String surnameEmployee, String nameEmployee) {
        Employee employee = new Employee(surnameEmployee, nameEmployee);
        if (!employeesMap.containsKey(employee.getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new EmployeeNotFoundException();
        }
        return employeesMap.get(employee.getInformaitioForKey(surnameEmployee, nameEmployee));
    }

    public Employee removeEmployee(String surnameEmployee, String nameEmployee) {
        Employee employee = new Employee(surnameEmployee, nameEmployee);
        if (!employeesMap.containsKey(employee.getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new EmployeeNotFoundException();
        }
        return employeesMap.remove(employee.getInformaitioForKey(surnameEmployee, nameEmployee));
    }

    public Map<String, Employee> getAll() {
        return new HashMap<>(employeesMap);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}




