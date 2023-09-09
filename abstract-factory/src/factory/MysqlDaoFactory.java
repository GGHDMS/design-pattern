package factory;

import domain.product.dao.ProductDao;
import domain.product.dao.mysql.ProductMySqlDao;
import domain.userInfo.dao.UserInfoDao;
import domain.userInfo.dao.mysql.UserInfoMysqlDao;

public class MysqlDaoFactory implements DaoFactory {

    @Override
    public UserInfoDao createUserInfoDao() {
        return new UserInfoMysqlDao();
    }

    @Override
    public ProductDao createProductDao() {
        return new ProductMySqlDao();
    }
}
