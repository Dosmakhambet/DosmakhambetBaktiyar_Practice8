package com.dosmakhambetbbaktiyar_practice8.model;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    EVENTS_READ("events:read"),
    EVENTS_WRITE("events:write"),
    FILES_READ("files:read"),
    FILES_WRITE("files:write"),
    USERS_READALL("users:readall");

    private final String permission;


    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
