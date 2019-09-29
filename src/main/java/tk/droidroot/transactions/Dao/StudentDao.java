package tk.droidroot.transactions.Dao;

import tk.droidroot.transactions.Entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class StudentDao {

    private EntityManager entityManager;

    public StudentDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Student read(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Student student = entityManager.find(Student.class, id);
        transaction.commit();
        return student;
    }

    public List<Student> readAll(){
        EntityTransaction transaction = entityManager.getTransaction();
        if(!transaction.isActive()){
            transaction.begin();
        }

        List<Student> students = entityManager
                .createQuery("SELECT m FROM Student m")
                .getResultList();

        if(transaction.isActive()){
            transaction.commit();
        }

        return students;
    }

    public void removeAll(){
        EntityTransaction transaction = entityManager.getTransaction();
        if(!transaction.isActive()){
            transaction.begin();
        }

        entityManager
                .createQuery("DELETE FROM Student")
                .executeUpdate();

        if(transaction.isActive()){
            transaction.commit();
        }

    }

    public Student create(Student student){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
        return student;
    }

}
