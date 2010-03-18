package com.markupware.whiteboard4j.server;

import com.markupware.whiteboard4j.protocol.WhiteBoard;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RunWBServer {
  private static final String name = "TextualWhiteBoard";

  public static void main(String[] args) {
    try {
      WhiteBoard board = new TextualWhiteBoard(name);
      WhiteBoard stub =
          (WhiteBoard) UnicastRemoteObject.exportObject(board, 0);
      Registry registry = LocateRegistry.getRegistry();
      registry.rebind(name, stub);
      log(name + "is now bound");
    } catch (Exception e) {
      err("Problem in deploying " + name);
      e.printStackTrace();
    }
  }

  private static void log(String msg) {
    System.out.println(msg);
  }

  private static void err(String msg) {
    System.err.println(msg);
  }
}
