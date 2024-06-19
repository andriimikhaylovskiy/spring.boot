package mate.academy.spring.boot.service;

import java.util.List;
import mate.academy.spring.boot.dto.category.CategoryDto;
import mate.academy.spring.boot.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto requestDto);

    CategoryDto getCategoryById(Long id);

    List<CategoryDto> getAll(Pageable pageable);

    void deleteById(Long id);

    CategoryDto updateCategory(Long id, CreateCategoryRequestDto requestDto);
}
