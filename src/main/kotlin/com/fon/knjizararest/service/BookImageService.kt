package com.fon.knjizararest.service

import com.fon.knjizararest.entity.BookImage
import java.util.*

interface BookImageService {
    fun findBookImageByImageId(imageId: Long): Optional<BookImage> //default method
    fun saveBookImage(bookImage: BookImage) //default method
    fun findBookImagesByBookBookId(bookId: Long): List<BookImage>
    fun deleteBookImageByImageId(imageId: Long)
}