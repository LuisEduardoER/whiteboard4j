package com.markupware.whiteboard4j.protocol;

import java.io.Serializable;

public interface Message extends Serializable {
  enum Type {
    USER,
    SYSTEM,
    COMMAND
  }  
  String getPlainText();
  Type getType();
}
