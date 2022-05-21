package c22.ps167.backendrestfulapi.data.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
@Table(name = "nutrition_facts")
data class NutritionFact(

    @Column(name = "calories")
    var calories: Int,

    @Column(name = "total_fat")
    var totalFat: Int,

    @Column(name = "saturated_fat")
    var saturatedFat: Int,

    @Column(name = "protein")
    var protein: Int,

    @Column(name = "total_carbohydrate")
    var totalCarbohydrate: Int,

    @Column(name = "sugar")
    var sugar: Int,

    @Column(name = "sodium")
    var sodium: Int

) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @OneToOne(mappedBy = "nutritionFact", optional = false, orphanRemoval = true)
    @JsonBackReference
    var product: Product? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as NutritionFact

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , calories = $calories , totalFat = $totalFat , saturatedFat = $saturatedFat , protein = $protein , totalCarbohydrate = $totalCarbohydrate , sugar = $sugar , sodium = $sodium , product = $product )"
    }
}
