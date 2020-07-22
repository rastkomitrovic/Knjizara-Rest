package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.BookImage
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookImageRepository : CrudRepository<BookImage, Long> {
    fun findBookImagesByBookBookId(bookId: Long): List<BookImage>
    fun deleteBookImageByImageId(imageId: Long)
}