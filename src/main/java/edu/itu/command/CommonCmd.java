package edu.itu.command;

/**
 * Created by xuxu on 10/16/16.
 */
public abstract class CommonCmd {
    public final int CMD_TYPE_QUERY = 0x01;
    public final int CMD_TYPE_SET = 0x10;

    public final int CMD_ACTION_SET_PAPAMETER = 0;
    public final int CMD_ACTION_SET_TIME = 1;


    public int cmdType  = Character.MAX_VALUE;
    public int cmdAction = Character.MAX_VALUE;

}
