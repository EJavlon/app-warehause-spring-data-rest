package com.company.appwarehausespringdatarest.repasitory;

import com.company.appwarehausespringdatarest.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);

    boolean existsByParentCategory(Category parentCategory);
}
