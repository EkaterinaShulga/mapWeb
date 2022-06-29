package pro.sky.java.course2.mapweb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeIncorrectInputStringData extends RuntimeException {
        public EmployeeIncorrectInputStringData(){
            super();
        }


    }
