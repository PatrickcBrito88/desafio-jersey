package org.brito.desafiojersey.enums;

public enum ERole {

    ADMIN("admin"),
    USER("user");

    private String role;

    ERole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static ERole toString(String role) {
        for (ERole e : ERole.values()) {
            if (e.getRole().equalsIgnoreCase(role)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Role não encontrada: " + role);
    }
}
