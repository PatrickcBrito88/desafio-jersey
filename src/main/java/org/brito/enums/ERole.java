package org.brito.enums;

public enum ERole {

    ADMIN("admin"),
    USER("user");

    private String role;

    ERole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
