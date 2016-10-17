package edu.itu.command;

/**
 * Created by xuxu on 10/16/16.
 */
public class QueryCmd extends CommonCmd{


    public QueryCmd(char action) {
        this.cmdAction = action;
    }

    @Override
    public char[] doPackage() {
        return new char[0];
    }
}
