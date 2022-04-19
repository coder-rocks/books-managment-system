package com.jpmorgan.book.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpmorgan.book.domain.entity.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long>, IBookCustomRepository{
}
