package com.example.primerparcial.broadcast;

public interface BroadcastManagerCallerInterface {

    void MessageReceivedThroughBroadcastManager(
            String channel, String type, String message);

    void ErrorAtBroadcastManager(Exception error);

}
