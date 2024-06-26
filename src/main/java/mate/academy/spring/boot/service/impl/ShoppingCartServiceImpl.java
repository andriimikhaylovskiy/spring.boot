package mate.academy.spring.boot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.shoppingcart.ShoppingCartDto;
import mate.academy.spring.boot.mapper.ShoppingCartMapper;
import mate.academy.spring.boot.model.ShoppingCart;
import mate.academy.spring.boot.model.User;
import mate.academy.spring.boot.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.spring.boot.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shopCartRepo;
    private final ShoppingCartMapper shopCartMapper;

    @Override
    public ShoppingCart getShopCart(Long userId) {
        return shopCartRepo.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Can't find shopping cart by id:" + userId));
    }

    @Override
    @Transactional
    public ShoppingCartDto getShopCartDto(Long userId) {
        return shopCartMapper.toDto(getShopCart(userId));
    }

    @Override
    public void createShoppingCart(User user) {
        shopCartRepo.save(shopCartMapper.mapUserToShopCart(user));
    }
}
