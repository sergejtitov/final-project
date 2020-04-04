package htp.controller.errors;

import lombok.Data;

@Data
public class ErrorMessage {
    private Long errorCode;

    private String message;

    public ErrorMessage(String message) {

        this.message = message;
    }
}
