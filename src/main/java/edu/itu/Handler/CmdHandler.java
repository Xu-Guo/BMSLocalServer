package edu.itu.Handler;

import edu.itu.command.CommonCmd;

/**
 * Created by xuxu on 10/16/16.
 * The Cmdhandler class handls command queue which sends command in sequence without conflict.
 *
 */
public abstract class CmdHandler {
    public abstract void handleCmd(CommonCmd rcmd);
}
