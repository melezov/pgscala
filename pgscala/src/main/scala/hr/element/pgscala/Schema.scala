package hr.element

package pgscala{

//  ----------------------------------------------------------------

  object Ident {
    val quote = util.PGIdent.quote _
  }

  trait Ident {
    protected val path: Seq[Entity]
    override lazy val toString = path map(e=>Ident.quote(e.entity.name)) mkString(".")
  }

//  ----------------------------------------------------------------

  case class Entity(entity: Symbol) extends Ident {
//    val toCC = entity.name.split('_').map(_.capitalize).mkString
    protected val path = this :: Nil
  }

//  ----------------------------------------------------------------

  case class Schema(schema: Entity) extends Ident {
    protected val path = schema :: Nil
  }

  case class Table(schema: Entity, table: Entity) extends Ident {
    protected val path = schema :: table :: Nil
  }

//  ----------------------------------------------------------------

  case class Column(column: Entity, num: Int = 1) extends Ident {
    require(num>0, "Column number must be a positive integer, got %d!".format(num))
    protected val path = column :: Nil
  }
}

package object pgscala{
  implicit def wrapEntity(entity: Symbol): Entity = new Entity(entity)
  implicit def wrapColumn(column: Symbol): Column = Column(wrapEntity(column))

  implicit def wrapReturningColumn(column: Symbol): Some[Column] =
    Some(wrapColumn(column))

  implicit def wrapNumberedColumn(numberedColumn: Pair[Symbol, Int]): Column =
    Column(new Entity(numberedColumn._1), numberedColumn._2)

  implicit def wrapColumnKeyPair(columnKeyPair: Pair[Symbol, Any]): Pair[Column,Any] =
    (Column(wrapEntity(columnKeyPair._1)),columnKeyPair._2)
}
