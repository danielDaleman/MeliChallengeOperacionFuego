package com.meli.challengemeli.transversal.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValidationError {
    String fieldName;
    String messageError;

    public ValidationError(String fieldName,String messageError) {
        this.fieldName = fieldName;
        this.messageError = messageError;
    }
}
