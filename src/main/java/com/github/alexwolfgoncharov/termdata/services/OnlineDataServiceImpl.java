package com.github.alexwolfgoncharov.termdata.services;

import com.github.alexwolfgoncharov.termdata.dao.OnlineDataDAO;
import com.github.alexwolfgoncharov.termdata.dao.OnlineDataDAOImpl;
import com.github.alexwolfgoncharov.termdata.interfaces.OnlineData;

import java.util.List;

/**
 * Created by alexwolf on 10.03.16.
 */
public class OnlineDataServiceImpl implements  OnlineDataService {
    private OnlineDataDAO dataDAO = new OnlineDataDAOImpl();

    @Override
    public void add(OnlineData data) {
        dataDAO.add(data);
    }

    @Override
    public void modify(OnlineData data) {
        dataDAO.modify(data);
    }

    @Override
    public void delete(OnlineData data) {
        dataDAO.delete(data);
    }

    @Override
    public List<OnlineData> getAll() {
        return dataDAO.getAll();
    }

    @Override
    public OnlineData getLastByBase(String base) {
        return dataDAO.getLastByBase(base);
    }
}
