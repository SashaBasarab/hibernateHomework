package repository;

import entity.Item;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemRepository {

    private final EntityManager manager;

    public ItemRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Item item) {
        manager.getTransaction().begin();
        manager.persist(item);
        manager.getTransaction().commit();
    }

    public void update(Item item) {
        manager.getTransaction().begin();
        manager.merge(item);
        manager.getTransaction().commit();
    }

    public void delete(Item item) {
        manager.getTransaction().begin();
        manager.remove(item);
        manager.getTransaction().commit();
    }

    public Item findById(Long id) {
        return manager
                .createQuery("select i from Item i where i.id = :id", Item.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Item findByName(String name) {
        return manager
                .createQuery("select i from Item i where i.name = :name", Item.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Item> findAll() {
        return manager
                .createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
