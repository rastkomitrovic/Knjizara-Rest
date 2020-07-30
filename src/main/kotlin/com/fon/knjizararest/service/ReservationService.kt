package com.fon.knjizararest.service

import com.fon.knjizararest.entity.Reservation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

interface ReservationService {
    fun findReservationByReservationId(reservationId: Long): Optional<Reservation> //default method
    fun saveReservation(reservation: Reservation) //default method
    fun findReservations(pageable: Pageable): Page<Reservation> //default method
    fun findReservationsByStatus(status: String, pageable: Pageable): Page<Reservation>
    fun deleteReservationByReservationId(reservationId: Long)
    fun existsByEntryEntryId(entryId: Long): Boolean
    fun findReservationByEntryEntryId(entryId: Long): Optional<Reservation>
}