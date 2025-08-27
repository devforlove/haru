package com.senok.apicore.integration

import com.querydsl.core.types.EntityPath
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class IntegrationUtilService {
    @Autowired
    protected lateinit var entityManagerFactory: EntityManagerFactory
    protected val entityManager by lazy { entityManagerFactory.createEntityManager() }
    protected val queryFactory: JPAQueryFactory by lazy { JPAQueryFactory(entityManager) }

    fun <T> deleteAll(path: EntityPath<T>) {
        entityManager.transaction.let { transaction ->
            transaction.begin()
            queryFactory.delete(path).execute()
            transaction.commit()
        }
    }

    fun getQuery(): JPAQueryFactory {
        return queryFactory
    }
}