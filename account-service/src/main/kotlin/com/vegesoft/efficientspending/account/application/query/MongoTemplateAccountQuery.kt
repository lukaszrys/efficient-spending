package com.vegesoft.efficientspending.account.application.query

import com.vegesoft.efficientspending.account.domain.ACCOUNT_COLLECTION_NAME
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component
import java.util.*

@Component
class MongoTemplateAccountQuery(
        private val mongoTemplate: MongoTemplate
) : AccountQuery {
    override fun findCurrentAccount(email: String): Optional<CurrentAccountView> {
        val query = Query(Criteria.where("email").`is`(email))

        return Optional.ofNullable(mongoTemplate.findOne(query, CurrentAccountView::class.java, ACCOUNT_COLLECTION_NAME))
    }

}