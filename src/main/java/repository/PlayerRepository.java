package repository;

import entity.Player;
import entity.Team;

import javax.persistence.EntityManager;
import java.util.List;

public class PlayerRepository {

    private final EntityManager manager;

    public PlayerRepository(EntityManager manager) {
        this.manager = manager;
    }

    public void save(Player player) {
        manager.getTransaction().begin();
        manager.persist(player);
        manager.getTransaction().commit();
    }

    public void update(Player player) {
        manager.getTransaction().begin();
        manager.merge(player);
        manager.getTransaction().commit();
    }

    public void delete(Player player) {
        manager.getTransaction().begin();
        manager.remove(player);
        manager.getTransaction().commit();
    }

    public Player findById(Long id) {
        return manager
                .createQuery("select p from Player p where p.id = :id", Player.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Player> findAll() {
        return manager
                .createQuery("select p from Player p", Player.class)
                .getResultList();
    }

    public List<Player> findAllByTeam(Team team) {
        return manager
                .createQuery("select p from Player p where p.team = :team", Player.class)
                .setParameter("team", team)
                .getResultList();
    }

}
