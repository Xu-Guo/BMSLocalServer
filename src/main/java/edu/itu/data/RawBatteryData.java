package edu.itu.data;

import edu.itu.Handler.BatteryDataHandler;
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


    @Override
    public void notifyDataHandler(DataHandler dh, CommonRawData rd) {
        dh.handleData(rd);
    }

    @Override
    public void setData(List<Integer> list) {
        System.out.println("Set RBD!");

        this.setChCur(((list.get(3).intValue() << 8) + list.get(4).intValue()) * 1000 * 10 / 4095 / 75); // in unit of mA

        this.setDisCur(((list.get(5).intValue() << 8) + list.get(6).intValue()) * 1000 / 4095 / 5); // in unit of mA

        this.setTemperature((list.get(7).intValue() << 8) + list.get(8).intValue());

        this.setStateOfCharge(list.get(9).intValue());

        this.setBatteryStatus(list.get(10).intValue());

        this.setChargerStatus(list.get(11).intValue());
        //System.out.println("Set RBD2!");
        int year = (list.get(12).intValue() << 8) + list.get(13).intValue();
        int month = list.get(14).intValue();
        int day = list.get(15).intValue();
        int hour = list.get(16).intValue();
        int min = list.get(17).intValue();
        int sec = list.get(18).intValue();

        LocalDateTime time = LocalDateTime.of(year, Month.of(month), day, hour, min, sec);
        Timestamp timestamp = Timestamp.valueOf(time);
        //LocalDateTime ldt2 = timestamp.toLocalDateTime();

        this.timestamp = timestamp;

        this.print();

        //if no current in/out, means battery is not charging or discharging, then we will not notify data handler to insert data to data base.
        if(this.getBatteryStatus() == 0){
            return;
        }
        notifyDataHandler(bdh, this);
    }

}
