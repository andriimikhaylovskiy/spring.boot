package mate.academy.spring.boot.mapper;

import mate.academy.spring.boot.config.MapperConfig;
import mate.academy.spring.boot.dto.shoppingcart.ShoppingCartDto;
import mate.academy.spring.boot.model.ShoppingCart;
import mate.academy.spring.boot.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(source = "user", target = "user")
    ShoppingCart mapUserToShopCart(User user);

    @Mapping(source = "user.id", target = "userId")
    ShoppingCartDto toDto(ShoppingCart shopCart);
}
