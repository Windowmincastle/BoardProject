package com.beyond.board.author.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data // DTO 에서는 Data로 깔아둬도 별 상관없을 것 같다. 그러나 엔티티에서는 Setter 사용을 지향하지 않는다.
public class AuthorListResDto {

    private Long id;
    private String name;
    private String email;

}
