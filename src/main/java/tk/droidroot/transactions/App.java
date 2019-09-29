package tk.droidroot.transactions;

import tk.droidroot.transactions.Dao.MarkDao;
import tk.droidroot.transactions.Dao.MentorDao;
import tk.droidroot.transactions.Dao.StudentDao;
import tk.droidroot.transactions.Entities.Mark;
import tk.droidroot.transactions.Entities.Mentor;
import tk.droidroot.transactions.Entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory("tk.droidroot.transactions.entity");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        MentorDao mentorDao = new MentorDao(entityManager);
        StudentDao studentDao = new StudentDao(entityManager);
        MarkDao markDao = new MarkDao(entityManager);


        System.out.println("Checking number of rows in each table");
        System.out.println();
        System.out.println("Mentors table: " + mentorDao.readAll().size());
        System.out.println("Students table: " + studentDao.readAll().size());
        System.out.println("Mark table: " + markDao.readAll().size());
        System.out.println();


        if((markDao.readAll().size() > 0) || (mentorDao.readAll().size() > 0) || (studentDao.readAll().size() > 0)) {
            System.out.println("Clearing tables");
            System.out.println();
        }

        if(markDao.readAll().size() > 0) {
            System.out.println("Clearing Mark table");
            markDao.removeAll();
            System.out.println("Mark table was cleared");
            System.out.println();
        }

        if(studentDao.readAll().size() > 0) {
            System.out.println("Clearing Student table");
            studentDao.removeAll();
            System.out.println("Student table was cleared");
            System.out.println();
        }

        if(mentorDao.readAll().size() > 0) {
            System.out.println("Clearing Mentor table");
            mentorDao.removeAll();
            System.out.println("Mentor table was cleared");
            System.out.println();
        }

        System.out.println("Checking number of rows in each table");
        System.out.println();
        System.out.println("Mentors table: " + mentorDao.readAll().size());
        System.out.println("Students table: " + studentDao.readAll().size());
        System.out.println("Mark table: " + markDao.readAll().size());
        System.out.println();

        System.out.println("Creating first mentor, student, mark");
        System.out.println();

        Mentor a_mentor = new Mentor();
        a_mentor.setName("Alexey");
        mentorDao.create(a_mentor);
        System.out.println(mentorDao.read(a_mentor.getId()));

        Student a_student = new Student();
        a_student.setName("Alexander");
        a_student.setMentor(a_mentor);
        studentDao.create(a_student);
        System.out.println(studentDao.read(a_student.getId()));

        Mark a_mark =  new Mark();
        a_mark.setValue((byte)10);
        a_mark.setMentor(a_mentor);
        a_mark.setStudent(a_student);
        markDao.create(a_mark);
        System.out.println(markDao.read(a_mark.getId()));
        System.out.println();

        System.out.println("Creating second mentor, student, mark");
        System.out.println();

        Mentor b_mentor = new Mentor();
        b_mentor.setName("Dmitriy");
        mentorDao.create(b_mentor);

        System.out.println(mentorDao.read(b_mentor.getId()));

        Student b_student = new Student();
        b_student.setName("Ivan");
        b_student.setMentor(b_mentor);
        studentDao.create(b_student);
        System.out.println(studentDao.read(b_student.getId()));

        Mark b_mark =  new Mark();
        b_mark.setValue((byte)3);
        b_mark.setMentor(b_mentor);
        b_mark.setStudent(b_student);
        markDao.create(b_mark);
        System.out.println(markDao.read(b_mark.getId()));
        System.out.println();

        System.out.println("Creating third mentor, student, mark");
        System.out.println();

        Mentor c_mentor = new Mentor();
        c_mentor.setName("David");
        mentorDao.create(c_mentor);
        System.out.println(mentorDao.read(c_mentor.getId()));

        Student c_student = new Student();
        c_student.setName("Konstantin");
        c_student.setMentor(c_mentor);
        studentDao.create(c_student);
        System.out.println(studentDao.read(c_student.getId()));

        Mark c_mark =  new Mark();
        c_mark.setValue((byte)7);
        c_mark.setMentor(c_mentor);
        c_mark.setStudent(c_student);
        markDao.create(c_mark);
        System.out.println(markDao.read(c_mark.getId()));
        System.out.println();

        System.out.println("Reading number of rows in each table");
        System.out.println();
        System.out.println("Mentors table: " + mentorDao.readAll().size());
        System.out.println("Students table: " + studentDao.readAll().size());
        System.out.println("Mark table: " + markDao.readAll().size());
        System.out.println();

        System.out.println("Testing complex update of two tables");
        System.out.println();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        a_student.setMentor(c_mentor);
        b_mark.setStudent(a_student);
        b_mark.setMentor(c_mentor);

        entityManager.flush();

        transaction.commit();

        System.out.println(studentDao.read(a_student.getId()));
        System.out.println(markDao.read(b_mark.getId()));

        entityManager.close();
    }
}
