package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*회원가입*/
    public Long join(Member member){
        memberRepository.save(member);


        /* 여기에서 작성한 메서드를 밖으로 빼고싶을 때
        ctrl+t -> 리패키징 메서드들 나옴
        Extract method 단축키 command+option+m  */
        validateDuplicateMember(member);

        return member.getId();

    }

    /*중복 회원 가입 불가 - 조건: 이름*/
    /*null이 있을 것 같은 경우 Optional을 씀 - ifPresent 외 다른 것들도 쓸 수 있음*/
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())  //command+option+v: Optional
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMember(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }



}
