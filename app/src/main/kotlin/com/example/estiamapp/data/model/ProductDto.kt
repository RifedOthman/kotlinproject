package com.example.estiamapp.data.model

import com.squareup.moshi.Json

data class ProductDto (
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    @Json(name = "category") val categoryName: String? = null,
    val images: List<String> = emptyList(),
    @Json(name = "thumbnail") val thumbnail: String? = null,
    val slug: String = "",
    val creationAt: String = "",
    val updatedAt: String = ""
) {
    val category: CategoryDto
        get() = CategoryDto(
            id = 0,
            name = categoryName ?: "Uncategorized",
            slug = categoryName?.lowercase()?.replace(" ", "-") ?: "uncategorized",
            image = thumbnail ?: "",
            creationAt = "",
            updatedAt = ""
        )
}