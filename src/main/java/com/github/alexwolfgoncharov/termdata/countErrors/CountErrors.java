package com.github.alexwolfgoncharov.termdata.countErrors;

import com.github.alexwolfgoncharov.termdata.interfaces.BaseID;

import java.util.List;

/**
 * Created by alexwolf on 04.01.16.
 */
public class CountErrors {
    com.github.alexwolfgoncharov.termdata.interfaces.BaseID BaseID;
    int count;
    List<Errors> errorList;
    boolean noSignal;
    boolean haveCriticalError;

    public boolean isHaveCriticalError() {
        return haveCriticalError;
    }

    public void setHaveCriticalError(boolean haveCriticalError) {
        this.haveCriticalError = haveCriticalError;
    }

    public com.github.alexwolfgoncharov.termdata.interfaces.BaseID getBaseID() {
        return BaseID;
    }

    public void setBaseID(com.github.alexwolfgoncharov.termdata.interfaces.BaseID baseID) {
        BaseID = baseID;
    }

    public CountErrors(com.github.alexwolfgoncharov.termdata.interfaces.BaseID baseID, int count, List<Errors> errorList, boolean noSignal) {
        BaseID = baseID;
        this.count = count;
        this.errorList = errorList;
        this.noSignal = noSignal;
    }

    public CountErrors() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Errors> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<Errors> errorList) {
        this.errorList = errorList;
    }

    public boolean isNoSignal() {
        return noSignal;
    }

    @Override
    public String toString() {
        return "CountErrors{" +
                "BaseID='" + BaseID + '\'' +
                ", count=" + count +
                ", errorList=" + errorList +
                ", noSignal=" + noSignal +
                '}';
    }

    public void setNoSignal(boolean noSignal) {
        this.noSignal = noSignal;
    }
}
