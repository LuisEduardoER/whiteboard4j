package com.markupware.whiteboard4j.server;

import com.markupware.whiteboard4j.protocol.*;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

public class TextualWhiteBoard implements WhiteBoard {

  private String name;
  private Map<String, UserAgent> clients;
  
  public TextualWhiteBoard(String name) {
    this.name = name;
    clients = new HashMap();
  }

  public String getName() {
    return name;
  }

  public void join(UserAgent client) {
    try {
      Participant user = client.getParticipant();
      String nick = user.getNickname();
      broadcast(nick + " stopped by.");
      clients.put(nick, client);
    } catch (RemoteException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  public void write(Message msg, Participant user) {
    broadcast(quote(msg.getPlainText(), user));
  }

  public void leave(String nick) {
    if (clients.remove(nick) != null) {
      broadcast(nick + " hit the road.");
    }
  }

  private String quote(String msg, Participant from) {
    return (from.getNickname() + ": " + msg);
  }
  
  private void broadcast(String msg) {
    try {
      for (UserAgent agent: clients.values())
        agent.writeMessage(msg);
    } catch (RemoteException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }
}
