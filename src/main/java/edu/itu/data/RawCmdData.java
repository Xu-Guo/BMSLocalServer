package edu.itu.data;

import edu.itu.Handler.CmdDataHandler;
import edu.itu.Handler.CmdHandler;
import edu.itu.Handler.DataHandler;

import java.util.List;

/**
 * Created by xuxu on 10/16/16.
 */
public class RawCmdData extends CommonRawData {
    private int commandType;
    private int commandAction;
    private List<Integer> data;
    private CmdDataHandler cdh = new CmdDataHandler();
    private CmdHandler ch = new CmdHandler();


    public RawCmdData() {
    }

    public RawCmdData(int commandType, int commandAction, List<Integer> data) {
        this.commandType = commandType;
        this.commandAction = commandAction;
        this.data = data;
    }

    @Override
    public void setData(List<Integer> list) {
        if(list == null){
            System.out.println("cmd content is null");
            return;
        }
        //do something here
        notifyDataHandler(cdh, this);

    }


    @Override
    public void notifyDataHandler(DataHandler dh, CommonRawData rd) {
        cdh.handleData(this);
    }

    @Override
    public void notifyCmdHandler(CmdHandler ch) {
        //do nothing here.
    }
}