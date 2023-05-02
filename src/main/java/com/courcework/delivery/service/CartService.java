package com.courcework.delivery.service;

import com.courcework.delivery.exception.CartNotFoundException;
import com.courcework.delivery.model.Cart;
import com.courcework.delivery.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts;
    }

    public Cart addCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public String deleteCart(Long id) throws CartNotFoundException {
        if (!cartRepository.existsById(id)) {
            throw new CartNotFoundException(id);
        }
        cartRepository.deleteById(id);
        return "Корзина с id " + id + " была успешно удалена";
    }

    public List<Cart> getCartsByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart update(Cart newCart, Long id) throws CartNotFoundException {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.setNumber(newCart.getNumber());
                    cart.setUser(newCart.getUser());
                    cart.setDish(newCart.getDish());

                    return cartRepository.save(cart);

                }).orElseThrow(() -> new CartNotFoundException(id));
    }
}


