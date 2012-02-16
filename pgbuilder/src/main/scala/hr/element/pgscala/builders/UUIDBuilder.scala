package hr.element.pgscala
package builders

object UUIDBuilder extends Builder {
  val clazz = "java.util.UUID"

  val pgType = "uuid"

  val to = "uuid.toString()"

  val from = "UUID.fromString(uuid)"
}
