package advanced.meetcats


object Equality extends App{
  import cats.Eq
  import cats.instances.int._

  val eqInt = Eq[Int]

  println(eqInt.eqv(123, 123))
  println(eqInt.eqv(123, 1234))
}

object EqualityWithSyntax extends App{
  import cats.syntax.eq._
  import cats.instances.int._

  println(123 === 123)
  println(123 =!= 123)
}

object EqualityWithOptionAndInt extends App{
  import cats.syntax.eq._
  import cats.instances.int._
  import cats.instances.option._

  // we have to add the Option aliases because Eq type class is invariant
  println((Some(20) : Option[Int]) === (Some(20): Option[Int]))

  //but you can use the apply functions on option instead
  println(Option(1) =!= Option(2))
  println(Option(1) =!= Option.empty[Int])

  // or some help from cats! just get the syntax to make things options
  import cats.syntax.option._
  println(1.some === None)

}

object CustomEquality extends App{
  import cats.syntax.eq._
  import cats.kernel.Eq

  case class Person(name: String)

  implicit val personEqual = Eq.instance[Person] { (p1, p2) =>
    p1.name==p2.name
  }

  val x = Person("Chris")
  val y = Person("Tom")
  val z = Person("Tom")

  println(x === y)
  println(y === z)
}