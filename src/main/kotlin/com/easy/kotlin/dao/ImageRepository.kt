package com.easy.kotlin.chapter11_kotlin_springboot.dao

import com.easy.kotlin.chapter11_kotlin_springboot.entity.Image
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by jack on 2017/7/17.
 *
 *


@Query注解里面的value和nativeQuery=true,意思是使用原生的sql查询语句.
sql模糊查询like语法,我们在写sql的时候是这样写的

like '%?%'

但是在@Query的value字符串中, 这样写

like %?1%

 */

interface ImageRepository : PagingAndSortingRepository<Image, Long> {
    @Query("SELECT a from #{#entityName} a where a.category like %?1%")
    fun findByCategory(category: String): MutableList<Image>

    @Query("select count(*) from #{#entityName} a where a.url = ?1")
    fun countByUrl(url: String): Int
}
