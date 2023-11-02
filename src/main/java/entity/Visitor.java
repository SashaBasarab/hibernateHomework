package entity;

import javax.persistence.*;

@Entity
@Table(name = "visitor")
public class Visitor extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    private Match match;

    private String email;

    private String password;

    public Visitor() {
    }

    public Visitor(String name, int age) {
        super(name, age);
    }

    public Visitor(Match match, String email, String password) {
        this.match = match;
        this.email = email;
        this.password = password;
    }

    @Override
    public void doWork() {
        System.out.println("Visitor bought a ticked for the match");
    }

    @Override
    public boolean registrationForMatch() {
        return false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
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
