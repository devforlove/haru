package com.senok.apicore.common.integration

import com.querydsl.core.types.EntityPath
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class IntegrationUtil(
    private val entityManagerFactory: EntityManagerFactory
) {
    init {
        IntegrationUtil.entityManagerFactory = entityManagerFactory
    }

    companion object {
        private lateinit var entityManagerFactory: EntityManagerFactory
        private val entityManager by lazy { entityManagerFactory.createEntityManager() }
        private val queryFactory: JPAQueryFactory by lazy { JPAQueryFactory(entityManager) }

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
}

