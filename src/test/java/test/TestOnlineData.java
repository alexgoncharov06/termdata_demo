package test;

import com.github.alexwolfgoncharov.termdata.dao.DAOBaseId;
import com.github.alexwolfgoncharov.termdata.dao.TermDao;
import com.github.alexwolfgoncharov.termdata.interfaces.OnlineData;
import com.github.alexwolfgoncharov.termdata.interfaces.ThermData;
import com.github.alexwolfgoncharov.termdata.services.OnlineDataService;
import com.github.alexwolfgoncharov.termdata.services.OnlineDataServiceImpl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by alexwolf on 10.03.16.
 */
public class TestOnlineData {

    public static void main(String ... agrs){
        OnlineDataService dataService = new OnlineDataServiceImpl();
        TermDao termDao = new TermDao();


        ThermData newTern = new ThermData();

        ThermData lastTherm = null;
        OnlineData lasrData = null;

        newTern.setBaseID("test");
        long now = new Date().getTime();
        newTern.setTime(new Timestamp(now));
        newTern.setTerm1(282.3f);
        newTern.setTerm2(0.45f);
        System.out.println(newTern.toString());

        OnlineData onlineData = new OnlineData(newTern);
        System.out.println(onlineData.toString());
        try {
            termDao.add(newTern);
            dataService.add(onlineData);

            lastTherm = termDao.getLastByBase(newTern.getBaseID());
            System.out.println("\n\r------------------------\n\r");
            System.out.println(lastTherm.toString());

            lasrData = dataService.getLastByBase(onlineData.getBaseID());
            System.out.println(lasrData.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
