package edu.itu.command;

/**
 * Created by xuxu on 10/16/16.
 */
public class QueryCmd extends CommonCmd implements Packageable{


    public QueryCmd(char action) {
        this.cmdAction = action;
    }

    @Override
    public int[] doPackage() {
        return new int[0];
    }
}
