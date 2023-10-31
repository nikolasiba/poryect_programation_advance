package co.edu.uniquindio.proyecto.Exception;

import co.edu.uniquindio.proyecto.Dto.MessageDTO;
import co.edu.uniquindio.proyecto.Dto.ValidationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.Binding;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageDTO<String>> generalException(Exception exception){
        return ResponseEntity.internalServerError().body(new MessageDTO<>(true, exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageDTO> validationException(MethodArgumentNotValidException exception){

        List<ValidationDTO> errors = new ArrayList<>();
        BindingResult results = exception.getBindingResult();

        for (FieldError item:results.getFieldErrors()) {
            errors.add(new ValidationDTO(item.getField(),item.getDefaultMessage()) );
        }

        return ResponseEntity.badRequest().body(new MessageDTO<>(true, errors));

    }


}
