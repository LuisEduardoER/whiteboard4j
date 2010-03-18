package com.markupware.whiteboard4j.client;

import com.markupware.whiteboard4j.protocol.Message;

public class WBMessage implements Message {

  private static final long serialVersionUID = -8152193555600327841L;
  private String text;
  private Message.Type type;

  public WBMessage(String text, Message.Type type) {
    this.text = text;
    this.type = type;
  }

  public String getPlainText() {
    return text;
  }

  public Message.Type getType() {
    return type;
  }
}
