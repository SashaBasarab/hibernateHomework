package service;

import entity.Player;
import repository.HeroRepository;
import repository.PlayerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class PickHero {

    private Player player;

    public void pickHero(Long playerId) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter hero id you want to pick: ");
        Long heroId = Long.valueOf(sc.next());

        EntityManagerFactory managerFactory =
                Persistence.createEntityManagerFactory("primary");
        EntityManager manager = managerFactory.createEntityManager();

        HeroRepository heroRepository = new HeroRepository(manager);
        PlayerRepository playerRepository = new PlayerRepository(manager);

        this.player = playerRepository.findById(playerId);
        this.player.setHero(heroRepository.findById(heroId));

        playerRepository.update(player);

    }

}
