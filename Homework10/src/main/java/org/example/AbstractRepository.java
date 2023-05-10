package org.example;




import jakarta.persistence.EntityManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface AbstractRepository<T, ID extends Serializable> extends CrudRepository<T, ID> {

    List<T> findByName(String name);
    boolean persist(T entity);
    void setEntityManager(EntityManager entityManager);
    EntityManager getEntityManager();
    void rollback();
    void handleException(Exception exception);
    void commit();
    void beginTransaction();

}
