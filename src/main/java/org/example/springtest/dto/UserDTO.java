package org.example.springtest.dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



/** TO DO:
 * put logic from User Here **/

@Setter
@Getter
@ToString
public class UserDTO {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int roleId;
}
