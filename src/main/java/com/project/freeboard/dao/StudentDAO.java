import javax.persistence.EntityManager;

import com.project.freeboard.service.PersistenceManager;

public class StudentDAO {

	private EntityManager em;

	public StudentDAO() {
		em = PersistenceManager.get().createEntityManager();
	}

	public boolean addStudent(Students s) {
		// Check for already exists
		try {
			em.getTransaction().begin();
			em.persist(s);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean updateStudent(Student s) {
		try {
			em.getTransaction().begin();
			em.merge(s); // cascades the tool & skill relationships
			em.flush();
			em.getTransaction().commit();
			em.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean removeStudent(Student id) {
		try {
			Student student = em.find(Student.class, id);
			em.getTransaction().begin();
			em.remove(student);
			em.flush();
			em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			em.close();
			return false;
		}
	}

	public List<Student> getStudents() {
		List<Student> students = null;
		em.getTransaction().begin();
		TypedQuery<Student> q = em.createNamedQuery("Student.getAll", Student.class);
		try {
			students = q.getResultList();
		} catch (NoResultException e) {
			students = new ArrayList<Equipo>();
		}

		em.flush();
		em.getTransaction().commit();
		return offers;
	}

	public Student getStudentById(String id) {

		em.getTransaction().begin();
		Student student = em.find(Student.class, id);
		em.flush();
		em.getTransaction().commit();
		return equipo;
	}
	
}