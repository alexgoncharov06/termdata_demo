package com.github.alexwolfgoncharov.termdata.countErrors;

import com.github.alexwolfgoncharov.termdata.interfaces.SensorsBaseId;

/**
 * Created by alexwolf on 04.01.16.
 */
public class Errors {
    String errorMessage;
    String BaseID;
    SensorsBaseId sensor;
    boolean critical;
    boolean noSignal;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getBaseID() {
        return BaseID;
    }

    public void setBaseID(String baseID) {
        BaseID = baseID;
    }

    public SensorsBaseId getSensor() {
        return sensor;
    }

    public void setSensor(SensorsBaseId sensor) {
        this.sensor = sensor;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public boolean isNoSignal() {
        return noSignal;
    }

    @Override
    public String toString() {
        return "Error{" +
                "errorMessage='" + errorMessage + '\'' +
                ", BaseID='" + BaseID + '\'' +
                ", sensor=" + sensor +
                ", critical=" + critical +
                ", noSignal=" + noSignal +
                '}';
    }

    public void setNoSignal(boolean noSignal) {
        this.noSignal = noSignal;
    }
}
