package com.vegesoft.efficientspending.cqrs

import org.springframework.context.event.EventListener

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@EventListener
annotation class CommandHandler