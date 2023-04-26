package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		// 정석 코드
		try {

			// 비영속
			Member member = new Member();
			member.setName("helloJPA");
			member.setId(101L);

			// 영속
			System.out.println("== BEFORE ==");
			em.persist(member);

			// 준영속
			em.detach(member);
			System.out.println("== AFTER ==");

			tx.commit();
			// commit을 하는 순간 변경 감지가 일어나서 update문이 날라간다
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
