package com.markupware.whiteboard4j.client;


import com.markupware.whiteboard4j.protocol.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RunWBClient {

  private static final String SERVER_BINDING_NAME = "TextualWhiteBoard";
  private BufferedReader reader;
  private String inputLine;
  private String userNick;


  private RunWBClient() {
    reader = new BufferedReader(new InputStreamReader(System.in));    
  }

  private void requestEntry() {
    userNick = grabUserNick();
    Participant user = new WBUser(userNick);
    WBClient client = new WBClient(user);
    try {
      UnicastRemoteObject.exportObject(client, 0);
      Registry registry = LocateRegistry.getRegistry();
      WhiteBoard board = (WhiteBoard) registry.lookup(SERVER_BINDING_NAME);
      board.join(client);
      display("You just jumped in");
      while(!(inputLine = reader.readLine()).equals("quit")) {
        if (inputLine.length() > 0) {
          board.write(new WBMessage(inputLine, Message.Type.USER), user);
        }        
      }
      board.leave(userNick);
      display("You just left");
    } catch (Exception e) {
      err("Problem occurred in WhiteBoard client");
      e.printStackTrace();
    }
  }

  private String grabUserNick() {
    String nick = "TheDoctor";
    System.out.print("Please enter your nick name: ");
    try {
      nick = reader.readLine();
    } catch (IOException ioe) {
      System.out.println(ioe.getMessage());
      ioe.printStackTrace();
      System.exit(1);
    }
    return nick;
  }

  public static void main(String[] args) {
    new RunWBClient().requestEntry();
  }

  public void display(String msg) {
    System.out.println(msg);
  }

  private static void err(String msg) {
    System.err.println(msg);
  }
}
