package entity;

import exceptions.NotEnoughManaException;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String role;

    private int strength;

    private int agility;

    private int intelligence;

    public static final int STRENGTH_СOEFFICIENT = 28;

    public static final double AGILITY_COEFFICIENT = 0.14;

    public static final int INTELLIGENCE_COEFFICIENT = 17;

    private int maxHealth = strength * STRENGTH_СOEFFICIENT;

    private int armor = (int) Math.round(agility * AGILITY_COEFFICIENT);

    private int maxMana = intelligence * INTELLIGENCE_COEFFICIENT;

    private int currentHealth;

    private int currentMana;

    @ManyToOne
    private Pick pick;

    @ManyToOne
    private Game game;

    @ManyToMany(mappedBy = "heroes")
    private List<Item> items;


    @OneToOne(mappedBy = "hero")
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public Hero() {

    }

    public Hero(String name, String role, int strength, int agility, int intelligence) {
        this.name = name;
        this.role = role;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }

    public void useAbility(){
        System.out.println("This ability doesn't require any mana");
    }

    public void useAbility(int mana) throws NotEnoughManaException{
        if (this.currentMana > mana){
            System.out.println("This ability requires " + mana + " mana, you can use it");
        }
        else {
            throw new NotEnoughManaException("This ability requires " + mana + " mana, you don't have enough mana");
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public boolean gankPossibility(){
        if ((this.currentHealth < (this.maxHealth / 2)) || (this.currentMana < (this.maxMana / 2))){
            System.out.println("I'm not able to gank");
            return false;
        }else {
            System.out.println("I'm ready to gank");
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return strength == hero.strength && agility == hero.agility && intelligence == hero.intelligence && maxHealth == hero.maxHealth && armor == hero.armor && maxMana == hero.maxMana && currentHealth == hero.currentHealth && currentMana == hero.currentMana && Objects.equals(name, hero.name) && Objects.equals(role, hero.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, role, strength, agility, intelligence, maxHealth, armor, maxMana, currentHealth, currentMana);
    }

    public Game getGame() {
        return game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getArmor() {
        return armor;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public Pick getPick() {
        return pick;
    }

    public void setPick(Pick pick) {
        this.pick = pick;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
