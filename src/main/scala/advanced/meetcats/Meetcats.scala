package advanced.meetcats



object Meetcats extends App{

  // this is nice, bring in instances you want, rather than magic'd default ones
  import cats.Show
  import cats.instances.int._
  import cats.instances.string._

  val showInt = Show.apply[Int]
  val showString = Show.apply[String]

  private val res: String = showInt.show(123)

  println(res)

  println(showString.show("CJ"))
}

object InterfaceSyntax extends App{
  import cats.syntax.show._
  import cats.instances.int._

  val shownInt = 123.show
}

object RollYerOwn extends App{
  import cats.Show
  import cats.syntax.show._

  case class Cat(name: String, age: Int)

  implicit val catShow: Show[Cat] = Show.show(cat => s"${cat.name} is ${cat.age} years old")

  val pepper = Cat("Pepper", 9)

  val res = pepper.show

  println(res)
}