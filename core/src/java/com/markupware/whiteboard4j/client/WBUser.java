package com.markupware.whiteboard4j.client;

import com.markupware.whiteboard4j.protocol.Participant;

import java.io.Serializable;

public class WBUser implements Participant {

  private String nick;
  private static final long serialVersionUID = -3309267612701655337L;

  public WBUser(String nick) {
    this.nick = nick;
  }

  public String getNickname() {
    return nick;
  }

  public void setNick(String nick) {
    this.nick = nick;
  }

  public void displayMessage(String msg) {
    System.out.println(msg);
  }

  @Override
  public String toString() {
    return "WBUser{" +
        "nick='" + nick + '\'' +
        '}';
  }
}
