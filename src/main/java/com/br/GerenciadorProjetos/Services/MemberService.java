package com.br.GerenciadorProjetos.Services;

import com.br.GerenciadorProjetos.Dtos.MemberRequestDto;
import com.br.GerenciadorProjetos.Dtos.MemberResponseDto;
import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Entity.Project;
import com.br.GerenciadorProjetos.Enums.MemberRole;
import com.br.GerenciadorProjetos.Exceptions.WrongRoleException;
import com.br.GerenciadorProjetos.Mappers.memberMapper;
import com.br.GerenciadorProjetos.Repository.MemberRepository;
import com.br.GerenciadorProjetos.Repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.MissingResourceException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final memberMapper memberMapper;
    private final ProjectRepository projectRepository;

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
        Member memberEntity = memberMapper.toMemberEntity(requestDto);
                connectProject(requestDto,memberEntity);
        memberRepository.save(memberEntity);
        return memberMapper.toMemberResponseDto(memberEntity);
    }

    private void verifyRole(String role) throws Exception {
        Boolean isValid = Arrays.stream(MemberRole.values())
                .anyMatch(r -> r.name().equalsIgnoreCase(role));
        if (!isValid) throw new WrongRoleException("Atribuição informada não existe.");
    }

    private void connectProject(MemberRequestDto requestDto, Member memberEntity) {
        if(requestDto.project() == null)
            return;
        Project projectEntity = projectRepository.findById(requestDto.project())
                .orElseThrow(() -> new EntityNotFoundException("Projeto informado não foi encontrado."));
        memberEntity.getProjects().add(projectEntity);
    }
}
