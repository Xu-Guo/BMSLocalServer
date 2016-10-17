package edu.itu.command;

import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * Created by xuxu on 10/16/16.
 */
public class SetCmd extends CommonCmd{
    private char CmdDataLength = 0;
    private char[] cmdData;

    public SetCmd(char cmdAction, LocalDateTime ldt) {
        this.cmdAction = cmdAction;
        ArrayList<Character> a = new ArrayList<>();
        a.add((char)((ldt.getYear() >> 8) & 0xFF));
        a.add((char)(ldt.getYear() & 0xFF));
        a.add((char)(ldt.getMonthValue() & 0xFF));
        a.add((char)(ldt.getDayOfMonth() & 0xFF));
        a.add((char)(ldt.getHour() & 0xFF));
        a.add((char)(ldt.getMinute() & 0xFF));
        a.add((char)(ldt.getSecond() & 0xFF));

        String str = "";

        for (Character c : a)
        {
            str += c + "\t";
        }
        //this.cmdData = str;
    }

    @Override
    public char[] doPackage() {
        return new char[0];
    }

//    public static void main(String[] args) {
//
//    }
}
