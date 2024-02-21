package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {

    MemberRepository repository = new MemoryMemberRepository();

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");     //command+shift+enter 바로 줄 바꿈
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        Assertions.assertEquals(result,member);
    }

}
