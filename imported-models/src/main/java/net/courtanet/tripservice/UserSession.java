/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package net.courtanet.tripservice;

public class UserSession {


    UserSession() {
    }

    public UserSession getInstance() {
        return this;
    }

    public User getLoggedUser() {
        throw new RuntimeException();
    }
}
