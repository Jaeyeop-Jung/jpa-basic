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
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setName("member1");
			member.setTeam(team);
			em.persist(member);

			em.flush();
			em.clear();

			Team team1 = em.find(Team.class, 1L);
			for (Member team1Member : team1.getMembers()) {
				System.out.println("team1Member = " + team1Member);
			}

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
