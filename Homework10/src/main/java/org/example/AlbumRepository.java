package org.example;




import org.example.utils.EntityManagerFactorySingleton;

import org.example.Album;
import java.util.List;
import jakarta.persistence.EntityManager;
import java.util.Optional;
import java.util.*;

public class AlbumRepository implements AbstractRepository<Album, Integer> {
    private EntityManager entityManager;

    public AlbumRepository() {
        EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
        setEntityManager(entityManagerFactorySingleton.getEntityManager());
    }

    @Override
    public List<Album> findByName(String name) {
        return (List<Album>) getEntityManager().createNamedQuery("Album.findByName").setParameter("name", name).getResultList();
    }

    @Override
    public boolean persist(Album entity) {
        try {
            beginTransaction();
            entityManager.persist(entity);
            commit();
            return true;
        } catch (Exception e) {
            rollback();
            return false;
        }
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

    public Artist findById(Long id) {
        return (Artist) entityManager.createNamedQuery("Album.findById").setParameter("id", id).getSingleResult();
    }

    public Long findId() {
        return (Long) getEntityManager().createNamedQuery("Album.findId").getSingleResult();
    }

    @Override
    public <S extends Album> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Album> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Album> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Album> findAll() {
        return null;
    }

    @Override
    public Iterable<Album> findAllById(Iterable<Integer> integers) {
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
    public void delete(Album entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Album> entities) {

    }

    @Override
    public void deleteAll() {

    }

    public void create(EntityManager entityManager, Album album) {
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
//        entityManager.close();
    }
}


//
//public class AlbumRepository extends DataRepository<Album, Integer> {
//    public AlbumRepository() {
//        EntityManagerFactorySingleton entityManagerFactorySingleton = EntityManagerFactorySingleton.getInstance();
//        setEntityManager(entityManagerFactorySingleton.getEntityManager());
//    }
//
//    public Artist findById(Long id) {
//        return (Artist) getEntityManager().createNamedQuery("Album.findById").setParameter("id", id).getSingleResult();
//    }
//
//    public Long findId() {
//        return (Long) getEntityManager().createNamedQuery("Album.findId").getSingleResult();
//    }
//
//    public List<Album> findByName(String name) {
//        return (List<Album>) getEntityManager().createNamedQuery("Album.findByName").setParameter("name", name).getResultList();
//    }
//
//    public void create(Album album) {
//        beginTransaction();
//        getEntityManager().persist(album);
//        commit();
//    }
//}
