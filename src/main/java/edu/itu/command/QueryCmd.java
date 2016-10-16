package edu.itu.command;

/**
 * Created by xuxu on 10/16/16.
 */
public class QueryCmd extends CommonCmd{
    public final char CMD_TYPE = CMD_TYPE_QUERY;
    public char cmdAction = Character.MAX_VALUE;


    public QueryCmd(char action) {
        this.cmdAction = action;
    }
}
