package beans;

import entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Philchenkov
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {
    @PersistenceContext(unitName = "CCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
 
    public List<Product> findByCategory(byte categoryId) {        
        List<Product> list = getEntityManager().createQuery("SELECT p FROM Product p WHERE p.categoryId.id = " + categoryId, Product.class).getResultList();        
        return list;        
    }
}
