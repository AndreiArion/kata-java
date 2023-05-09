/*
 * Copyright (C) by Courtanet, All Rights Reserved.
 */
package net.courtanet.tripservice;

import java.util.Collections;
import java.util.List;


public class TripService {

    /**
     * Check if user has friends
     */
    public static Boolean hasFriend(User user) {
        List<User> friends = user.getFriends()
        for (User f : friends) {
            if (f.equals(loggedUser)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Return list of trips for a logged User with Friends
     */
    public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
        User loggedUser = new UserSession().getInstance().getLoggedUser();
        if (loggedUser == null) {
            throw new UserNotLoggedInException();
        }
        if TripService.hasFriend(user) {
            return TripDAO.findTripsByUser(user);
        }
        return Collections.EMPTY_LIST;
    }
}
