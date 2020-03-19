package org.cil.projectnn.dao;

import org.cil.projectnn.model.Weight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("dummyDB")
public class DummyAccessService implements WeightDAO {

    private static List<Weight> DB = new ArrayList<>();

    @Override
    public int insertWeight(UUID id, Weight weight) {
        DB.add(new Weight(id, weight.getVal()));
        return 1;
    }

    @Override
    public List<Weight> selectAll() {
        return DB;
    }

    @Override
    public Optional<Weight> selectWeight(UUID id) {
        return DB.stream()
                .filter(weight -> weight.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteWeight(UUID id) {
        Optional<Weight> weightOptional = selectWeight(id);
        if (weightOptional.isPresent()) {
            DB.remove(weightOptional.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updateWeight(UUID id, Weight newWeight) {
        return selectWeight(id)
                .map(oldWeight -> {
                    int indexOfWeight = DB.indexOf(oldWeight);
                    if (indexOfWeight >= 0) {
                        DB.set(indexOfWeight, new Weight(id, newWeight.getVal()));
                        return 1;
                    }
                    return 0;
                }).orElse(0);
    }

}
