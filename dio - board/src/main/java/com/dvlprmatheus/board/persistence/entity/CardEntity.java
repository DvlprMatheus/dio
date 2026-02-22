package com.dvlprmatheus.board.persistence.enity;

import lombok.Data;

@Data
public class CardEntity {
    private Long id;
    private String title;
    private String description;
    private BoardColumnEntity boardColumn = new BoardColumnEntity();
}