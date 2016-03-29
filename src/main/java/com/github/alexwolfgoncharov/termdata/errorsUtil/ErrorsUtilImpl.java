package com.github.alexwolfgoncharov.termdata.errorsUtil;

import com.github.alexwolfgoncharov.termdata.countErrors.CountErrors;
import com.github.alexwolfgoncharov.termdata.countErrors.Errors;
import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.dao.TermDao;
import com.github.alexwolfgoncharov.termdata.interfaces.*;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 04.01.16.
 */
public class ErrorsUtilImpl implements ErrorsUtil {

    private static final Logger log = Logger.getLogger(Error.class.getName());

    @Override
    public CountErrors getCurrentErrors(String baseid) {
        CountErrors current = new CountErrors();

        OnlineData lastData = null;
        BaseID baseID = null;
        int errorCount = 0;
        List<Errors> errorList = new ArrayList<Errors>();

        try {
             lastData =  Factory.getInstance().getDataService().getLastByBase(baseid);
             baseID = Factory.getInstance().getBaseIdDAO().getByBaseID(baseid);
            current.setBaseID(baseID);

        } catch (SQLException e) {
            log.severe(e.getMessage());

//            e.printStackTrace();

        }

        if (lastData != null && baseID != null && baseID.isStatus()){
            List<SensorsBaseId> sensors = baseID.getSensors();
            System.out.println(lastData.getTime());
            Date now = new Date();
            Date last = new Date(lastData.getTime().getTime());
            long diff = now.getTime() - last.getTime();
            System.out.println("Diff: = "+ diff);
            if (diff > (15 * 60000)) {

                current.setNoSignal(true);
            }




            for (SensorsBaseId curSensor : sensors) {
                if (curSensor.isUsed()) {

                    String nameSens = (curSensor.getType().equals("tm") ? "term" : "signal") + curSensor.getSensorId();
//                    System.out.print(baseid+ " | " + nameSens + " ");


                    try {
                        Field f = lastData.getClass().getDeclaredField(nameSens);
                        f.setAccessible(true);

                        Integer signal = 0;
                        if (curSensor.getType().equals("tm")) {
                            Float temp = f.getFloat(lastData);
//                            System.out.println(temp);


                            if (curSensor.getMin() > temp) {

                                Errors error = new Errors();
                                error.setBaseID(lastData.getBaseID());
                                error.setErrorMessage(curSensor.getName() + " меньше чем " + curSensor.getMin() + "°С.");
//                                System.out.println(error.getErrorMessage());
                                error.setCritical(curSensor.isCriticalError());
                                error.setSensor(curSensor);

                                if (diff > (15 * 60000)) {
                                    error.setNoSignal(true);
                                    current.setNoSignal(true);
                                }
                                if (error.isCritical()){
                                    current.setHaveCriticalError(true);
                                }

                                errorList.add(error);
                                errorCount++;

                            }

                            if (curSensor.getMax() < temp) {

                                Errors error = new Errors();
                                error.setBaseID(lastData.getBaseID());
                                error.setErrorMessage(curSensor.getName() + " больше чем " + curSensor.getMin() + "°С.");
//                                System.out.println(error.getErrorMessage());
                                error.setCritical(curSensor.isCriticalError());
                                error.setSensor(curSensor);

                                if (error.isCritical()){
                                    current.setHaveCriticalError(true);
                                }
                                errorList.add(error);
                                errorCount++;


                            }

                        } else if (curSensor.getType().equals("dt")) {

                            signal = (Integer) f.get(lastData);
//                            System.out.println(signal);

                            if (signal != curSensor.getMin()) {

                                Errors error = new Errors();
                                error.setBaseID(lastData.getBaseID());
                                error.setErrorMessage(curSensor.getName());
                                error.setCritical(curSensor.isCriticalError());
                                error.setSensor(curSensor);
//                                System.out.println(error.getErrorMessage());

                                if (diff > (15 * 60000)) {
                                    error.setNoSignal(true);
                                    current.setNoSignal(true);
                                }
                                if (error.isCritical()){
                                    current.setHaveCriticalError(true);
                                }

                                errorList.add(error);
                                errorCount++;

                            }

                        }


                    } catch (NoSuchFieldException e) {
                        log.severe(e.getMessage());
//                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        log.severe(e.getMessage());
//                        e.printStackTrace();
                    }


                }
            }


        }

        current.setCount(errorCount);
        current.setErrorList(errorList);

        return current;
    }

    @Override
    public List<CountErrors> getAllCurrentErrors() {
        List<CountErrors> listErrors = new ArrayList<>();

        try {
            List<BaseID> allBaseID = Factory.getInstance().getBaseIdDAO().getAll();
            for (BaseID base : allBaseID){
                CountErrors cur = getCurrentErrors(base.getBaseID());
                listErrors.add(cur);



            }


        } catch (SQLException e) {
            log.severe(e.getMessage());
//            e.printStackTrace();
        }


        return listErrors;
    }

    @Override
    public List<CountErrors> getAllCurrentErrorsByLogin(String login) {
        List<CountErrors> listErrors = new ArrayList<>();
//        List<String> userBAseIDList = Factory.getInstance().getBaseIdDAO().getUserBaseIDStringList(login);
        User user = null;
        try {
             user = Factory.getInstance().getLoginDAO().getByLogin(login);
            Set<BaseID> userBaseIDList = user.getBaseIdSet();
            for (BaseID base : userBaseIDList){
                CountErrors cur = getCurrentErrors(base.getBaseID());
                listErrors.add(cur);



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return listErrors;
    }


}


