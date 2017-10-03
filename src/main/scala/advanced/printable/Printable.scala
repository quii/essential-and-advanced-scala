package advanced.printable


trait Printable[A] {
  def format(x: A) : String
}

object Printable{
  def format[A](x: A)(implicit p: Printable[A]) = p.format(x)
  def print[A](x: A)(implicit p: Printable[A]) = println(format(x))
}

// this means if there is an implicit instance of printable for a thing we can call format or print
object PrintableSyntax{
  implicit class PrintOps[A](x: A){
    def format(implicit p: Printable[A]): String = p.format(x)
    def print(implicit p: Printable[A]) = println(p.format(x))
  }
}

object PrintableInstances{

  implicit val stringPrintable = new Printable[String] {
    override def format(x: String) = x
  }

  implicit val intPrintable = new Printable[Int] {
    override def format(x: Int) = x.toString
  }
}
