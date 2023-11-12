package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    //스프링 데이터JPA 멤버 리포지토리 사용하기
    @Autowired
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {  //injection 받기
        this.memberRepository = memberRepository;
    }


    /*jpa 사용
   private EntityManager em;


   private DataSource dataSource;
   public SpringConfig(EntityManager em,DataSource dataSource) {
       this.dataSource =dataSource;
       this.em = em;
   }

     @Autowired
         public SpringConfig(DataSource dataSource) {
             this.dataSource = dataSource;
         }*/
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

  /*
    @Bean
    public MemberRepository memberRepository () {

        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em) ; //EntityManager 필요함

    }

   */
}
