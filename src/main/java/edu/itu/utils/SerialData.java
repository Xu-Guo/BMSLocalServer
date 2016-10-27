package edu.itu.utils;

import edu.itu.Handler.BatteryDataHandler;
import edu.itu.Handler.CmdDataHandler;
import edu.itu.Handler.DataHandler;
import edu.itu.data.CommonRawData;
import edu.itu.data.RawBatteryData;
import edu.itu.data.RawCmdData;
import edu.itu.factory.DataFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxu on 10/16/16.
 */
public class SerialData {

    private List<Integer> content = null;
    private RawBatteryData data = new RawBatteryData();
    private RawCmdData cmd = new RawCmdData();

    private BatteryDataHandler bdh = new BatteryDataHandler();
    private CmdDataHandler cdh = new CmdDataHandler();


//    private List<DataHandler> list = new ArrayList<DataHandler>();
//
//    public void registerDataHandle(DataHandler dh) {
//        list.add(dh);
//    }
//
//    public void removeDataHandle(DataHandler dh) {
//        list.remove(dh);
//    }

    public void setContent(List<Integer> a) {

        if(null == a){
            System.out.println("List is null~~");
            return;
        }
        this.content = a;
    }

    public SerialData() {
    }

    public SerialData(ArrayList<Integer> a) {
        this.content = a;
    }


    public boolean isValid() {
        if(null == content){
            System.out.println("null content!!");
            return false;
        }

        for (Integer i:content) {
            System.out.print(i.intValue());
            System.out.print(" ");
        }
        System.out.println("");


        if (content.size() == 0 || content.get(0).intValue() != 0x68 || content.get(content.size() - 1).intValue() != 0x88) {
            content = null;
            System.out.println("++++++++++++++++content incorrect++++++++++++++");
            return false;
        } else {
            return true;
        }
    }

    public int getSerialDataType() {
//        System.out.println("xxxx=" + content.get(1).intValue());
        return content.get(1);
    }

    public void setRawData() {


        if (!this.isValid()) {
            return;
        }

        CommonRawData data = DataFactory.createRawData(getSerialDataType());
        if(content != null){
            data.setData(content);
        }else {
            System.out.println("+++++++content is null+++");
        }
    }




    public void notifyDataHandler(DataHandler dh, CommonRawData rd) {
        dh.handleData(rd);
    }


}