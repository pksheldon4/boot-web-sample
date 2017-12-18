package com.pksheldon4.bootwebsample.repository;

import com.pksheldon4.bootwebsample.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {


    List<Person> findByFirstNameIsLikeAndLastNameIsLike(String first, String last);
}