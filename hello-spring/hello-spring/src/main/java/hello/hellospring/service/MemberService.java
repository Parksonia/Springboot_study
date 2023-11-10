package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// @Service : 서비스라고 등록
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;  // 직접 Service에서 새롭게 생성하는게 아닌 외부에서 가져오도록 생성자로 만들어줌
    }

    /*회원 가입*/
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 있는 중복 회원 x 로직 처리
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {     // member가 이미  존재한다면
                throw new IllegalStateException("이미 존재하는 회원입니다.");
        } );
        //Optional 선언 하지 않고 사용 할  수 있음 반환값이 Optional이기 때문에
        /*Optional<Member> result = memberRepository.findByName(member.getId());
         * result.ifPresen( m -> {
         * throw new IllegasStateException("이미 존재하는 회원입니다.")); */

    }
    /*전체 회원 조회*/
    public List<Member> findMember() {
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
