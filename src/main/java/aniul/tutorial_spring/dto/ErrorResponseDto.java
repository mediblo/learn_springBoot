package aniul.tutorial_spring.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
    private int status;
    private String message;

    public ErrorResponseDto(int status, String message){
        this.status = status;
        this.message = message;
    }
}
