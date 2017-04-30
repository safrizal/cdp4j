package io.webfolder.cdp.event.target;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;

/**
 * Issued when detached from target for any reason (including <code>detachFromTarget</code> command)
 */
@Domain("Target")
@EventName("detachedFromTarget")
public class DetachedFromTarget {
    private String targetId;

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }
}
