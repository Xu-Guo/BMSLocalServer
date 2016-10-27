package edu.itu.Handler;


import edu.itu.command.SetCmd;
import edu.itu.factory.CmdFactory;
import edu.itu.utils.SerialUtils;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Created by xuxu on 10/16/16.
 * The Cmdhandler class handls command queue which sends command in sequence without conflict.
 *
 */
public  class CmdHandler {
    public void sendSetTimeCommand(long time){

        SerialPort sp = SerialUtils.getInstance();
        try {
            sp.writeIntArray(CmdFactory.createSetTimeCommand(time).doPackage());
        }catch(SerialPortException e){
            e.printStackTrace();
            System.out.println("got SerialPortException!");
        }
        System.out.println("send set time command");

    }
}
