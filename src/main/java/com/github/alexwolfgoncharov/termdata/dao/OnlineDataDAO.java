package com.github.alexwolfgoncharov.termdata.dao;

import com.github.alexwolfgoncharov.termdata.interfaces.OnlineData;

import java.util.List;

/**
 * Created by alexwolf on 10.03.16.
 */
public interface OnlineDataDAO {
    void add(OnlineData data);
    void modify(OnlineData data);
    void delete (OnlineData data);
    List<OnlineData> getAll();
    OnlineData getLastByBase(String base);



}
