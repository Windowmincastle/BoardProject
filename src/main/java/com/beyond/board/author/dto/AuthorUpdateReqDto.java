package com.beyond.board.author.dto;

import com.beyond.board.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorUpdateReqDto {

    private String name;
    private String password;

    public AuthorUpdateReqDto toEntity(Author author){
        AuthorUpdateReqDto authorUpdateReqDto = AuthorUpdateReqDto.builder()
                .name(author.getName())
                .password(author.getPassword())
                .build();
        return authorUpdateReqDto;
    }
}
