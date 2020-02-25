package  com.vegesoft.efficientspending.account.application.controller.mapper

import com.vegesoft.efficientspending.account.application.command.CreateAccountCommand
import com.vegesoft.efficientspending.account.application.command.CreateAccountRequest
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccountCommandMapper {
    fun createCommand(id: UUID, request: CreateAccountRequest): CreateAccountCommand {
        return CreateAccountCommand(id = id, data = request)
    }
}