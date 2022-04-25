package com.tmgreyhat.api.security;

import java.util.Set;
import  com.google.common.collect.Sets;

import static com.tmgreyhat.api.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ,
            COURSE_WRITE,
            STUDENT_READ,
            STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissionSet;

    ApplicationUserRole(Set<ApplicationUserPermission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<ApplicationUserPermission> getPermissionSet() {
        return permissionSet;
    }
}
