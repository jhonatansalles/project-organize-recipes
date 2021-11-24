package br.com.vibbra.organizerecipes.model.response;

import br.com.vibbra.organizerecipes.model.entity.categories.Categories;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class CategoriesListResponse {

    private Integer count;
    private List<Categories> categories;
}