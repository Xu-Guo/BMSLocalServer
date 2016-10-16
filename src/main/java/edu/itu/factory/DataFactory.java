package edu.itu.factory;

import edu.itu.data.CommonRawData;
import edu.itu.data.RawBatteryData;
import edu.itu.data.RawCmdData;

/**
 * Created by xuxu on 10/16/16.
 */
public class DataFactory {

    private static int TYPE_DATA = 0x10;
    private static int TYPE_COMMAND = 0x11;

    private static RawBatteryData rbd = new RawBatteryData();
    private static RawCmdData rcd = new RawCmdData();

    public static CommonRawData createRawData(int serialDataType) {
        if (serialDataType == TYPE_DATA) {
            //System.out.println("return rbd");
            return rbd;

        }

        if(serialDataType == TYPE_COMMAND){
            //System.out.println("return rcd");
            return rcd;
        }
        //System.out.println("get here");
        return null;

    }
}
