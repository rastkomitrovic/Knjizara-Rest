package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Author
import com.fon.knjizararest.entity.Book
import com.fon.knjizararest.repository.AuthorRepository
import com.fon.knjizararest.service.AuthorService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthorServiceImpl(@Autowired val authorRepository: AuthorRepository) : AuthorService {
    override fun findAllAuthors(): List<Author> {
        return authorRepository.findAll().sortedBy { it.firstName }.toList()
    }

    override fun findAuthorByAuthorId(authorId: Long): Optional<Author> {
        return authorRepository.findById(authorId)
    }

    override fun existsAuthorByFirstNameAndLastNameAndDateOfBirth(firstName: String, lastName: String, dateOfBirth: Date): Boolean {
        return authorRepository.existsAuthorByFirstNameAndLastNameAndDateOfBirth(firstName, lastName, dateOfBirth)
    }

    override fun deleteByAuthorId(authorId: Long) {
        authorRepository.deleteById(authorId)
    }

    override fun saveAuthor(author: Author) {
        authorRepository.save(author)
    }

}