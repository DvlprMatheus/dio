package com.dvlprmatheus.board.dto;

import com.dvlprmatheus.board.persistence.entity.BoardColumnKindEnum;

public record BoardColumnInfoDTO(Long id, int order, BoardColumnKindEnum kind) {
}