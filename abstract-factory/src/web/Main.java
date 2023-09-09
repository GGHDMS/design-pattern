package web;

import domain.product.Product;
import domain.product.dao.ProductDao;
import domain.userInfo.UserInfo;
import domain.userInfo.dao.UserInfoDao;
import factory.DaoFactory;
import factory.MysqlDaoFactory;
import factory.OracleDaoFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("/Users/hsm/Desktop/github/design-pattern/abstract-factory/src/db.properties");

        Properties prop = new Properties();
        prop.load(fis);

        String dbType = prop.getProperty("DB-TYPE");

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("12345");
        userInfo.setPassword("!@#$%");
        userInfo.setUserName("asdfg");

        Product product = new Product();
        product.setProductId("0011AA");
        product.setProductName("TV");

        DaoFactory daoFactory = null;

        if (dbType.equals("MYSQL")) {
            daoFactory = new MysqlDaoFactory();
        } else if (dbType.equals("ORACLE")) {
            daoFactory = new OracleDaoFactory();
        } else {
            System.out.println("error");
        }

        UserInfoDao userInfoDao = daoFactory.createUserInfoDao();

        System.out.println("UserInfo Transaction");
        userInfoDao.insertUserInfo(userInfo);
        userInfoDao.updateUserInfo(userInfo);
        userInfoDao.deleteUserInfo(userInfo);

        ProductDao productDao = daoFactory.createProductDao();

        System.out.println("Product Transaction");
        productDao.insertProduct(product);
        productDao.updateProduct(product);
        productDao.deleteProduct(product);
    }
}
