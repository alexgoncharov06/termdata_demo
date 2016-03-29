package test;

import com.github.alexwolfgoncharov.termdata.countErrors.CountErrors;
import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtil;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtilImpl;
import com.github.alexwolfgoncharov.termdata.interfaces.User;
import com.github.alexwolfgoncharov.termdata.interfaces.BaseID;
import com.github.alexwolfgoncharov.termdata.interfaces.UserRoleEnum;
import com.github.alexwolfgoncharov.termdata.services.UserService;
import com.github.alexwolfgoncharov.termdata.services.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by alexwolf on 05.01.16.
 */
public class errorsTest {

    public static void main(String ... agrs) {

        ErrorsUtil errUtil = new ErrorsUtilImpl();
//        List<CountErrors> currentErrors = errUtil.getAllCurrentErrors();
//
//        for (CountErrors oneEr : currentErrors){
//            System.out.println(oneEr.toString());
//
//        }

        UserService userServ = new UserServiceImpl();
//
//        List<User> userList = null;
//        try {
//            userList = Factory.getInstance().getLoginDAO().getAll();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
////            e.printStackTrace();
//        }
//        System.out.println("User List:");
//        if (userList == null) System.out.println("is null");
//        else System.out.println(userList.size());
//        for(User us : userList){
//            System.out.println(us.toString());
//
//        }

        int count = 0;
        boolean forUser = false;
        String login = "alexwolf";
        User user = null;
//        try {
            user =  userServ.getUser(login);
        System.out.println( user.toString());


//                    Factory.getInstance().getLoginDAO().getByLogin(login);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        List<CountErrors> allCurrentErrors = new ArrayList<CountErrors>();
//        List<BaseID>  usersBaseId = null;
            if (user != null) {
                if (user.getRole().equals("USER")) {
                    forUser = true;
                }

                Set<BaseID> usersBaseID = user.getBaseIdSet();
                Set<Integer> userIDSet = new HashSet<>();
                for (BaseID onebase : usersBaseID){
                    userIDSet.add(onebase.getID());
                }
                System.out.println(usersBaseID.toString());
                List<CountErrors> allErrorsForAllUsers = errUtil.getAllCurrentErrors();
                System.out.println(allErrorsForAllUsers.toString());
                try {
                    BaseID test = Factory.getInstance().getBaseIdDAO().getById(16);
                    System.out.println("Тест" + "\t" + userIDSet.contains(test.getID()));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int countIter = 0;
                for (CountErrors oneError : allErrorsForAllUsers) {
                    countIter++;
                    System.out.println(countIter + "\t" + usersBaseID.contains(oneError.getBaseID()));

                    if (!forUser || userIDSet.contains(oneError.getBaseID().getID()) ) {
                        System.out.println(oneError.toString());
                        allCurrentErrors.add(oneError);
                    }
                }

                System.out.println(allCurrentErrors.size());
                for (CountErrors errors : allCurrentErrors){
                    System.out.println(errors.toString());
//                    allCurrentErrors.remove(errors);
                }

            } else {

                System.out.print("\"Error\" : \"No login: " +  login + "\"");
//                out.close();
                return;
            }




    }
}
