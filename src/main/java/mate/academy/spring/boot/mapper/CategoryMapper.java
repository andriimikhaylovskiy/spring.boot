package mate.academy.spring.boot.mapper;

import mate.academy.spring.boot.config.MapperConfig;
import mate.academy.spring.boot.dto.category.CategoryDto;
import mate.academy.spring.boot.dto.category.CreateCategoryRequestDto;
import mate.academy.spring.boot.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto requestDto);

    Category updateCategoryFromDto(@MappingTarget Category category,
                                   CreateCategoryRequestDto requestDto);
}
