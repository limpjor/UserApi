package org.smartjob.com.model.request;

import lombok.Data;
import lombok.Getter;
import org.smartjob.com.model.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class UserRequest {

    Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@dominio\\.cl$", message = "El correo debe tener el dominio '@dominio.cl'. ")
    private String email;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",  message = "Password debe tener minimo 8 caracteres, la primera letra en mayusculas y un numero." )
    private String password;

    List<PhoneRequest> phones;
}
