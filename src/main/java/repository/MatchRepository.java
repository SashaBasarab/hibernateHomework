package repository;

import entity.Match;

import javax.persistence.EntityManager;
import java.util.List;

public class MatchRepository {

    private final EntityManager manager;

    public MatchRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Match match) {
        manager.getTransaction().begin();
        manager.persist(match);
        manager.getTransaction().commit();
    }

    public void update(Match match) {
        manager.getTransaction().begin();
        manager.merge(match);
        manager.getTransaction().commit();
    }

    public void delete(Match match) {
        manager.getTransaction().begin();
        manager.remove(match);
        manager.getTransaction().commit();
    }

    public Match findById(Long id) {
        return manager
                .createQuery("select m from Match m where m.id = :id", Match.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Match> findAll() {
        return manager
                .createQuery("select m from Match m", Match.class)
                .getResultList();
    }

}
