package edu.itu.Handler;



import edu.itu.data.CommonRawData;
import edu.itu.data.RawBatteryData;
import edu.itu.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by xuxu on 10/16/16.
 */
public class BatteryDataHandler extends DataHandler {
    @Override
    public void handleData(CommonRawData rd) {
        System.out.println("get raw battery data!");
        if (rd instanceof RawBatteryData) {
            mysqlInsertData((RawBatteryData) rd);
        }
    }


    public void mysqlInsertData(RawBatteryData rbd) {
        Connection conn = JDBCUtils.getConn();
        String sql = "insert into batteryData (battery_id, ch_cur, dis_cur, temperature, stateofcharge, battery_status, charger_status, timestp) values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0X01);
            ps.setInt(2, (rbd.getChCur()));
            //ps.setDouble(3, (rbd.getDisCur() / ADC_FULL_SCALE_VALUE / DISCHARGE_SENSE_RESISTANCE));
            ps.setInt(3, (rbd.getDisCur()));
            ps.setDouble(4, rbd.getTemperature() / TEMPERATURE_FACTOR);
            ps.setInt(5,rbd.getStateOfCharge());
            ps.setInt(6, rbd.getBatteryStatus());
            ps.setInt(7, rbd.getChargerStatus());
            ps.setTimestamp(8, rbd.getTimestamp());


            ps.execute();
            System.out.println(rbd.getTimestamp());
            System.out.println("insert a line!!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(ps);
        }

    }
}
