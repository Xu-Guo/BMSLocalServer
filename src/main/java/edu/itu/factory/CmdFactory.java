package edu.itu.factory;

import edu.itu.command.SetCmd;

/**
 * Created by xuxu on 10/16/16.
 */
public class CmdFactory {

    public static SetCmd createSetTimeCommand(long time){
        SetCmd sc = new SetCmd(time);
        return sc;
    }

}
