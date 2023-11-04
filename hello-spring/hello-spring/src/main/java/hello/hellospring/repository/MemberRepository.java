package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {  //Member객체를 저장 위한 저장소
    Member save (Member member);
    /*Optional = java8에 있는 기능*/
    Optional<Member> findById(Long id);  //id 찾기,Optional의 옵션으로 해당 아이디가 없으면 null반환
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
