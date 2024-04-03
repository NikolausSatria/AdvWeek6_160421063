package com.example.advweek6_160421063.model

data class Brand(
    var id:String?,
    var name:String?,
    var founded:String?,
    var products:List<String>?,
    var images:String?,
    var founder:BrandFounder?
)

data class BrandFounder(
    var name:String?,
    var birth_year:String?
)