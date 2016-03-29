package test;

import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.interfaces.BaseID;
import com.github.alexwolfgoncharov.termdata.interfaces.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by alexwolf on 11.01.16.
 */
public class UserTest {

    public static void main(String ... agrs){

        System.out.println("Start");
        try {
            User oneUser = Factory.getInstance().getLoginDAO().getByLogin("alexwolf");
//			List<BaseID> allList = Factory.getInstance().getBaseDao().getAll();

            System.out.println(oneUser.toString());
//			oneUser.setBaseIdSet(allList);

//			Factory.getInstance().getLoginDAO().update(oneUser);
            BaseID ll = new BaseID();
            ll.getBaseID();
            List<User> allList  = Factory.getInstance().getLoginDAO().getAll();
            System.out.println(allList.size());
            for (User base : allList){

                System.out.println(base.toString());
            }



        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
