package factory;

import domain.product.dao.ProductDao;
import domain.userInfo.dao.UserInfoDao;

public interface DaoFactory {
    UserInfoDao createUserInfoDao();
    ProductDao createProductDao();
}
