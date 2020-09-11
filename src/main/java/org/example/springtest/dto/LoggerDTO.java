package org.example.springtest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
public class LoggerDTO {
    private int id;
    private int changerId;
    private String changerLogin;
    private int changedUserId;
    private String changedUserLogin;
    private String date;
    private String message;
    private String changes;
}
