package facades;

import dtos.RenameMeDTO;
import entities.Employee;
import entities.RenameMe;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;


public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {}


    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void createEmployee(String name, String address, int salary){
        Employee employee = new Employee(name, address, salary);
       EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Employee getbyID(int id){
        EntityManager em = getEntityManager();
        try{
            return em.find(Employee.class, id);
        } finally{
            em.close();
        }
    }
    public List<Employee> getbyName(String name){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Employee> q = em.createQuery("Select e from Employee e Where e.name = :name", Employee.class);
            q.setParameter("name", name);
            return q.getResultList();
        } finally{
            em.close();
        }
    }
    public List<Employee> getAllEmployees(){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Employee> q = em.createQuery("Select e from Employee e", Employee.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    public List<Employee> getEmployeesWithHighestSalary(){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Employee> doxxing = em.createQuery("Select e from Employee e Where e.Salary = (SELECT MAX(e.Salary) from Employee e)", Employee.class);
            return doxxing.getResultList();
        } finally {
            em.close();
        }
    }




    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        facade.createEmployee("Johannes", "Himmelgade 99", 78786754);
        facade.createEmployee("Karl-emil", "Hjemløs", 0);

        System.out.println(facade.getbyID(1));

        System.out.println(facade.getAllEmployees());

        System.out.println(facade.getEmployeesWithHighestSalary());

        System.out.println(facade.getbyName("Karl-emil"));



    }


}

