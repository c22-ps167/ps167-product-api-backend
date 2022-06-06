package c22.ps167.backendrestfulapi.data.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "products")
data class Product(

    @Id
    @Column(name = "id")
    val id: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "created_at")
    val createdAt: Date,

    @Column(name = "updated_at")
    var updatedAt: Date?,

    @Column(name = "url")
    var url: String

) {
    @OneToOne(cascade = [CascadeType.ALL], optional = false, orphanRemoval = true)
    @JoinColumn(name = "nutrition_fact_id", nullable = false)
    @JsonManagedReference
    var nutritionFact: NutritionFact? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Product

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , createdAt = $createdAt , updatedAt = $updatedAt , url = $url , nutritionFact = $nutritionFact )"
    }
}
