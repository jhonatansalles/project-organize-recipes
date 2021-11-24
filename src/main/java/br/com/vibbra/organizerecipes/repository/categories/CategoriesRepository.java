package br.com.vibbra.organizerecipes.repository.categories;

import br.com.vibbra.organizerecipes.model.entity.categories.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {

    @Transactional(readOnly = true)
    Boolean existsByName(String name);

    @Transactional(readOnly = true)
    List<Categories> findAllByName(@Param("NAME") String name);
}