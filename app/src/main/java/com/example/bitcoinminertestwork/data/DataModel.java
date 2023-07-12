package com.example.bitcoinminertestwork.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "bitcoindata")
public class DataModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public int satoshi;
    public double btc;
    public double percent;
    public int server;

    public DataModel(int satoshi, double btc, double percent, int server) {
        this.satoshi = satoshi;
        this.btc = btc;
        this.percent = percent;
        this.server = server;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSatoshi() {
        return satoshi;
    }

    public void setSatoshi(int satoshi) {
        this.satoshi = satoshi;
    }

    public double getBtc() {
        return btc;
    }

    public void setBtc(double btc) {
        this.btc = btc;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getServer() {
        return server;
    }

    public void setServer(int server) {
        this.server = server;
    }
}
