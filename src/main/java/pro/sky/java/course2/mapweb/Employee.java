package pro.sky.java.course2.mapweb;

import java.util.Objects;

class Employee {
    private final String nameEmployee;
    private final String surnameEmployee;
    private Integer salary;
    private int department;

    public Employee(String surnameEmployee, String nameEmployee, Integer salary, int department) {
        this.surnameEmployee = surnameEmployee;
        this.nameEmployee = nameEmployee;
        this.salary = salary;
        this.department = department;
    }

    public Employee(String surnameEmployee, String nameEmployee) {
        this.surnameEmployee = surnameEmployee;
        this.nameEmployee = nameEmployee;
    }

    public String getInformaitioForKey(String surnameEmployee, String nameEmployee) {
        return getSurnameEmployee() + getNameEmployee();
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public String getSurnameEmployee() {
        return surnameEmployee;
    }

    public Integer getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Сотрудник " +
                "имя = " + nameEmployee + "," +
                " фамилия = " + surnameEmployee + "," +
                " з/п =" + salary + "," +
                " департамент = " + department + " " + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(nameEmployee, employee.nameEmployee) && Objects.equals(surnameEmployee, employee.surnameEmployee)
                && Objects.equals(salary, employee.salary) && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameEmployee, surnameEmployee, salary, department);
    }
}