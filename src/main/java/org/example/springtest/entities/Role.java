package org.example.springtest.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.springtest.common.UserRole;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
public class Role {

    @Id
    private int roleId;
    private UserRole roleName;

}
