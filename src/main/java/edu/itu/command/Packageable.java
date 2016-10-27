package edu.itu.command;

/**
 * Created by xuxu on 10/17/16.
 */
public interface Packageable {
    public final int START_FLAG = 0X68;
    public final int END_FLAG = 0x88;

    public abstract int[] doPackage();
}
