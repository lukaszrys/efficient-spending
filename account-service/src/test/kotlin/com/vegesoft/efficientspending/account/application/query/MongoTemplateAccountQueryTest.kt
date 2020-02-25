package com.vegesoft.efficientspending.account.application.query

import com.vegesoft.efficientspending.account.domain.ACCOUNT_COLLECTION_NAME
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class MongoTemplateAccountQueryTest {

    @MockK
    private lateinit var mongoTemplate: MongoTemplate
    @InjectMockKs
    private lateinit var tested: MongoTemplateAccountQuery

    @Test
    @DisplayName("Should create query and return view")
    fun shouldCreateQueryAndReturnView() {
        val email = "email"
        val firstName = "firstName"
        val lastName = "lastName"
        val query = Query(Criteria.where("email").`is`(email))
        val view = CurrentAccountView("firstName", "lastName", email)
        every { mongoTemplate.findOne(query, CurrentAccountView::class.java, ACCOUNT_COLLECTION_NAME) } returns view

        val result = tested.findCurrentAccount(email).get()

        assertEquals(email, result.email)
        assertEquals(firstName, result.firstName)
        assertEquals(lastName, result.lastName)
    }
}