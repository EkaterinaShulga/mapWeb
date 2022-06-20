package pro.sky.java.course2.mapweb;

import java.util.Objects;

public class Employee {
    private String nameEmployee;
    private String surnameEmployee;

    public Employee(String surnameEmployee, String nameEmployee) {
        this.surnameEmployee = surnameEmployee;
        this.nameEmployee = nameEmployee;
    }
    public String getInformaitioForKey(String surnameEmployee, String nameEmployee){
        String keyMap = getSurnameEmployee() + getNameEmployee() ;
        return   keyMap;
    }
    public String getNameEmployee() {
        return nameEmployee;
    }
    public String getSurnameEmployee() {
        return surnameEmployee;
    }

    @Override
    public String toString(){
        return  getSurnameEmployee() + " " + getNameEmployee() ;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(nameEmployee, employee.nameEmployee) && Objects.equals(surnameEmployee, employee.surnameEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameEmployee, surnameEmployee);
    }
}
