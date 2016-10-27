package edu.itu.data;

import edu.itu.Handler.BatteryDataHandler;
import edu.itu.Handler.CmdHandler;
import edu.itu.Handler.DataHandler;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

/**
 * Created by xuxu on 10/16/16.
 */
public class RawBatteryData extends CommonRawData {

    private int batteryId;
    private int chCur;
    private int disCur;
    private int temperature;
    private int stateOfCharge;   //in percentage %
    private int batteryStatus;
    private int chargerStatus;
    private Timestamp timestamp;

    private CmdHandler ch = new CmdHandler();

    public int getStateOfCharge() {
        return stateOfCharge;
    }

    public void setStateOfCharge(int stateOfCharge) {
        this.stateOfCharge = stateOfCharge;
    }

    private BatteryDataHandler bdh = new BatteryDataHandler();

    public RawBatteryData() {
    }

    public void setChCur(int chCur) {
        this.chCur = chCur;
    }

    public void setDisCur(int disCur) {
        this.disCur = disCur;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public void setChargerStatus(int chargerStatus) {
        this.chargerStatus = chargerStatus;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getBatteryId() {
        return batteryId;
    }

    public int getChCur() {
        return chCur;
    }

    public int getDisCur() {
        return disCur;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public int getChargerStatus() {
        return chargerStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void print(){
        System.out.println("chur = " + this.getChCur());
        System.out.println("dis_cur = " + this.getDisCur());
        System.out.println("temp = " + this.getTemperature());
        System.out.println("BatteryStatus = " + this.getBatteryStatus());
        System.out.println("StateOfCharge = " + this.getStateOfCharge());
    }

    public boolean compareTime(LocalDateTime time1, LocalDateTime time2){
        if(time1 == null || time2 == null){
            System.out.println("one or all time is null");
            return true;
        }
        int sysHour = 0;
        if(time2.getHour() > 12){
            sysHour = time2.getHour() - 12;
        }
        if(time1.getMinute() != time2.getMinute()){
            return false;
        }
        if(Math.abs(time1.getSecond() - time2.getSecond()) > 3){

            return false;
        }
        return true;
    }

    @Override
    public void notifyDataHandler(DataHandler dh, CommonRawData rd) {
        dh.handleData(rd);
    }

    @Override
    public void notifyCmdHandler(CmdHandler ch) {
        ch.sendSetTimeCommand(System.currentTimeMillis());
    }

    @Override
    public void setData(List<Integer> list) {

        if(list == null){
            System.out.println("content is null");
            return;
        }
        System.out.println("Set RBD!");

        this.setChCur(((list.get(3)<< 8) + list.get(4)) * 1000 * 10 / 4095 / 100); // in unit of mA

        this.setDisCur(((list.get(5) << 8) + list.get(6)) * 1000 / 4095 / 5); // in unit of mA

        this.setTemperature((list.get(7) << 8) + list.get(8));

        this.setStateOfCharge(list.get(9));

        this.setBatteryStatus(list.get(10));

        this.setChargerStatus(list.get(11));
        //System.out.println("Set RBD2!");
        int year = (list.get(12) << 8) + list.get(13);
        int month = list.get(14);
        int day = list.get(15);
        int hour = list.get(16);
        int min = list.get(17);
        int sec = list.get(18);


        LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, min, sec);

        this.timestamp = Timestamp.valueOf(time);
        //LocalDateTime sysTime = LocalDateTime.now();

        //check if board time is sync with local host system, if not, send time sync command.

//        if(!compareTime(time,sysTime)){
//            System.out.println("++++++here i am+++++++");
//            //this.timestamp = Timestamp.valueOf(sysTime);
//            //notifyCmdHandler(ch);
//        }else{
//            this.timestamp = Timestamp.valueOf(time);
//        }


        //this.print();

        notifyDataHandler(bdh, this);
    }

}
