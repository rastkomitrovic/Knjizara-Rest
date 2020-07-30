package com.fon.knjizararest.service.impl

import com.fon.knjizararest.entity.Reservation
import com.fon.knjizararest.repository.ReservationRepository
import com.fon.knjizararest.service.ReservationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReservationServiceImpl(@Autowired val reservationRepository: ReservationRepository) : ReservationService {
    override fun findReservationByReservationId(reservationId: Long): Optional<Reservation> {
        return reservationRepository.findById(reservationId)
    }

    override fun saveReservation(reservation: Reservation) {
        reservationRepository.save(reservation)
    }

    override fun findReservations(pageable: Pageable): Page<Reservation> {
        return reservationRepository.findAll(pageable)
    }

    override fun findReservationsByStatus(status: String, pageable: Pageable): Page<Reservation> {
        return reservationRepository.findReservationsByStatus(status, pageable)
    }

    override fun deleteReservationByReservationId(reservationId: Long) {
        reservationRepository.deleteById(reservationId)
    }

    override fun existsByEntryEntryId(entryId: Long): Boolean {
        return reservationRepository.existsByEntryEntryId(entryId)
    }

    override fun findReservationByEntryEntryId(entryId: Long): Optional<Reservation> {
        return reservationRepository.findReservationByEntryEntryId(entryId)
    }
}