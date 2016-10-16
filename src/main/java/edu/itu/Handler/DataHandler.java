package edu.itu.Handler;

import edu.itu.data.CommonRawData;

/**
 * Created by xuxu on 10/16/16.
 */
public abstract class DataHandler {
    Double CHARGE_SENSE_RESISTANCE = 0.1;
    Double DISCHARGE_SENSE_RESISTANCE = 5.0;
    Double CHARGE_OPAMP_GAIN = 7.0;
    int ADC_FULL_SCALE_VALUE = 4095;
    double TEMPERATURE_FACTOR = 1000.0;

    public abstract void handleData(CommonRawData rd);
}
