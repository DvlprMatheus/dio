package com.dvlprmatheus.board.dto;

import com.dvlprmatheus.board.persistence.entity.BoardColumnKindEnum;

public record BoardColumnDTO(Long id,
    String name,
    BoardColumnKindEnum kind,
    int cardsAmount) {
}