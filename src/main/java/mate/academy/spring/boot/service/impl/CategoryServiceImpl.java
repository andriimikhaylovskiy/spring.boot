package mate.academy.spring.boot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.category.CategoryDto;
import mate.academy.spring.boot.dto.category.CreateCategoryRequestDto;
import mate.academy.spring.boot.mapper.CategoryMapper;
import mate.academy.spring.boot.model.Category;
import mate.academy.spring.boot.repository.category.CategoryRepository;
import mate.academy.spring.boot.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        return categoryMapper.toDto(categoryRepo.save(categoryMapper.toModel(requestDto)));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return categoryRepo.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Category with id '%s' wasn't found", id)));
    }

    @Override
    public List<CategoryDto> getAll(Pageable pageable) {
        return categoryRepo.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    @Transactional
    public CategoryDto updateCategory(Long id, CreateCategoryRequestDto requestDto) {
        Category oldCategory = categoryRepo.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Can't get category by id = " + id));
        return categoryMapper.toDto(
                categoryMapper.updateCategoryFromDto(oldCategory, requestDto));
    }
}
