package repository;

import entity.Team;
import entity.Trainer;

import javax.persistence.EntityManager;
import java.util.List;

public class TrainerRepository {

    private final EntityManager manager;

    public TrainerRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Trainer trainer) {
        manager.getTransaction().begin();
        manager.persist(trainer);
        manager.getTransaction().commit();
    }

    public void update(Trainer trainer) {
        manager.getTransaction().begin();
        manager.merge(trainer);
        manager.getTransaction().commit();
    }

    public void remove(Trainer trainer) {
        manager.getTransaction().begin();
        manager.remove(trainer);
        manager.getTransaction().commit();
    }

    public Trainer findById(Long id) {
        return manager
                .createQuery("select t from Trainer t where t.id=:id", Trainer.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Trainer findByTeam(Team team) {
        return manager
                .createQuery("select t from Trainer t where t.team =:team", Trainer.class)
                .setParameter("team", team)
                .getSingleResult();
    }

    public List<Trainer> findAll() {
        return manager
                .createQuery("select t from Trainer t", Trainer.class)
                .getResultList();
    }

}
