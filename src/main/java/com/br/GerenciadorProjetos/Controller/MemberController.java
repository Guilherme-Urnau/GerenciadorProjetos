package com.br.GerenciadorProjetos.Controller;

import com.br.GerenciadorProjetos.Dtos.ProjectRequestDto;
import com.br.GerenciadorProjetos.Entity.ProjectMember;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    @GetMapping
    public Page<ProjectMember> getMembers(Pageable pageable){
        return memberService.getAllMembers();
    }

    @GetMapping("/{memberId}")
    public ProjectMember getMemberById(@PathVariable("memberId") Long memberId){
        return memberService.getMemberById(memberId);
    }

    @PostMapping
    public ProjectMember createMember(@Valid @RequestBody memberRequestDto requestDto){
        return memberService.createMember(requestDto);
    }
}
