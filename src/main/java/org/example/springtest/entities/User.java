package org.example.springtest.entities;

import lombok.*;
import org.example.springtest.common.UserRole;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int roleId;

    public String getRoleName() {
        if (this.roleId>0){
        return UserRole.values()[this.roleId-1].name();}
        return "None";
    }

}
