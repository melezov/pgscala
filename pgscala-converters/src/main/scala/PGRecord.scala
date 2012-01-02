import scala.collection.mutable.ListBuffer

object PGRecord {
  def pack(elements: Traversable[String]): String =
    elements.mkString("(", ",", ")")

  def unpack(record: String): List[String] = {
    require('(' == record.head, "Illegal character at start of record!")
    require(')' == record.last, "Illegal character at end of record!")

    val lR = new ListBuffer[String]

    var cur = 1
    val len = record.length - 1
    val sB = new StringBuilder

    while (cur <= len) {
      record(cur) match {
        case ',' | ')' =>
          lR += (if (sB.isEmpty) null else sB.toString)
          sB.clear()

        case '"' =>
          assert(sB.isEmpty, "Quote cannot start in the middle!")
          cur += 1

          var JAVA_FLAG = true
          while (JAVA_FLAG && (cur < len)) {
            record(cur) match {
              case x if x != '"' =>
                sB += x

              case '"' =>
                cur += 1
                if (record(cur) != '"') {
                  lR += sB.toString
                  sB.clear()
                  JAVA_FLAG = false
                  cur -= 1 // MEGA HACK
                }
                else {
                  sB ++= "\"\""
                }
            }

            cur += 1
          }


        case x =>
          sB += x
      }

      cur += 1
    }

    lR.map(e =>
      if (e eq null) null else e.replace("\\\\", "\\").replace("\"\"", "\"")
    ).result
  }


/*
  public static final String[] fromString(final String array)
      throws ParseException {

    int curIndex = 1;
    final Queue<String> sQ = new ArrayDeque<String>();

    for (int index = 1; index <= lastIndex; index++) {
      final char ch = array.charAt(index);
      if ((',' == ch) || (index == lastIndex)) {
        final String elem = array.substring(curIndex, index);
        sQ.add(elem);
        curIndex = index + 1;
      }
    }

    return sQ.toArray(new String[sQ.size()]);
  }
*/
}