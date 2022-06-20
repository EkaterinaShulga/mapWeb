package pro.sky.java.course2.mapweb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class EmployeeBookController {
    private final EmployeeBookService employeeBookService;

    public EmployeeBookController(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    @GetMapping("/get")
    public String getEmployee(@RequestParam("surname") String surnameEmployee,
                              @RequestParam("name") String nameEmployee) {
        return "в списке есть сотрудник " + employeeBookService.getEmployee(nameEmployee, surnameEmployee);
    }

    @GetMapping("/put")
    public String putEmployee(@RequestParam("surname") String surnameEmployee,
                              @RequestParam("name") String nameEmployee) {
        return "в список добавлен сотрудиник " + employeeBookService.putEmployee(surnameEmployee, nameEmployee);
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam("surname") String surnameEmployee,
                                 @RequestParam("name") String nameEmployee) {
        return "из списка удален сотрудник " + employeeBookService.removeEmployee(surnameEmployee, nameEmployee);
    }

    @GetMapping("/getAll")
    public Map<String, Employee> getAll() {
        return employeeBookService.getAll();
    }
}
