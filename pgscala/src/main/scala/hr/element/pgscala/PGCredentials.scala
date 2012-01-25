package hr.element.pgscala

case class PGCredentials(
  host: String,
  port: Int,
  dbName: String,
  user: String,
  pass: String
)
