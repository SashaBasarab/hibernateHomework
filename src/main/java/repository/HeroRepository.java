package repository;

import entity.Hero;
import enums.HeroName;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Locale;

public class HeroRepository {

    private final EntityManager manager;

    public HeroRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Hero hero) {
        manager.getTransaction().begin();
        manager.persist(hero);
        manager.getTransaction().commit();
    }

    public void update(Hero hero) {
        manager.getTransaction().begin();
        manager.merge(hero);
        manager.getTransaction().commit();
    }

    public void delete(Hero hero) {
        manager.getTransaction().begin();
        manager.remove(hero);
        manager.getTransaction().commit();
    }

    public Hero findById(Long id) {
        return manager
                .createQuery("select h from Hero h where h.id = :id", Hero.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Hero> findAll() {
        return manager
                .createQuery("select h from Hero h", Hero.class)
                .getResultList();
    }

    public List<Hero> findByRole(String role) {
        return manager
                .createQuery("select h from Hero h where h.role = :role", Hero.class)
                .setParameter("role", role.toUpperCase(Locale.ROOT))
                .getResultList();

    }

}
