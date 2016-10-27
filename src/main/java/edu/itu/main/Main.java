package edu.itu.main;

import edu.itu.Handler.CmdHandler;
import edu.itu.factory.CmdFactory;
import edu.itu.utils.SerialUtils;
import jssc.SerialPort;
import jssc.SerialPortException;

import java.util.concurrent.TimeUnit;

/**
 * Created by xuxu on 10/17/16.
 */
public class Main {

    static SerialPort serialPort;

    public static void main(String[] args) {

        serialPort = SerialUtils.getInstance();
        SerialUtils.openPort(serialPort);
        System.out.println("hello");

        CmdHandler ch = new CmdHandler();
        ch.sendSetTimeCommand(System.currentTimeMillis());


    }

}

