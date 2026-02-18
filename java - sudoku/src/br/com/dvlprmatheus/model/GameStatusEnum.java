package br.com.dvlprmatheus.model;

public enum GameStatusEnum {
    NON_STARTED("not started"),
    INCOMPLETE("incomplete"),
    COMPLETE("complete");

    private final String label;

    GameStatusEnum(final String label){
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}