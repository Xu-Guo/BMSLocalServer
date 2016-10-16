package edu.itu.command;

/**
 * Created by xuxu on 10/16/16.
 */
public abstract class CommonCmd {
    private final char START_FLAG = 0X68;
    private final char END_FLAG = 0x88;
    protected final char CMD_TYPE_SET = 0x01;
    protected final char CMD_TYPE_QUERY = 0x01;



    private char CmdDataLength = 0;



    private String CmdData;

    public String doPackaging(QueryCmd qc){
        return "querycmd";
    };

    public String dopackaging(SetCmd sc){
        return "setcmd";
    };
}
