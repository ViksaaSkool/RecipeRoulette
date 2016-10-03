package com.recipe.roulette.app.events;

/**
 * Created by varsovski on 03-Oct-16.
 */

public class InternetConnectionEvent {
    private final boolean hasConnection;
    //additionally types of connection!

    public InternetConnectionEvent(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }

    public boolean isConnected() {
        return hasConnection;
    }
}
