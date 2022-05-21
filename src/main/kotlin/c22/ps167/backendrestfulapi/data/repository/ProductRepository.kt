package c22.ps167.backendrestfulapi.data.repository

import c22.ps167.backendrestfulapi.data.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String>