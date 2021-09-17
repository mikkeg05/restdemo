package facades;

import dtos.EmployeeDTO;
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

    public void createEmployee(EmployeeDTO emp){
       EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(new Employee(emp.getName(), emp.getAddress(), emp.getSalary()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public EmployeeDTO getbyID(int id){
        EntityManager em = getEntityManager();
       try{
           em.getTransaction().begin();
           TypedQuery<Employee> findfolk = em.createQuery("select e from Employee e where e.id = :id", Employee.class);
           findfolk.setParameter("id", id);
           Employee emp = findfolk.getSingleResult();
           em.getTransaction().commit();
           return new EmployeeDTO(emp);
       }
       finally {
           em.close();
       }
    }
    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getbyName(String name){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Employee> q = em.createQuery("Select e from Employee e Where e.name = :name", Employee.class);
            q.setParameter("name", name);
            List<Employee> pølle = q.getResultList();
            return (List<EmployeeDTO>)(List<?>)pølle;
        } finally{
            em.close();
        }
    }
    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getAllEmployees(){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Employee> q = em.createQuery("Select e from Employee e", Employee.class);
            List<Employee> pølle2 = q.getResultList();

            return (List<EmployeeDTO>)(List<?>)pølle2;
        } finally {
            em.close();
        }
    }
    @SuppressWarnings("unchecked")
    public List<EmployeeDTO> getEmployeesWithHighestSalary(){
        EntityManager em = getEntityManager();
        try{
            TypedQuery<Employee> doxxing = em.createQuery("Select e from Employee e Where e.Salary = (SELECT MAX(e.Salary) from Employee e)", Employee.class);
           List<Employee> lortlortlortlort = doxxing.getResultList();
           return (List<EmployeeDTO>)(List<?>)lortlortlortlort;
        } finally {
            em.close();
        }
    }




    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade facade = EmployeeFacade.getEmployeeFacade(emf);
        facade.createEmployee(new EmployeeDTO("Karlos", "Jylland", 999));
        facade.createEmployee(new EmployeeDTO("Karl-emil", "Hjemløs", 0));

        System.out.println(facade.getbyID(1));

        System.out.println(facade.getAllEmployees());

        System.out.println(facade.getEmployeesWithHighestSalary());

        System.out.println(facade.getbyName("Karl-emil"));



    }


}

