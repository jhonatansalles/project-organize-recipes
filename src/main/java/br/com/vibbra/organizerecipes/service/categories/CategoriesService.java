package br.com.vibbra.organizerecipes.service.categories;

import br.com.vibbra.organizerecipes.base.BaseService;
import br.com.vibbra.organizerecipes.exception.NotFoundException;
import br.com.vibbra.organizerecipes.exception.ValidationException;
import br.com.vibbra.organizerecipes.model.entity.categories.Categories;
import br.com.vibbra.organizerecipes.model.response.CategoriesListResponse;
import br.com.vibbra.organizerecipes.model.response.CategoryResponse;
import br.com.vibbra.organizerecipes.repository.categories.CategoriesRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class CategoriesService extends BaseService {

    @Autowired
    CategoriesRepository categoriesRepository;

    public CategoriesListResponse listCategories(String name) {
        List<Categories> categoriesList = categoriesRepository.findAllByName(name);
        return CategoriesListResponse.builder()
                .count(categoriesList.size())
                .categories(categoriesList)
                .build();
    }

    public CategoryResponse getCategory(Long id) {
        Optional<Categories> optionalCategory = categoriesRepository.findById(id);
        return CategoryResponse.builder()
                .category(optionalCategory.orElseThrow(() -> new NotFoundException("Category not found with -> ID: " + id)))
                .build();
    }

    public Categories createCategory(Categories category) {

        this.validateRequiredFields(this.returnMapRequiredFields(category));

        if (BooleanUtils.isTrue(categoriesRepository.existsByName(category.getName()))) {
            throw new ValidationException("There is already a category with -> NAME: " + category.getName());
        }

        category.setUserLastchange("API_CREATE_CATEGORY");

        Categories savedCategory = categoriesRepository.save(category);

        return Categories.builder()
                .id(savedCategory.getId())
                .build();
    }

    public void updateCategory(Long id, Categories category) {

        this.validateRequiredFields(this.returnMapRequiredFields(category));

        Categories categoryBD = categoriesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with -> ID: " + id));

        categoryBD.setId(id);
        categoryBD.setVersion(categoryBD.getVersion());
        categoryBD.setUserLastchange("API_UPDATE_CATEGORY");

        categoriesRepository.save(category);
    }

    private Map<String, Optional<?>> returnMapRequiredFields(Categories object) {
        return Map.of(
                "Name", Optional.ofNullable(object).map(Categories::getName),
                "Description", Optional.ofNullable(object).map(Categories::getDescription)
        );
    }
}
