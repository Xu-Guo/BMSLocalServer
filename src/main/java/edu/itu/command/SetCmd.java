package edu.itu.command;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by xuxu on 10/16/16.
 */
public class SetCmd extends CommonCmd implements Packageable{
    private int cmdDataLength = 0;
    private ArrayList<Integer> cmdData = new ArrayList<>();

    public SetCmd(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        cmdData.add(cal.get(Calendar.YEAR) >> 8 & 0xff);
        cmdData.add(cal.get(Calendar.YEAR) & 0xff);
        cmdData.add(cal.get(Calendar.MONTH) + 1); //Month start from 0 here.
        cmdData.add(cal.get(Calendar.DAY_OF_MONTH));
        int hour = 0;
        if(cal.get(Calendar.AM_PM) == Calendar.PM){
            hour = cal.get(Calendar.HOUR) + 12;
        }else{
            hour = cal.get(Calendar.HOUR);
        }
        cmdData.add(hour);
        cmdData.add(cal.get(Calendar.MINUTE));
        cmdData.add(cal.get(Calendar.SECOND));

        this.cmdDataLength = cmdData.size();

        this.cmdType = CMD_TYPE_SET;
        this.cmdAction = CMD_ACTION_SET_TIME;
    }

    @Override
    public int[] doPackage() {
        int total_length = this.cmdDataLength + 3;

        ArrayList<Integer> packagedData = new ArrayList<>();
        packagedData.add(START_FLAG);
        packagedData.add(total_length);
        packagedData.add(cmdType);
        packagedData.add(cmdAction);
        packagedData.add(cmdDataLength);
        packagedData.addAll(cmdData);
        packagedData.add(END_FLAG);

        int[] finalData = new int[packagedData.size()];
        for(int i = 0;i < finalData.length;i++) {
            finalData[i] = packagedData.get(i);
            System.out.print(finalData[i] + " ");
        }
        System.out.println(" ");

        return finalData;
    }
}
