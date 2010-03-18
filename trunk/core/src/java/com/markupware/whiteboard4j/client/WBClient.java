package com.markupware.whiteboard4j.client;

import com.markupware.whiteboard4j.protocol.Participant;
import com.markupware.whiteboard4j.protocol.UserAgent;

public class WBClient implements UserAgent {

  private Participant user;

  public WBClient(Participant user) {
    this.user = user;
  }

  public void writeMessage(String msg) {
    System.out.println(msg);
  }

  public Participant getParticipant() {
    return user;
  }
}
