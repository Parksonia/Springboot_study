package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence =0L; //sequence 1,2,3... 1씩 증가되는 회원번호를 위함

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //회원등록이 save될때마다 sequence 1씩 증가됨 ==id ;
        store.put(member.getId(),member); //map에 아이디,회원 저장됨
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // store.get(id)->null이 있을 수 있기 때문에 Optional.ofNullable로 감싸 반환함
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //람다식
                .filter(member ->member.getName().equals(name)) //member에서 같은 name찾기
                .findAny(); //찾으면 반환 없으면 Optional 때문에 null반환됨

    }
    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values()); //Member 반환
    }
    public void clearStore(){

        store.clear(); // .clear() 데이터를 싹 다 지워줌
    }

}
