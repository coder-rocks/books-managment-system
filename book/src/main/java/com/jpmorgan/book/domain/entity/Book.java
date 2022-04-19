package com.jpmorgan.book.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK")
public class Book{
		
		@Id
		@Column(name = "ID")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Long id;
		
		@Column(name = "ISBN")
		private String isbn;
		
		@Column(name = "TITLE")
		private String title;
		
		@Column(name = "AUTHOR")
		private String author;
		
		@Column(name = "TAGS")
		private String tags;
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}


		public String getIsbn() {
			return isbn;
		}

		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getTags() {
			return tags;
		}

		public void setTags(String tags) {
			this.tags = tags;
		}
}
