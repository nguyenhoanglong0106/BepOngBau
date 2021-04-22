package com.example.bepongbau.Model;

import java.io.Serializable;

public class Information implements Serializable {
    public String hovaten;
    public String gioitinh;
    public String ngaysinh;
    public String sdt;

    public Information(String hovaten, String gioitinh, String ngaysinh, String sdt) {
        this.hovaten = hovaten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
    }

    public Information() {
    }

    public String getHovaten() {
        return hovaten;
    }

    public void setHovaten(String hovaten) {
        this.hovaten = hovaten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    @Override
    public String toString() {
        return "Information{" +
                "hovaten='" + hovaten + '\'' +
                ", gioitinh='" + gioitinh + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }
}
