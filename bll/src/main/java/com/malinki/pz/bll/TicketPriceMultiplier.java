package com.malinki.pz.bll;

public enum TicketPriceMultiplier {
    BURZUAZJA(3);

    private int value;

    TicketPriceMultiplier(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
