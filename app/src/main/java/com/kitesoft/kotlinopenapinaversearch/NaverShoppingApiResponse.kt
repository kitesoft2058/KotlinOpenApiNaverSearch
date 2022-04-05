package com.kitesoft.kotlinopenapinaversearch

data class NaverShoppingApiResponse(var total:Int, var display:String, var items:MutableList<ShoppingItem>)

data class ShoppingItem(
    var title:String,
    var link:String,
    var image:String,

    //integer의 값으로 빈값이 와서 NumberFormatting Exception이 발생할 수 있기에 그냥 String으로 받기..
//    var lprice:Int,
    var lprice:String,
//    var hprice:Int,
    var hprice:String,
    var mallName:String,
//    var productId:Int,
//    var productType:Int,
    var productId:String,
    var productType:String,
    var maker:String,
    var brand:String,
    var category1:String,
    var category2:String,
    var category3:String,
    var category4:String
)
