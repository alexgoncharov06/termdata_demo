package test;

import com.github.alexwolfgoncharov.termdata.countErrors.CountErrors;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtil;
import com.github.alexwolfgoncharov.termdata.errorsUtil.ErrorsUtilImpl;
import com.github.alexwolfgoncharov.termdata.servlets.ErrorsCount;

/**
 * Created by alexwolf on 19.01.16.
 */
public class TestErrorByDBaseID {

    public static void main(String ... args){

        ErrorsUtilImpl util = new ErrorsUtilImpl();
        String baseidStr = "867273021574053";

        CountErrors count = util.getCurrentErrors(baseidStr);
        System.out.println(count.toString());

    }

}
