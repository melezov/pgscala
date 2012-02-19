.class public hr.element.pgscala.converters.PGNullableConverter
.super java/lang/Object

.method public static fromPGString(Ljava/lang/String;){ jasminClass }
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullable{ fileName }Converter.stringTo{ fileName }(Ljava/lang/String;){ jasminClass }
  areturn
.end method

.method public static toPGString({ jasminClass })Ljava/lang/String;
  .limit locals 1
  .limit stack 2

  aload_0
  invokestatic hr/element/pgscala/converters/PGNullable{ fileName }Converter.{ javaTypeLower }ToString({ jasminClass })Ljava/lang/String;
  areturn
.end method
