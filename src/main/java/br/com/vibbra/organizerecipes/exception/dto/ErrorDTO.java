package br.com.vibbra.organizerecipes.exception.dto;

import br.com.vibbra.organizerecipes.exception.AppException;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String error;

    public ErrorDTO(Exception exception) {
        super();
        if(exception instanceof AppException) {
            AppException appException = (AppException) exception;
            this.error = appException.getError();
        } else {
            this.error = exception.getMessage();
        }
    }
}