package  com.vegesoft.efficientspending.authorization.application.controller.mapper

import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserRequest
import com.vegesoft.efficientspending.authorization.application.command.CreateAppUserCommand
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserCommandMapper {
    fun createCommand(id: UUID, request: CreateAppUserRequest): CreateAppUserCommand {
        return CreateAppUserCommand(id = id, data = request)
    }
}