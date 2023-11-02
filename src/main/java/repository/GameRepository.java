package repository;

import entity.Game;

import javax.persistence.EntityManager;
import java.util.List;

public class GameRepository {

    private final EntityManager manager;

    public GameRepository(EntityManager entityManager) {
        this.manager = entityManager;
    }

    public void save(Game game) {
        manager.getTransaction().begin();
        manager.persist(game);
        manager.getTransaction().commit();
    }

    public void update(Game game) {
        manager.getTransaction().begin();
        manager.merge(game);
        manager.getTransaction().commit();
    }

    public void delete(Game game) {
        manager.getTransaction().begin();
        manager.remove(game);
        manager.getTransaction().commit();
    }

    public Game findById(Long id) {
        return manager
                .createQuery("select g from Game g where g.id = :id", Game.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Game> findAll() {
        return manager
                .createQuery("select g from Game g", Game.class)
                .getResultList();
    }

}
