package io.confluent.testcontainers;

import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.wait.strategy.Wait;

public class SchemaRegistryContainerTest {

  private static KafkaContainer kafka = new KafkaContainer("5.2.1");

  @BeforeClass
  public static void setUp() {
    kafka.start();
  }

  @Test
  public void shouldStartWithKafka() {
    try (SchemaRegistryContainer schemaRegistryContainer = new SchemaRegistryContainer("5.2.1")) {
      schemaRegistryContainer.withKafka(kafka)
          .waitingFor(Wait.forHttp("/subjects")
                          .forStatusCode(200))
          .start();
    }
  }
}