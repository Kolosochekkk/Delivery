package com.courcework.delivery.service;

import com.courcework.delivery.exception.PromocodeNotFoundException;
import com.courcework.delivery.model.Promocode;
import com.courcework.delivery.repository.PromocodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PromocodeService {

    @Autowired
    private PromocodeRepository promocodeRepository;

    public List<Promocode> getAllPromocodes() {
        List<Promocode> promocodes = promocodeRepository.findAll();
        return promocodes;
    }

    public Promocode addPromocode(Promocode promocode) {
        return promocodeRepository.save(promocode);
    }

    public Promocode getPromocodeById(Long id) throws PromocodeNotFoundException {
        return promocodeRepository.findById(id)
                .orElseThrow(() -> new PromocodeNotFoundException(id));
    }

    public String deletePromocode(Long id) throws PromocodeNotFoundException {
        if (!promocodeRepository.existsById(id)) {
            throw new PromocodeNotFoundException(id);
        }
        promocodeRepository.deleteById(id);
        return "Промокод с id " + id + " был успешно удален";
    }

    public Promocode update(Promocode newPromocode, Long id) throws PromocodeNotFoundException {
        return promocodeRepository.findById(id)
                .map(promocode -> {
                    promocode.setCode(newPromocode.getCode());
                    promocode.setDiscount(newPromocode.getDiscount());

                    return promocodeRepository.save(promocode);

                }).orElseThrow(() -> new PromocodeNotFoundException(id));
    }
}
