package pro.sky.java.course2.mapweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/departments")
public class EmployeeBookController {
    private final EmployeeBookService employeeBookService;
    private final EmployeeDepartmentService employeeDepartmentService;


    public EmployeeBookController(EmployeeBookService employeeBookService, EmployeeDepartmentService employeeDepartmentService) {
        this.employeeBookService = employeeBookService;
        this.employeeDepartmentService = employeeDepartmentService;
    }


    @GetMapping("/get")
    public String getEmployee(@RequestParam("surname") String surnameEmployee,
                              @RequestParam("name") String nameEmployee) {
        return "в списке есть  " + employeeBookService.getEmployee(surnameEmployee, nameEmployee);
    }

    @GetMapping("/put")
    public String putEmployee(@RequestParam("surname") String surnameEmployee,
                              @RequestParam("name") String nameEmployee,
                              @RequestParam("salary") Integer salary,
                              @RequestParam("department") int department) {
        return "в список добавлен  " + employeeBookService.putEmployee(surnameEmployee, nameEmployee, salary, department);
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("surname") String surnameEmployee,
                                 @RequestParam("name") String nameEmployee) {
        return "из списка удален  " + employeeBookService.removeEmployee(surnameEmployee, nameEmployee);
    }

    @GetMapping("/getAll")
    public String getAll() {
        return String.valueOf(employeeBookService.getAll());
    }

    @GetMapping("/max-salary")
    public String getEmployeeWithMaxSalary(@RequestParam("number") int department) {
        return "Максимальную зарплату получает " + employeeDepartmentService.getEmployeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public String getEmployeeWithMinSalary(@RequestParam("number") int department) {
        return "Минимальную зарплату получает " + employeeDepartmentService.getEmployeeWithMinSalary(department);
    }

    @GetMapping("/all-department")
    public String getAllEmployeesInDepartment(@RequestParam("number") int department) {
        return "В отделе числятся: " + employeeDepartmentService.getAllEmployeesInDepartment(department);
    }

    @GetMapping("/all")
    public String getAllEmployees() {
        return "Все сотрудики с распределением по отделам : " + employeeDepartmentService.getAllEmployees();
    }

}
