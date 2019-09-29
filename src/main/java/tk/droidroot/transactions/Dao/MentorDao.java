package tk.droidroot.transactions.Dao;

import tk.droidroot.transactions.Entities.Mentor;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MentorDao {

    private EntityManager entityManager;

    public MentorDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Mentor read(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Mentor mentor = entityManager.find(Mentor.class, id);
        transaction.commit();
        return mentor;
    }

    public List<Mentor> readAll(){
        EntityTransaction transaction = entityManager.getTransaction();
        if(!transaction.isActive()){
            transaction.begin();
        }

        List<Mentor> mentors = entityManager
                .createQuery("SELECT m FROM Mentor m")
                .getResultList();

        if(transaction.isActive()){
            transaction.commit();
        }

        return  mentors;
    }

    public void removeAll(){
        EntityTransaction transaction = entityManager.getTransaction();
        if(!transaction.isActive()){
            transaction.begin();
        }

        entityManager
                .createQuery("DELETE FROM Mentor")
                .executeUpdate();

        if(transaction.isActive()){
            transaction.commit();
        }

    }

    public Mentor create(Mentor mentor){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mentor);
        transaction.commit();
        return mentor;
    }
}
