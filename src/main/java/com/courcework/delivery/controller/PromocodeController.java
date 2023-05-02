package com.courcework.delivery.controller;

import com.courcework.delivery.exception.PromocodeNotFoundException;
import com.courcework.delivery.model.Promocode;
import com.courcework.delivery.service.PromocodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class PromocodeController {

    @Autowired
    private PromocodeService promocodeService;

    @GetMapping("/promocodes")
    public List<Promocode> getAllPromocodes() {
        return promocodeService.getAllPromocodes();
    }

    @PostMapping("/promocode")
    public Promocode addPromocode(@RequestBody Promocode promocode) {
        return promocodeService.addPromocode(promocode);
    }

    @GetMapping("/promocode/{id}")
    public Promocode getPromocodeById(@PathVariable Long id) throws PromocodeNotFoundException {
        return promocodeService.getPromocodeById(id);
    }

    @PutMapping("/promocode/{id}")
    public Promocode updatePromocode(@PathVariable Long id, @RequestBody Promocode newPromocode) throws PromocodeNotFoundException {
        return promocodeService.update(newPromocode, id);
    }

    @DeleteMapping("/promocode/{id}")
    public String deletePromocode(@PathVariable Long id) throws PromocodeNotFoundException {
        return promocodeService.deletePromocode(id);
    }
}

