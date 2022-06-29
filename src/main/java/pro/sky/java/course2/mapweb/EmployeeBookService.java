package pro.sky.java.course2.mapweb;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.mapweb.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.mapweb.exception.EmployeeIncorrectInputStringData;
import pro.sky.java.course2.mapweb.exception.EmployeeNotFoundException;

import java.util.*;

@Service
public class EmployeeBookService {

    Map<String, Employee> employeesMap = new HashMap<>();

    public String checkSurname(String surname){
        String newSurname = "";
        if(!StringUtils.isAlpha(surname) ){
            throw new EmployeeIncorrectInputStringData();
            }
        if (!Character.isUpperCase(surname.charAt(0))){
          return   newSurname =  StringUtils.capitalize(surname);
        } return surname;
    }
    public String checkName(String name){
        String newName = "";
        if(!StringUtils.isAlpha(name)){
            throw new EmployeeIncorrectInputStringData();
        }
         if (!Character.isUpperCase(name.charAt(0))){
           newName = StringUtils.capitalize(name);
           return newName;
        }
        return name;
    }

    public String getInformaitioForKey(String surnameEmployee, String nameEmployee) {
        return  surnameEmployee + nameEmployee;
    }
    public Employee putEmployee(String surnameEmployee, String nameEmployee, Integer salary, int department) {
        Employee employee = new Employee(checkSurname(surnameEmployee), checkName(nameEmployee), salary, department);
        if (employeesMap.containsKey(getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new EmployeeAlreadyAddedException();
        } else {
            employeesMap.put(getInformaitioForKey(surnameEmployee, nameEmployee), employee);

        }
        return employee;
    }

    public Employee getEmployee(String surnameEmployee, String nameEmployee) {
        Employee employee = new Employee(surnameEmployee, nameEmployee);
        if (!employeesMap.containsKey(getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new EmployeeNotFoundException();
        }
        return employeesMap.get(getInformaitioForKey(surnameEmployee, nameEmployee));
    }

    public Employee removeEmployee(String surnameEmployee, String nameEmployee) {
        Employee employee = new Employee(surnameEmployee, nameEmployee);
        if (!employeesMap.containsKey(getInformaitioForKey(surnameEmployee, nameEmployee))) {
            throw new EmployeeNotFoundException();
        }
        return employeesMap.remove(getInformaitioForKey(surnameEmployee, nameEmployee));
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




