package com.br.GerenciadorProjetos.Mappers;

import com.br.GerenciadorProjetos.Dtos.MemberRequestDto;
import com.br.GerenciadorProjetos.Dtos.MemberResponseDto;
import com.br.GerenciadorProjetos.Entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface memberMapper {

    Member toMemberEntity(MemberRequestDto dto);

    MemberResponseDto toMemberResponseDto(Member member);
}
