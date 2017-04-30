package io.webfolder.cdp.event.target;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.EventName;
import io.webfolder.cdp.type.target.TargetInfo;

/**
 * Issued when attached to target because of auto-attach or <code>attachToTarget</code> command
 */
@Domain("Target")
@EventName("attachedToTarget")
public class AttachedToTarget {
    private TargetInfo targetInfo;

    private Boolean waitingForDebugger;

    public TargetInfo getTargetInfo() {
        return targetInfo;
    }

    public void setTargetInfo(TargetInfo targetInfo) {
        this.targetInfo = targetInfo;
    }

    public Boolean isWaitingForDebugger() {
        return waitingForDebugger;
    }

    public void setWaitingForDebugger(Boolean waitingForDebugger) {
        this.waitingForDebugger = waitingForDebugger;
    }
}
