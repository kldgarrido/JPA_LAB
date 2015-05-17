package devlab.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import devlab.model.Person;

public class Main {
	private static final String PERSISTENCE_UNIT_NAME = "JPA_Test";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

		// Find All Person
		findAllPerson();

		// create new Person
		createPerson();


		// Find Person for id
		findPerson();

		// Modify Person
		modifyPerson();

		// Delete Person
		deletePerson();

	}

	private static void createPerson() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Person person = new Person("Kaled","Garrido");
		em.persist(person);
		em.getTransaction().commit();
		em.close();

	}

	private static void findAllPerson() {
		EntityManager em = factory.createEntityManager();
		Query q = em.createNamedQuery("person.findAll");
		List<Person> persons = q.getResultList();
		for (Person person : persons) {
			System.out.println(person);
		}

	}

	private static void findPerson(){
		EntityManager em = factory.createEntityManager();
		Person person = (Person) em.find(Person.class, 1);
		System.out.println(person);

	}

	private static void modifyPerson(){
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();
		Person person = em.find(Person.class, 1);
		person.setFirstName("Delak");
		em.persist(person);
		em.getTransaction().commit();
		em.close();

	}

	private static void deletePerson(){
		EntityManager em = factory.createEntityManager();
		Person person4 = em.find(Person.class, 4);
		if(person4 != null){
			em.getTransaction().begin();
			em.remove(person4);
			em.getTransaction().commit();
			em.close();
		}
	}

} 