package org.cil.projectnn.api;


import org.cil.projectnn.model.Weight;
import org.cil.projectnn.service.WeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/weights")

@RestController
public class WeightController {

    private final WeightService weightService;

    @Autowired
    public WeightController(WeightService weightService) {
        this.weightService = weightService;
    }

    @PostMapping
    public void addWeight(@Valid @NonNull @RequestBody Weight weight) {
        weightService.addWeight(weight);
    }

    @GetMapping
    public List<Weight> getAllWeights() {
        return weightService.getAllWeights();
    }

    @GetMapping(path = "{id}")
    public Weight getWeight(@PathVariable ("id") UUID id) {
        return weightService.getWeight(id).orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteWeight(@PathVariable("id") UUID id) {
        weightService.deleteWeight(id);
    }

    @PutMapping(path = "{id}")
    public void updateWeight(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Weight newWeight) {
        weightService.updateWeight(id, newWeight);
    }

}
