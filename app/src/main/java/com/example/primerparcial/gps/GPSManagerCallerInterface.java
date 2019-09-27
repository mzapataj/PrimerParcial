package com.example.primerparcial.gps;

import android.location.Location;

public interface GPSManagerCallerInterface {
    void needPermissions();

    void locationHasBeenReceived(Location location);

    void gpsErrorHasBeenThrown(Exception exception);
}
