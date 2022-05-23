package c22.ps167.backendrestfulapi.data.repository

import c22.ps167.backendrestfulapi.data.entity.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : JpaRepository<Product, String> {

    @Query("SELECT u FROM Product u WHERE u.name LIKE %:name%")
    fun findAllByProductName(@Param(value = "name") name: String, pageable: Pageable): Page<Product>

}