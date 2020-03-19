package org.cil.projectnn.dao;

import org.cil.projectnn.model.Weight;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WeightDAO {

    int insertWeight(UUID id, Weight weight);

    default int insertWeight(Weight weight) {
        UUID id = UUID.randomUUID();
        return insertWeight(id, weight);
    }

    List<Weight> selectAll();

    Optional<Weight> selectWeight(UUID id);

    int deleteWeight(UUID id);

    int updateWeight(UUID id, Weight weight);
}
