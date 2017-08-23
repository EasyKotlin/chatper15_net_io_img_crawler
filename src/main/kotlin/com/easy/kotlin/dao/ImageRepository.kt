package com.easy.kotlin.chapter11_kotlin_springboot.dao

import com.easy.kotlin.chapter11_kotlin_springboot.entity.Image
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

/** Created by jack on 2017/7/17.

@Query注解里面的value和nativeQuery=true,意思是使用原生的sql查询语句.
sql模糊查询like语法,我们在写sql的时候是这样写的

like '%?%'

但是在@Query的value字符串中, 这样写

like %?1%

另外，要注意的是： 对于执行update和delete语句需要添加@Modifying注解
 */

interface ImageRepository : PagingAndSortingRepository<Image, Long> {
    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.category like %?1% order by a.gmtModified desc")
    fun findByCategory(category: String): MutableList<Image>

    @Query("select count(*) from #{#entityName} a where a.url = ?1")
    fun countByUrl(url: String): Int

    /**源数据列表*/
    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 order by a.id desc")
    override fun findAll(pageable: Pageable): Page<Image>

    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.category like %:searchText% order by a.id desc")
    fun search(@Param("searchText") searchText: String, pageable: Pageable): Page<Image>

    /**收藏列表*/
    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.isFavorite=1 order by a.gmtModified desc")
    fun findAllFavorite(pageable: Pageable): Page<Image>

    @Query("SELECT a from #{#entityName} a where a.isDeleted=0 and a.isFavorite=1 and a.category like %:searchText% order by a.gmtModified desc")
    fun searchFavorite(@Param("searchText") searchText: String, pageable: Pageable): Page<Image>

    @Throws(Exception::class)
    @Modifying
    @Transactional
    @Query("update #{#entityName} a set a.isFavorite=1,a.gmtModified=now() where a.id=?1")
    fun addFavorite(id: Long)

    @Throws(Exception::class)
    @Modifying
    @Transactional
    @Query("update #{#entityName} a set a.isDeleted=1 where a.id=?1")
    fun delete(id: Long)

}
