package com.example.demo.repository;

import com.example.demo.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long>,PagingAndSortingRepository<Question,Long> {
//    @Query(value = "select * from question order by rand() #pageable",
//            countQuery = "select count(*) from question",
//            nativeQuery = true)
//    Page<Question> findAllRandom(Pageable pageable);
}
