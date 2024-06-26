package mate.academy.spring.boot.repository.shoppingcart;

import mate.academy.spring.boot.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
}
