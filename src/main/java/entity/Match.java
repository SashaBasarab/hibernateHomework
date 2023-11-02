package entity;

import exceptions.CantBeginMatchException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Game game;

    @ManyToMany(mappedBy = "matches")
    private List<Team> teams;

    @OneToMany(mappedBy = "match")
    private List<Visitor> visitors;

    public static final int MMR_POINTS_PER_MATCH = 25;

    private boolean isStarted = false;

    public Match() {

    }

    public Match(List<Team> teams) {
        this.teams = teams;
    }

    public void beginMatch() throws CantBeginMatchException{
        if (teams.get(1).teamIsReadyForTheMatch() && teams.get(2).teamIsReadyForTheMatch()){
            System.out.println("The match has begun, first team is: " + teams.get(1).getName() + " and the second team is: " + teams.get(2).getName());
        }else{
            throw new CantBeginMatchException("Some team is not ready yet");
        }
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
