package org.example.springtest.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserChangesLog {
    String signature;
    String changerLogin;
    Object[] arguments;
}
