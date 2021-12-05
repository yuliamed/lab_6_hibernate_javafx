import javax.persistence.*;

@Entity
@Table(name="animals")
public class Animal {
    @Column(name="name")
    private String name;
    @Column(name="nickname")
    private String nickname;
    @Column(name="color")
    private String color;
    @Column(name="breed")
    private String breed;
    @Column(name="birthdate")
    private String birthdate;
    @Column(name="cost")
    private double cost;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idanimal")
    private int id;

    public Animal(String name, String nickname, String color, String breed, String birthdate, double cost) {
        this.name = name;
        this.nickname = nickname;
        this.color = color;
        this.breed = breed;
        this.birthdate = birthdate;
        this.cost = cost;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    //public void setId(int id) {
        //this.id = id;
    //}

    public Animal() {
    }
}
