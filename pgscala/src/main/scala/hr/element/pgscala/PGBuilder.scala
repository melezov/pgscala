package hr.element.pgscala

object PGBuilder{
  case class Column(
      name: String,
      pos: Int,
      format: String,
      not_null: Boolean,
      comment: Option[String]
  )
}

trait PGBuilder{db: PGScala =>

  protected val ignoredSchemas = Array("pg_catalog", "pg_toast", "information_schema")

  def getTables() =
    db.bag("""
      SELECT
        n.nspname::text AS "schema",
        c.relname::text AS "table"
      FROM pg_catalog.pg_class c
        JOIN pg_catalog.pg_user u ON u.usesysid = c.relowner
        JOIN pg_catalog.pg_namespace n ON n.oid = c.relnamespace
      WHERE c.relkind='r' AND NOT n.nspname=ANY(?)
      ORDER BY 1, 2;
""",
    ignoredSchemas) {rs=>
      val schema = Symbol(rs.getStr('schema)): Entity
      val table = Symbol(rs.getStr('table)): Entity
      Schema(schema) -> Table(schema, table)
    }

  def getColumns(table: Table) =
    db.arr("""
      SELECT
        a.attname AS name,
        a.attnum AS pos,
        pg_catalog.format_type(a.atttypid, a.atttypmod) AS format,
        a.attnotnull AS not_null,
        pg_catalog.col_description(a.attrelid, a.attnum) AS comment
      FROM pg_catalog.pg_attribute a
      WHERE a.attrelid = ANY(
        SELECT oid
        FROM pg_catalog.pg_class
       WHERE relname = ? AND relnamespace = (
          SELECT oid
          FROM pg_catalog.pg_namespace
          WHERE nspname = ?
        )
      ) AND a.attnum > 0 AND NOT a.attisdropped
      ORDER BY 2;
""",
    table.table.entity.name, table.schema.entity.name) {rs=>
      PGBuilder.Column(
        rs.getStr('name),
        rs.getInt('pos),
        rs.getStr('format),
        rs.getBool('not_null),
        rs.optStr('comment)
      )
  }

  def getDatabaseSource = {
    val sb = new StringBuilder

    for ((s, tables) <- db.getTables) {

      sb ++= "object %s {\r\n".format(s.schema.entity.name)
      val maxLen = tables.map(_.table.entity.name.length).max

      for(t <- tables) {
        val schemaName = t.schema.entity.name
        val tableName = t.table.entity.name
        sb ++= "  object %2$s extends Table('%1$s, '%2$s)\r\n".format(schemaName, tableName)
        sb ++= "  case class %s(\r\n".format(tableName)

        val fields = (for(c <- db.getColumns(t)) yield {
          val field = c.name
          val format = PG.find(c.format).map(_.clazz) orElse (c.format match {
            case "text[]"        => Some("IndexedSeq[String]")
            case "cidr" | "ip4r" => Some("String")
            case x => sys.error("Unknow data type: " + x )
          }) get

          val optionFormat = if (c.not_null){
            format
          }
          else {
            "Option[%s]".format(format)
          }

          field -> optionFormat
        })

        val maxFieldLen = fields.map(_._1.length).max

        sb ++= (fields.map{kv =>
          val field = kv._1
          val padding = " " * (maxFieldLen - kv._1.length)
          val optionFormat = kv._2
          "     %s:%s %s".format(field, padding, optionFormat)
        }).mkString(",\r\n")

        sb ++= "\r\n  )\r\n\r\n"
      }

      sb ++= "}\r\n\r\n"
    }

    sb.result
  }
}