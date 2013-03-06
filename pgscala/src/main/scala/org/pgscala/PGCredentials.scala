package org.pgscala

case class PGCredentials(
  host: String
, port: Int = 5432
, dbName: String
, user: String
, password: String
, sslMode: String = "require"
)
