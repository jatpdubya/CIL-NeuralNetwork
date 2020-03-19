package org.cil.projectnn.model;

import java.util.UUID;

public class Weight {

    private final UUID id;
    private final String val;

    public Weight(UUID id, String val) {
        this.id = id;
        this.val = val;
    }

    public UUID getId() {
        return id;
    }

    public String getVal() {
        return val;
    }
}
