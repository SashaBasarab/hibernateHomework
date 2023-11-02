package repository;

import entity.Team;
import entity.Trainer;

import javax.persistence.EntityManager;
import java.util.List;

public class TeamRepository {

    private final EntityManager manager;

    public TeamRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Team team) {
        manager.getTransaction().begin();
        manager.persist(team);
        manager.getTransaction().commit();
    }

    public void update(Team team) {
        manager.getTransaction().begin();
        manager.merge(team);
        manager.getTransaction().commit();
    }

    public void delete(Team team) {
        manager.getTransaction().begin();
        manager.remove(team);
        manager.getTransaction().commit();
    }

    public Team findById(Long id) {
        return manager
                .createQuery("select t from Team t where t.id = :id", Team.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Team findTeamByTrainer(Trainer trainer) {
        return manager
                .createQuery("select t from Team t where t.trainer = :trainer", Team.class)
                .setParameter("trainer", trainer)
                .getSingleResult();
    }

    public List<Team> findAll() {
        return manager
                .createQuery("select t from Team t", Team.class)
                .getResultList();
    }

}
