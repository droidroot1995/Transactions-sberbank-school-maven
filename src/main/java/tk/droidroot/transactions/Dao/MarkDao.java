package tk.droidroot.transactions.Dao;

import tk.droidroot.transactions.Entities.Mark;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class MarkDao {

    private EntityManager entityManager;

    public MarkDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Mark read(Long id){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Mark mark = entityManager.find(Mark.class, id);
        transaction.commit();
        return mark;
    }

    public List<Mark> readAll(){
        EntityTransaction transaction = entityManager.getTransaction();
        if(!transaction.isActive()){
            transaction.begin();
        }

        List<Mark> marks = entityManager
                .createQuery("SELECT m FROM Mark m")
                .getResultList();

        if(transaction.isActive()){
            transaction.commit();
        }

        return marks;
    }

    public void removeAll(){
        EntityTransaction transaction = entityManager.getTransaction();
        if(!transaction.isActive()){
            transaction.begin();
        }

        entityManager
                .createQuery("DELETE FROM Mark")
                .executeUpdate();

        if(transaction.isActive()){
            transaction.commit();
        }

    }

    public Mark create(Mark mark){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(mark);
        transaction.commit();
        return mark;
    }
}
