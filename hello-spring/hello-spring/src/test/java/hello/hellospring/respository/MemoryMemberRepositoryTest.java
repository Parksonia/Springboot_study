package hello.hellospring.respository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {


    MemoryMemberRepository repository = new MemoryMemberRepository();


     @AfterEach  //Test 메서드가 끝날때마다 실행되도록 하는 콜백메서드
     public void afterEach() {
        repository.clearStore(); //데이터 싹 다 지워줌
    }

     @Test  //테스트를 진행 할 수 있음
     public void save() {
         Member member = new Member();
         member.setName("spring");
         repository.save(member); //저장,저장하면 id값 셋팅  sequence +=1

         //반환 타입이 Oprional이기 때문에 .get()으로 꺼내줌
         Member result = repository.findById(member.getId()).get();
         //jUnit의 Assertions로  result와 save된 member 와 id로 찾은 멤버 동일 확인

         /* 방법1 Assertions junit.jupiter
         Assertions.assertEquals(member,result); //(기대값,실제값),통과
         Assertions.assertEquals(member,null); // 불통
        */
         //방법2 Assertions assertj.core 위의 방식보다 더 간편하다 static 이라 import 해주면 Assertions 없이 바로 쓸 수 있음
         assertThat(member).isEqualTo(result);

     }
     @Test
    public void findByName() {
         Member member1 =new Member();
         member1.setName("spring1");
         repository.save(member1); //회원 저장

         Member member2 =new Member();
         member2.setName("spring2");
         repository.save(member2); //회원 저장

         Member result = repository.findByName("spring1").get();
         assertThat(result).isEqualTo(member1);

     }
     @Test
    public void findAll(){
         Member member1 = new Member();
         member1.setName("spring1");
         repository.save(member1); //데이터로 저장

         Member member2 = new Member();
         member2.setName("spring2");
         repository.save(member2); //데이터로 저장

         List<Member> result = repository.findAll();
         assertThat(result.size()).isEqualTo(2); // 2개의 아이디 저장됨 통과

     }

 }
