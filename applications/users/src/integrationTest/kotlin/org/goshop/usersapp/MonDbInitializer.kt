package org.goshop.usersapp

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName

class MonDbInitializer : ApplicationContextInitializer<ConfigurableApplicationContext?> {
  companion object {
    private lateinit var mongoDBContainer: MongoDBContainer
  }

  init {
    mongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
    mongoDBContainer.start()
  }

  override fun initialize(applicationContext: ConfigurableApplicationContext) {
    val values = TestPropertyValues.of(
      "spring.data.mongodb.host=" + mongoDBContainer.containerIpAddress,
      "spring.data.mongodb.port=" + mongoDBContainer.firstMappedPort
    )
    values.applyTo(applicationContext)
  }
}
