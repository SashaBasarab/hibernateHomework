package repository;

import entity.Pick;
import entity.Team;

import javax.persistence.EntityManager;
import java.util.List;

public class PickRepository {

    private final EntityManager manager;

    public PickRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Pick pick) {
        manager.getTransaction().begin();
        manager.persist(pick);
        manager.getTransaction().commit();
    }

    public void update(Pick pick) {
        manager.getTransaction().begin();
        manager.merge(pick);
        manager.getTransaction().commit();
    }

    public void delete(Pick pick) {
        manager.getTransaction().begin();
        manager.remove(pick);
        manager.getTransaction().commit();
    }

    public Pick findById(Long id) {
        return manager
                .createQuery("select p from Pick p where p.id = :id", Pick.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Pick findPickByTeam(Team team) {
        return manager
                .createQuery("select p from Pick p where p.team = :team", Pick.class)
                .setParameter("team", team)
                .getSingleResult();
    }

    public List<Pick> findAll() {
        return manager
                .createQuery("select p from Pick p", Pick.class)
                .getResultList();
    }

}
