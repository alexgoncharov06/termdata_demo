package test;

import com.github.alexwolfgoncharov.termdata.countErrors.CountErrors;
import com.github.alexwolfgoncharov.termdata.dao.Factory;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtil;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtilImpl;

import java.util.List;

/**
 * Created by alexwolf on 17.01.16.
 */
public class TestBaseId {
    public static void main(String ... agrs){

//        List<Integer> baseIDList = Factory.getInstance().getBaseIdDAO().getUserBaseIDList("agro");
//        System.out.println(baseIDList.toString());

        List<CountErrors> curr = new ErrorsUtilImpl().getAllCurrentErrorsByLogin("alexwolf");
        System.out.println(curr.toString());
    }
}
