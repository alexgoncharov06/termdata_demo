package com.github.alexwolfgoncharov.termdata.errorsUtil;

import com.github.alexwolfgoncharov.termdata.countErrors.CountErrors;
import com.github.alexwolfgoncharov.termdata.interfaces.BaseID;

import java.util.List;

/**
 * Created by alexwolf on 04.01.16.
 */
public interface ErrorsUtil {
    

     CountErrors getCurrentErrors(String baseid);

    List<CountErrors> getAllCurrentErrors();
    List<CountErrors> getAllCurrentErrorsByLogin(String login);

}
