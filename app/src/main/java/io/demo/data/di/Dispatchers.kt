package io.demo.data.di

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: DemoDispatchers)

enum class DemoDispatchers { Default, IO, }
