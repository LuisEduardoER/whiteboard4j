package com.markupware.whiteboard4j.protocol;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WhiteBoard extends Remote {
  String getName() throws RemoteException;
  void join(UserAgent client) throws RemoteException;
  void write(Message msg, Participant from) throws RemoteException;
  void leave(String nick) throws RemoteException;
}
