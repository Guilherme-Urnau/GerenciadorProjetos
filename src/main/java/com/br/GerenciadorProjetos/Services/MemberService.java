package com.br.GerenciadorProjetos.Services;

import com.br.GerenciadorProjetos.Dtos.MemberRequestDto;
import com.br.GerenciadorProjetos.Dtos.MemberResponseDto;
import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Enums.MemberRole;
import com.br.GerenciadorProjetos.Mappers.memberMapper;
import com.br.GerenciadorProjetos.Repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final memberMapper memberMapper;

    public Page<MemberResponseDto> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable).map(memberMapper::toMemberResponseDto);
    }

    public MemberResponseDto getMemberById(Long memberId) {
        Member entity = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));

        return memberMapper.toMemberResponseDto(entity);
    }

    public MemberResponseDto createMember(MemberRequestDto requestDto) throws Exception {
        verifyRole(requestDto.role());

        Member entity = memberRepository.save(memberMapper.toMemberEntity(requestDto));
        return memberMapper.toMemberResponseDto(entity);
    }

    private void verifyRole(String role) throws Exception {
        Boolean isValid = Arrays.stream(MemberRole.values()).anyMatch(r -> r.name().equalsIgnoreCase(role));
        if (!isValid) throw new Exception("Role incorreto");
        //todo criar nova exceçao e colocar no controller advice
    }
}
