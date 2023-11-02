package entity;

import exceptions.SalaryNullException;
import interfaces.MatchResult;
import interfaces.PeoplesActionWhenMatchBegins;
import interfaces.Vacation;

import javax.persistence.*;

@Entity
@Table(name = "trainer")
public class Trainer extends Person implements MatchResult, Vacation, PeoplesActionWhenMatchBegins, Runnable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer experienceInYears;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    public static final int TRAINING_DURATION_IN_HOURS = 2;

    private Integer salary;

    private String email;

    private String password;

    public Trainer(String name, int age, Integer experienceInYears) {
        super(name, age);
        this.experienceInYears = experienceInYears;
    }

    public Trainer(Integer experienceInYears, Integer salary, String email, String password) {
        this.experienceInYears = experienceInYears;
        this.salary = salary;
        this.email = email;
        this.password = password;
    }

    public Trainer() {

    }

    public void trainingApproach(){
        if (team.teamAverageTimeInHoursPerWeeek() < 10){
            System.out.println("this is not enough, so we will train harder");
        } else {
            System.out.println("Ok, this is enough, we will train as usual");
        }
    }

    @Override
    public void doWork() {
        System.out.println("Trainer " + getName() + " is training players");
        this.team.getPlayers().stream().forEach(Player::training);
    }

    @Override
    public boolean registrationForMatch() {
        if (team.checkTeamRegistration()){
            System.out.println("Trainer " + getName() + " can register for match");
            return true;
        } else {
            System.out.println("Trainer " + getName() + " can't register for match because team is not registered yet");
            return false;
        }
    }

    private void checkSalary() throws SalaryNullException {
        if (this.salary == null) {
            throw new SalaryNullException("Salary is not set");
        }
    }

    @Override
    public void wonGame() {
        try {
            checkSalary();
        } catch (SalaryNullException e) {
            e.printStackTrace();
            return;
        }
        this.salary += 100;
        System.out.println("This month my salary will be increased: " + this.salary + "$");
    }

    @Override
    public void lostGame() {
        try {
            checkSalary();
        } catch (SalaryNullException e) {
            e.printStackTrace();
            return;
        }
        this.salary -= 100;
        setEnergy(getEnergy() - 5);
        System.out.println("This month my salary will be decreased: " + this.salary + "$");
    }

    @Override
    public void goOnVacation(int vacationDuration) {
        System.out.println("Trainer " + getName() + " goes on vacation for " + vacationDuration + " days");
        setEnergy(100);
    }

    @Override
    public void informationAboutVacation(int vacationDuration) {
        System.out.println("Trainer " + getName() + " was on vacation for " + vacationDuration + " days, his energy now is: " + getEnergy());
    }

    @Override
    public void beginActivityDuringTheMatch() {
        System.out.println("Trainer " + getName() + " is giving pieces of advice to his team");
    }

    @Override
    public void run() {
        sayName();
        for (int i = 0; i < 5; i++) {
            int randomNum = 1 + (int) Math.random() * 2;
            if (randomNum == 1){
                this.team.wonGame();
            } else {
                this.team.lostGame();
            }
            try {
                checkSalary();
            } catch (SalaryNullException e) {
                e.printStackTrace();
            }
        }

    }

    public Integer getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(Integer experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getTeamName(){
        return this.team.getName();
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
