package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.BookImage
import com.fon.knjizararest.repository.BookImageRepository
import com.fon.knjizararest.service.BookImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class BookImageServiceImpl(@Autowired val bookImageRepository: BookImageRepository) : BookImageService {
    override fun findBookImageByImageId(imageId: Long): Optional<BookImage> {
        return bookImageRepository.findById(imageId)
    }

    override fun saveBookImage(bookImage: BookImage) {
        bookImageRepository.save(bookImage)
    }

    override fun findBookImagesByBookBookId(bookId: Long): List<BookImage> {
        return bookImageRepository.findBookImagesByBookBookId(bookId)
    }

    override fun deleteBookImageByImageId(imageId: Long) {
        bookImageRepository.deleteBookImageByImageId(imageId)
    }
}