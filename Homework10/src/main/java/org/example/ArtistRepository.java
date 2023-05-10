package org.example;



import jakarta.persistence.EntityManager;
import org.example.utils.EntityManagerFactorySingleton;

import java.util.List;
import java.util.Optional;

//import static java.security.cert.CertStore.handleException;
//import static jdk.jfr.events.FileForceEvent.commit;
import static org.h2.mvstore.MVStoreTool.rollback;

public class ArtistRepository implements AbstractRepository<Artist, Integer> {

    private EntityManager entityManager;

    public ArtistRepository() {
        EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
        setEntityManager(entityManagerFactorySingleton.getEntityManager());
    }

    @Override
    public List<Artist> findByName(String name) {
        return (List<Artist>) getEntityManager().createNamedQuery("Artist.findByName").setParameter("name", name).getResultList();
    }

    public Long findId() {
        return (Long) getEntityManager().createNamedQuery("Artist.findId").getSingleResult();
    }

    public void create(EntityManager entityManager, Artist artist) {
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
//        entityManager.close();
    }

    @Override
    public boolean persist(Artist entity) {
        try {
            beginTransaction();
            EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
            EntityManager entityManager = entityManagerFactorySingleton.getEntityManager();
            entityManager.persist(entity);
            commit();
            return true;
        } catch (Exception e) {
            handleException(e);
            rollback();
        }
        return false;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void rollback() {
        entityManager.getTransaction().rollback();
    }

    @Override
    public void handleException(Exception exception) {
        System.out.println(exception);
    }

    @Override
    public void commit() {
        entityManager.getTransaction().commit();
    }

    @Override
    public void beginTransaction() {
        entityManager.getTransaction().begin();
    }

    @Override
    public <S extends Artist> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Artist> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Artist> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Artist> findAll() {
        return null;
    }

    @Override
    public Iterable<Artist> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Artist entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Artist> entities) {

    }

    @Override
    public void deleteAll() {

    }
}


//public class ArtistRepository extends DataRepository<Artist, Integer> {
//    public ArtistRepository() {
//        EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
//        setEntityManager(entityManagerFactorySingleton.getEntityManager());
//    }
//
//    public Artist findById(Long id) {
//        return (Artist) getEntityManager().createNamedQuery("Artist.findById").setParameter("id", id).getSingleResult();
//    }
//
//    public Long findId() {
//        return (Long) getEntityManager().createNamedQuery("Artist.findId").getSingleResult();
//    }
//
//    public List<Artist> findByName(String name) {
//        return (List<Artist>) getEntityManager().createNamedQuery("Artist.findByName").setParameter("name", name).getResultList();
//    }
//
//    public void create(EntityManager entityManager, Artist artist) {
//        entityManager.getTransaction().begin();
//        entityManager.persist(artist);
//        entityManager.getTransaction().commit();
////        entityManager.close();
//    }
//}
