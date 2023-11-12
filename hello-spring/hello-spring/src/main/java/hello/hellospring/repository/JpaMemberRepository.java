package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; // jpa는  EntityManager라는걸로 모든게 동작됨(라이브러리를 통해 자동으로 EntityManager를 주입받는다)

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //.persist() :영구 저장하다. jpa가 insert ,db에 넣고 ,setId 까지 다 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
         Member member = em.find(Member.class,id);  //.find(조회할 타입,식별자) : 조회

        return Optional.ofNullable(member); // 값이 없을 수 있으니 Optional로 반환
    }
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name =:name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m",Member.class) //jpql 쿼리언어
                .getResultList(); //inline variable 단축키 :ctrl alt n;
    }
}
