package tk.droidroot.transactions.Entities;

import javax.persistence.*;

@Entity
public class Mark {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private byte value;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Mentor mentor;

    public Mark(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", student=" + student +
                ", mentor=" + mentor +
                '}';
    }
}
