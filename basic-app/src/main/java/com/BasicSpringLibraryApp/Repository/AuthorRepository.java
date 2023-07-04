package com.BasicSpringLibraryApp.Repository;

import com.BasicSpringLibraryApp.model.Author;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AuthorRepository extends JpaRepository <Author, Long> {
}