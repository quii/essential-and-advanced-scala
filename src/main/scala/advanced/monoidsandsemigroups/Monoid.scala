package advanced.monoidsandsemigroups

object MonoidInstances extends App{
  import cats.Monoid
  import cats.instances.string._
  import cats.instances.int._
  import cats.instances.option._

  private val monoidInt = Monoid[Int]
  private val monoidString: Monoid[String] = Monoid[String]
  private val monoidOption = Monoid[Option[Int]]

  println(monoidString.combine("Hi ", "there"))
  println(monoidString.empty)

  println(monoidInt.combine(2, 4))

  println(monoidOption.combine(Option(2), Option(4)))
  println(monoidOption.combine(Option(2), Option.empty[Int]))
}

object MonoidSyntax extends App{
  import cats.syntax.semigroup._ //remember combine comes from semigroup, which monoid inherits from
  import cats.instances.string._
  import cats.instances.option._
  import cats.Monoid

  val res = "Hi " |+| "there" |+| Monoid[String].empty

  println(res)

  println(Option("Hello, ") |+| Option("World"))
}