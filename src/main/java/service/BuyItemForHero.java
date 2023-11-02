package service;

import entity.Hero;
import repository.HeroRepository;
import repository.ItemRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class BuyItemForHero {

    private Hero hero;

    public void buyItemForHero(Long heroId) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter item name: ");
        String itemName = sc.next();

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("primary");
        EntityManager manager = factory.createEntityManager();

        HeroRepository heroRepository = new HeroRepository(manager);
        this.hero = heroRepository.findById(heroId);

        ItemRepository itemRepository = new ItemRepository(manager);
        this.hero.addItem(itemRepository.findByName(itemName));

        heroRepository.update(this.hero);

    }

}
