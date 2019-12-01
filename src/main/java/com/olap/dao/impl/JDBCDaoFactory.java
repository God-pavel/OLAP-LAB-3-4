package com.olap.dao.impl;

import com.olap.dao.DaoFactory;
import com.olap.dao.DimensionDao;
import com.olap.dao.FactDao;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public FactDao createFactDao() {
        return new JDBCFactDao();
    }

    @Override
    public DimensionDao createDimensionDao() {
        return new JDBCDimensionDao();
    }
}
