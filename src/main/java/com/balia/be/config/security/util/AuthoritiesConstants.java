package com.balia.be.config.security.util;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String TEAM_LEADER = "Team Leader";
    public static final String TEAM_MEMBER = "Team Member";
    
    private AuthoritiesConstants() {
    }
}
