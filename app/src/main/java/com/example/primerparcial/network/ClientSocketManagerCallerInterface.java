package com.example.primerparcial.network;

public interface ClientSocketManagerCallerInterface {
    void MessageReceived(String message);
    void ErrorFromSocketManager(Exception error);
}
