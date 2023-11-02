package entity;

import interfaces.PeoplesActionWhenMatchBegins;
import jdk.jfr.Unsigned;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany(mappedBy = "game")
    private Set<Hero> heroes;

    @OneToMany(mappedBy = "game")
    private Set<Item> items;

    @OneToMany(mappedBy = "game")
    private List<Match> matches;


//    private List<PeoplesActionWhenMatchBegins> allPeoplesActionDuringTheMatch = new ArrayList<>();

    public Game(Set<Hero> heroes, Set<Item> items) {
        this.heroes = heroes;
        this.items = items;
        this.matches = new ArrayList<>();
    }

    public Game() {
        this.heroes = new HashSet<>();
        this.items = new HashSet<>();
        this.matches = new ArrayList<>();
    }

//    public void beginMatch() {
//        System.out.println("Match is started ----------------");
//        allPeoplesActionDuringTheMatch.stream().forEach(PeoplesActionWhenMatchBegins::beginActivityDuringTheMatch);
//    }
//
//    public void addPeople(PeoplesActionWhenMatchBegins peoplesActionWhenMatchBegins) {
//        this.allPeoplesActionDuringTheMatch.add(peoplesActionWhenMatchBegins);
//    }

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) {
        this.matches.add(match);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public List<PeoplesActionWhenMatchBegins> getAllPeoplesActionDuringTheMatch() {
//        return allPeoplesActionDuringTheMatch;
//    }
//
//    public void setAllPeoplesActionDuringTheMatch(List<PeoplesActionWhenMatchBegins> allPeoplesActionDuringTheMatch) {
//        this.allPeoplesActionDuringTheMatch = allPeoplesActionDuringTheMatch;
//    }
}
