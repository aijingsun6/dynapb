package org.alking.dynapb;

import java.util.ArrayList;
import java.util.List;

public class FooMan {

    private Long lv;

    private Float fv;

    private Double dv;

    private Boolean bv;

    private String sv;

    public Long getLv() {
        return lv;
    }

    public void setLv(Long lv) {
        this.lv = lv;
    }

    public Float getFv() {
        return fv;
    }

    public void setFv(Float fv) {
        this.fv = fv;
    }

    public Double getDv() {
        return dv;
    }

    public void setDv(Double dv) {
        this.dv = dv;
    }

    public Boolean getBv() {
        return bv;
    }

    public void setBv(Boolean bv) {
        this.bv = bv;
    }

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
        this.sv = sv;
    }

    public FooMan() {
    }

    public void parseFrom(byte[] bytes){
        PbMessage msg = new PbMessage(bytes.length);
        msg.read(bytes, 0);
        for (PbField f: msg.getFields()){
            int fieldNum = f.getFieldNum();
            PbValue value = (PbValue) f.getValue();
            switch (fieldNum){
                case 1:
                    this.lv = value.longValue();
                    break;
                case 2:
                    this.fv = value.floatValue();
                    break;
                case 3:
                    this.dv = value.doubleValue();
                    break;
                case 4:
                    this.bv = value.boolValue();
                    break;
                case 5:
                    this.sv = value.stringValue();
                    break;
                default:
                    break;
            }
        }
    }

    public byte[] toBytes(){
        List<PbField> fs = new ArrayList<>();
        fs.add( new PbField(1,PbValue.valueOf(lv)));
        fs.add( new PbField(2,PbValue.valueOf(fv)));
        fs.add( new PbField(3,PbValue.valueOf(dv)));
        fs.add( new PbField(4,PbValue.valueOf(bv)));
        fs.add( new PbField(5,PbValue.valueOf(sv)));
        PbMessage msg = new PbMessage(fs);
        int size = msg.size();
        byte[] data = new byte[size];
        msg.write(data, 0);
        return data;
    }

}