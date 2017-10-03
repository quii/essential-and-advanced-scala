package advanced.printable

final case class Cat(
                      name: String,
                      age: Int,
                      color: String
                    )

object CatPrinter{
  implicit val printer = new Printable[Cat] {
    override def format(x: Cat) = s"${x.name} is a ${x.age} year-old ${x.color} cat."
  }
}

object CatApp extends App{
  import CatPrinter._
  import PrintableSyntax._

  val pepper = Cat("Pepper", 9, "christmas pudding")
  pepper.print
}