package com.balia.be.service.dto;

public class UserQuery {
    private Boolean active;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserQuery{" + "active=" + active + '}';
    }
}
