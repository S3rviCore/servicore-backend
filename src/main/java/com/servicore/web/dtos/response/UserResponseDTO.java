package com.servicore.web.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {

    private final Long id;
    private final String name;
    private final String lastName;
    private final String email;
    private final Long roleId;
    private final String roleName;
    private final String temporaryPassword;

    public UserResponseDTO(
        Long id,
        String name,
        String lastName,
        String email,
        Long roleId,
        String roleName
    ) {
        this(id, name, lastName, email, roleId, roleName, null);
    }

    public UserResponseDTO(
        Long id,
        String name,
        String lastName,
        String email,
        Long roleId,
        String roleName,
        String temporaryPassword
    ) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.roleId = roleId;
        this.roleName = roleName;
        this.temporaryPassword = temporaryPassword;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public Long getRoleId() { return roleId; }
    public String getRoleName() { return roleName; }
    public String getTemporaryPassword() { return temporaryPassword; }

}