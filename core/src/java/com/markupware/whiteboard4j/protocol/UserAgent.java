package com.markupware.whiteboard4j.protocol;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserAgent extends Remote {
  Participant getParticipant() throws RemoteException;
  void writeMessage(String msg) throws RemoteException;
}
