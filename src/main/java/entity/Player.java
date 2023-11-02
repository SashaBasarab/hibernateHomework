package entity;

import exceptions.MmrNullException;
import interfaces.PeoplesActionWhenMatchBegins;
import interfaces.Vacation;

import javax.persistence.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Entity
@Table(name = "player")
public class Player extends Person implements Vacation, Comparable<Player>, PeoplesActionWhenMatchBegins, Runnable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nickname;

    private String nationality;

    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hero_ID", referencedColumnName = "id")
    private Hero hero;

    private int freeTimeInHoursPerWeek;

    private Integer mmr;

    private String email;

    private String password;

    private boolean isRegistered = false;

    @ManyToOne
    private Team team;

    public Player() {

    }

    public Player(String name, int age, String nickname, String nationality, String role, Hero hero) {
        super(name, age);
        this.nickname = nickname;
        this.nationality = nationality;
        this.role = role;
        this.hero = hero;
        try {
            createFile();
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
    }

    public Player(String name, int age, String nickname, String nationality, String role, String email, String password) {
        super(name, age);
        this.nickname = nickname;
        this.nationality = nationality;
        this.role = role;
        this.email = email;
        this.password = password;
//        try {
//            createFile();
//        } catch (IOException e) {
//            System.out.println("Something went wrong");
//        }
    }

    public Player(String name, int age, String nickname) {
        super(name, age);
        this.nickname = nickname;
    }

    private void createFile() throws IOException {
        File file = new File("D:/KindGeek Java Projects/Homework/src/working_with_files/" + this.nickname + "_information.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file, false);
        fileWriter.append("Player's name: " + getName() +
                ", \nplayer's nickname: " + this.nickname +
                ", \nplayer's role: " + this.role +
                ", \nplayer's nationality: " + this.nationality + "\n---------------------------").append("\nPlayer's hero name: " + hero.getName() +
                ", \nhero's role: " + hero.getRole());
        fileWriter.close();
    }

    @Override
    public void sayName(){
        System.out.println("Hi, my name is " + getName() + ", I'm a player and my role is: " + this.role);
    }

    @Override
    public void communicationWithTeam(){
        System.out.println("WHERE IS ******* GANK, YOU ****************");
    }

    public void gankRequest(Player player){
        if (hero.gankPossibility()){
            System.out.println("Ok, " + player.getNickname() + ", I'll help you");
        }else{
            System.out.println("Sorry, " + player.getNickname() + " I can't help you right now");
        }
    }

    @Override
    public void doWork() {
        System.out.println("Player " + this.nickname + " is playing match");
    }

    public void training() {
        freeTimeInHoursPerWeek -= Trainer.TRAINING_DURATION_IN_HOURS;
        System.out.println(this.nickname + "'s training is successfully completed");
    }

    @Override
    public boolean registrationForMatch() {
        this.isRegistered = true;
        System.out.println("Player " + this.nickname + " is registered for the match");
        return true;
    }

    @Override
    public void goOnVacation(int vacationDuration) {
        System.out.println("Player " + getName() + " goes on vacation for " + vacationDuration + " days");
        setEnergy(100);
        freeTimeInHoursPerWeek += 6;
    }

    @Override
    public void informationAboutVacation(int vacationDuration) {
        System.out.println("Player " + getName() + " was on vacation for " + vacationDuration + " days, his energy now is: " + getEnergy() + " he can train now more often");
    }

    @Override
    public int compareTo(Player o) {
        try {
            this.checkMmr();
        } catch (MmrNullException e) {
            e.printStackTrace();
        }
        if (this.mmr.compareTo(o.getMmr()) == 0) {
            return this.nickname.compareTo(o.getNickname());
        }
        return this.mmr.compareTo(o.getMmr());
    }

    void checkMmr() throws MmrNullException{
        if (this.mmr == null) {
            throw new MmrNullException("Mmr is not set");
        }
    }

    private void reflectionCheck() {
        System.out.println("Method was invoked");
    }

    @Override
    public void beginActivityDuringTheMatch() {
        System.out.println("Player " + this.nickname + " is playing");
    }

    @Override
    public void run() {
        sayName();
        for (int i = 0; i < 10; i++) {
            beginActivityDuringTheMatch();
            System.out.println(this.mmr);
        }
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public int getFreeTimeInHoursPerWeek() {
        return freeTimeInHoursPerWeek;
    }

    public void setFreeTimeInHoursPerWeek(int freeTimeInHoursPerWeek) {
        this.freeTimeInHoursPerWeek = freeTimeInHoursPerWeek;
    }

    public Integer getMmr() {
        return mmr;
    }

    public void setMmr(Integer mmr) {
        this.mmr = mmr;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public void setRegistered(boolean registered) {
        isRegistered = registered;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
