package edu.itu.command;

/**
 * Created by xuxu on 10/17/16.
 */
public interface Packageable {
    public final char START_FLAG = 0X68;
    public final char END_FLAG = 0x88;

    public abstract char[] doPackage();
}
