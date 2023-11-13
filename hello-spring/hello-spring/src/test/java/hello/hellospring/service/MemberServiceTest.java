package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService ;
    MemoryMemberRepository memberRepository ;

    @BeforeEach  //테스트마다 테스트 시작 전  실행됨
    public void bEeforeEach() {

        memberRepository = new MemoryMemberRepository();
        memberService =new MemberService(memberRepository);

    }

    @AfterEach  //Test 메서드가 끝날때마다 실행되도록 하는 콜백메서드
    public void afterEach() {
        memberRepository.clearStore(); //데이터 싹 다 지워줌
    }
    @Test
    void join() {
        //given ~가 주어진 것이
        Member member = new Member();
        member.setName("hello");

        //when ~ 이런 상황일 때
        Long saveId = memberService.join(member);
        //then ~결과가 나와야 함 을 테스트
        Member findMember = memberService.findOne(saveId).get(); //.get() =>Optional return 꺼내기
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
//       /*방법2. 람다식 */
        IllegalStateException e = assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //()->memberService.join(member2) 메서드 실행하면 IllegalStateException 예외가 발생 해야 함

        /* 방법1.
        try{
        memberService.join(member2);
        fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        //then
    }

    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}