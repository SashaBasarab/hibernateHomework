package service;

import entity.Player;
import entity.Trainer;
import entity.Visitor;
import exceptions.InvalidEmailFormatException;
import repository.MatchRepository;
import repository.PlayerRepository;
import repository.TrainerRepository;
import repository.VisitorRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Registration implements interfaces.Registration {

    private Visitor visitor;

    private Trainer trainer;

    private Player player;

    private String kindOfPerson;

    public Registration(String kindOfPerson) {
        this.kindOfPerson = kindOfPerson;
    }

    public void registration() throws InvalidEmailFormatException {

        switch (kindOfPerson.toLowerCase(Locale.ROOT)) {

            case("visitor"):
                visitorRegistration();
                break;

            case("trainer"):
                trainerRegistration();
                break;

            case("player"):
                playerRegistration();
                break;

            default:
                throw new InvalidEmailFormatException("Sorry, you've typed invalid kind of person to registrate");

        }

    }

    private void playerRegistration() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("primary");
        EntityManager manager = factory.createEntityManager();

        try {
            inputInformationAboutPlayer();
        } catch (InvalidEmailFormatException e) {
            System.out.println(e);
            return;
        }

        PlayerRepository playerRepository = new PlayerRepository(manager);
        playerRepository.save(player);

        factory.close();
        manager.close();
    }

    private void trainerRegistration() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("primary");
        EntityManager manager = factory.createEntityManager();

        try {
            inputInformationAboutTrainer();
        } catch (InvalidEmailFormatException e) {
            System.out.println(e);
            return;
        }

        TrainerRepository trainerRepository = new TrainerRepository(manager);
        trainerRepository.save(trainer);

        factory.close();
        manager.close();
    }

    private void visitorRegistration() {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("primary");
        EntityManager manager = factory.createEntityManager();

        try {
            inputIformationAboutVisitor();
        } catch (InvalidEmailFormatException e) {
            System.out.println(e);
            return;
        }

        VisitorRepository visitorRepository = new VisitorRepository(manager);
        visitorRepository.save(visitor);

        factory.close();
        manager.close();
    }

    private void inputInformationAboutPlayer() throws InvalidEmailFormatException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = sc.next();

        if (!isValidEmailAddress(email)) {
            throw new InvalidEmailFormatException("Sorry, you've typed incorrect email");
        }

        System.out.println("Enter your password: ");
        String password = sc.next();

        System.out.println("Enter your name: ");
        String name = sc.next();

        System.out.println("Enter your age: ");
        int age = Integer.parseInt(sc.next());

        System.out.println("Enter your nickname: ");
        String nickname = sc.next();

        System.out.println("Enter your nationality: ");
        String nationality = sc.next();

        System.out.println("Enter your role: ");
        String role = sc.next();

        this.player = new Player(name, age, nickname, nationality, role, email, password);

        sc.close();

    }

    private void inputInformationAboutTrainer() throws InvalidEmailFormatException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = sc.next();

        if (!isValidEmailAddress(email)) {
            throw new InvalidEmailFormatException("Sorry, you've typed incorrect email");
        }

        System.out.println("Enter your password: ");
        String password = sc.next();

        System.out.println("Enter your experience in years: ");
        int experienceInYears = Integer.parseInt(sc.next());

        System.out.println("Enter your salary: ");
        int salary = Integer.parseInt(sc.next());

        this.trainer = new Trainer(experienceInYears, salary, email, password);

        sc.close();

    }

    private void inputIformationAboutVisitor() throws InvalidEmailFormatException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your email: ");
        String email = sc.next();

        if (!isValidEmailAddress(email)) {
            throw new InvalidEmailFormatException("Sorry, you've typed incorrect email");
        }

        System.out.println("Enter your password: ");
        String password = sc.next();

        System.out.println("Enter match id you want to buy a ticket");
        Long matchId = Long.valueOf(sc.next());


        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("primary");
        EntityManager manager = factory.createEntityManager();

        MatchRepository matchRepository = new MatchRepository(manager);

        this.visitor = new Visitor(matchRepository.findById(matchId), email, password);

        sc.close();
        factory.close();
        manager.close();
    }

    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getKindOfPerson() {
        return kindOfPerson;
    }

    public void setKindOfPerson(String kindOfPerson) {
        this.kindOfPerson = kindOfPerson;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(visitor, that.visitor) && Objects.equals(kindOfPerson, that.kindOfPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitor, kindOfPerson);
    }

}
