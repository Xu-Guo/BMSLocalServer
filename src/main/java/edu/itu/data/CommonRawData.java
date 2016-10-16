package edu.itu.data;

import edu.itu.Handler.DataHandler;

import java.util.List;

/**
 * Created by xuxu on 10/16/16.
 */
public abstract class CommonRawData {
    public abstract void setData(List<Integer> list);
    public abstract void notifyDataHandler(DataHandler dh, CommonRawData rd);
}
