package essential.typeclasses

object EqualImplicits {
  trait Equal[A] {
    def equal(x: A, y:A): Boolean
  }

  implicit class EqualOps[A](a: A) {
    def ===(b: A)(implicit equal: Equal[A]) = equal.equal(a, b)
  }

  implicit object CaseInsensitiveString extends Equal[String] {
    override def equal(x: String, y: String): Boolean = x.toLowerCase==y.toLowerCase()
  }

  //This is context bounds, its useful when you want to say I want an A that has a typeclass instance
  def printEquals[T: Equal](x: T, y: T): Unit = {
    println(x===y)
  }

  // Equivalent
  def printEquals2[A](x: A, y: A)(implicit equal: Equal[A]): Unit ={
    println(x===y)
  }
}

object Ex2 extends App{
  import EqualImplicits._ //this will bring CaseInsensitiveString as the default instance of Equal[String]

  println("abcd".===("ABCD"))
  printEquals("abcd", "ABCD")
}