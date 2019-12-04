package dao.impl;

import dao.DaoFactory;
import dao.DimensionDao;
import dao.FactDao;

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
