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

object SuperAdder extends App{
  import cats.Monoid
  import cats.syntax.semigroup._
  import cats.instances.int._


  case class Order(totalCost: Double, quantity: Double)

  implicit val orderMonoid: Monoid[Order] = new Monoid[Order] {
    override def empty = Order(0, 0)
    override def combine(x: Order, y: Order) = Order(x.totalCost+y.totalCost, x.quantity+y.quantity)
  }

  def notVeryGenericAdd(items: List[Int]): Int = items.foldLeft(Monoid[Int].empty)(_ |+| _)

  // so long as you provide a Monoid of A, you're cooking
  def add[A](items: List[A])(implicit monoid: Monoid[A]): A = items.foldLeft(Monoid[A].empty)(_ |+| _)

  println(notVeryGenericAdd(List(1, 2, 3)))
  println(add(List(1, 2, 3)))
  println(add(List(Order(20, 2), Order(30, 10))))
}

object PickADifferentMonoid extends App{
  import cats.Monoid
  import cats.syntax.semigroup._

  val multiplicationMonoid = new Monoid[Int] {
    override def empty = 1
    override def combine(x: Int, y: Int) = x* y
  }

  def myThing(x: Int, y: Int) ={
    // import the typeclass impl you need to change it up
    implicit val myMonoid = multiplicationMonoid
    x |+| y
  }

  println(myThing(2, 4))
}