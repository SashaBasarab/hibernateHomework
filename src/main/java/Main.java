import entity.Hero;
import entity.Item;
import enums.HeroName;
import enums.HeroRole;
import exceptions.InvalidEmailFormatException;
import repository.HeroRepository;
import repository.ItemRepository;
import service.BuyItemForHero;
import service.PickHero;
import service.Registration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) throws InvalidEmailFormatException {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("primary");
        EntityManager manager = factory.createEntityManager();
        HeroRepository heroRepository = new HeroRepository(manager);
        Hero hero = new Hero(HeroName.AXE.name(), HeroRole.HARDLINER.name(), 30, 10, 15);
        Item item = new Item("Desolator", 3500);
        ItemRepository itemRepository = new ItemRepository(manager);
        heroRepository.save(hero);
        itemRepository.save(item);
        manager.close();
        factory.close();

        Registration registration = new Registration("player");
        registration.registration();

        PickHero pickHero = new PickHero();
        pickHero.pickHero(1L);

        BuyItemForHero buyItemForHero = new BuyItemForHero();
        buyItemForHero.buyItemForHero(1L);

    }
}
