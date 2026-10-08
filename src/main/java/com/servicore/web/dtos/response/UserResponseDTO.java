package com.servicore.web.dtos.response;

public class UserResponseDTO {
    
    private final Long id;
    private final String name;
    private final String lastName;
    private final String email;
    private final Long roleId;
    private final String roleName;

    public UserResponseDTO(
        Long id,
        String name,
        String lastName,
        String email,
        Long roleId,
        String roleName
    ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
 
    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

}
