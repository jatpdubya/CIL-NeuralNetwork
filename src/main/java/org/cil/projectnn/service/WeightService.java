package org.cil.projectnn.service;

import org.cil.projectnn.dao.WeightDAO;
import org.cil.projectnn.model.Weight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WeightService {

    private final WeightDAO weightDao;

    @Autowired
    public WeightService(@Qualifier("dummyDB")WeightDAO weightDao) {
        this.weightDao = weightDao;
    }

    public int addWeight(Weight weight) {
        return weightDao.insertWeight(weight);
    }

    public List<Weight> getAllWeights() {
        return weightDao.selectAll();
    }

    public Optional<Weight> getWeight(UUID id) {
        return weightDao.selectWeight(id);
    }

    public int deleteWeight(UUID id) {
        return weightDao.deleteWeight(id);
    }

    public int updateWeight(UUID id, Weight newWeight) {
        return weightDao.updateWeight(id, newWeight);
    }



}
