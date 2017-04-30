package io.webfolder.cdp.event.target;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

/**
 * Notifies about new protocol message from attached target
 */
@Domain("Target")
@EventName("receivedMessageFromTarget")
public class ReceivedMessageFromTarget {
    private String targetId;

    private String message;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
