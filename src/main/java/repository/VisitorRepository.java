package repository;

import entity.Match;
import entity.Visitor;

import javax.persistence.EntityManager;
import java.util.List;

public class VisitorRepository {
    
    private final EntityManager manager;

    public VisitorRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Visitor visitor) {
        manager.getTransaction().begin();
        manager.persist(visitor);
        manager.getTransaction().commit();
    }

    public void update(Visitor visitor) {
        manager.getTransaction().begin();
        manager.merge(visitor);
        manager.getTransaction().commit();
    }

    public void delete(Visitor visitor) {
        manager.getTransaction().begin();
        manager.remove(visitor);
        manager.getTransaction().commit();
    }

    public Visitor findById(Long id) {
        return manager
                .createQuery("select v from Visitor v where v.id=:id", Visitor.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Visitor> findAll() {
        return manager
                .createQuery("select v from Visitor v", Visitor.class)
                .getResultList();
    }

    public List<Visitor> findAllByMatchId(Match match) {
        return manager
                .createQuery("select v from Visitor v where v.match =:match", Visitor.class)
                .setParameter("match", match)
                .getResultList();
    }

}
