package com.br.GerenciadorProjetos.Controller;

import com.br.GerenciadorProjetos.Dtos.MemberRequestDto;
import com.br.GerenciadorProjetos.Dtos.MemberResponseDto;
import com.br.GerenciadorProjetos.Entity.Member;
import com.br.GerenciadorProjetos.Services.MemberService;
import jakarta.validation.Valid;
import jdk.jshell.Snippet;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<MemberResponseDto> getMembers(Pageable pageable, HttpMethod httpMethod){
        return memberService.getAllMembers(pageable);
    }

    @GetMapping("/{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponseDto getMemberById(@PathVariable("memberId") Long memberId){
        return memberService.getMemberById(memberId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto createMember(@Valid @RequestBody MemberRequestDto requestDto) throws Exception {
        return memberService.createMember(requestDto);
    }
}
