package domain.product.dao.mysql;

import domain.product.Product;
import domain.product.dao.ProductDao;

public class ProductMySqlDao implements ProductDao {

    @Override
    public void insertProduct(Product product) {
        System.out.println("insert into MYSQL DB productId = " + product.getProductId());
    }

    @Override
    public void updateProduct(Product product) {
        System.out.println("update into MYSQL DB productId = " + product.getProductId());
    }

    @Override
    public void deleteProduct(Product product) {
        System.out.println("delete from MYSQL DB productId = " + product.getProductId());
    }
}
