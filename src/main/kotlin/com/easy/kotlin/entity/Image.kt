package com.easy.kotlin.chapter11_kotlin_springboot.entity

import java.util.*
import javax.persistence.*

@Entity
class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = -1
    @Version
    var version: Long = 0
    var category: String = ""
    var isFavorite: Int = 0
    var url: String = ""
    var gmtCreated: Date = Date()
    var gmtModified: Date = Date()
    var isDeleted: Int = 0  //1 Yes 0 No
    var deletedDate: Date = Date()
    override fun toString(): String {
        return "Image(id=$id, version=$version, category='$category', isFavorite=$isFavorite, url='$url', gmtCreated=$gmtCreated, gmtModified=$gmtModified, isDeleted=$isDeleted, deletedDate=$deletedDate)"
    }


}

