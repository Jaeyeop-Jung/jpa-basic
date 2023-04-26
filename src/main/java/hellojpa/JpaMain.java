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

			Member member = new Member(200L, "A");
			em.persist(member);
			em.flush();

			Member findMember = em.find(Member.class, 200L);
			findMember.setName("B");

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
