package essential.sequencingcollections


object ProductTypes extends App{
  case class Pair[A,B](a: A, b: B)

  def intAndString: Pair[Int, String] = Pair(33, "CJ")
  def boolAndString: Pair[Boolean, String] = Pair(true, "CJ rulz")

  println(s"intAndString = ${intAndString}")
  println(s"boolAndString = ${boolAndString}")
}

object SumTypes extends App{

  sealed trait Sum[A, B]
  final case class Left[A, B](value: A) extends Sum[A,B]
  final case class Right[A, B](value: B) extends Sum[A,B]

  val x: Sum[String, Int] = Right(123)

  println(s"x = ${x}")

}

object MaybeApp extends App{

  sealed trait Maybe[+A]{

    def map[B] (f: A=>B): Maybe[B] = this match{
      case Empty => Empty
      case Full(a) => Full(f(a))
    }

    def flatMap[B] (f: A=>Maybe[B]): Maybe[B] = this match{
      case Empty => Empty
      case Full(a) => f(a)
    }
  }

  final case object Empty extends Maybe[Nothing]
  final case class Full[A](value: A) extends Maybe[A]

  val perhaps: Maybe[Int] = Empty
  val deffo: Maybe[Int] = Full(20)


  def multiplyBy2 = (x: Int) => x * 2
  def maybeMultiplyBy2 = (x: Int) => Full(x * 2)

  println(s"perhaps map multiplyBy2 = ${perhaps.map(multiplyBy2)}")
  println(s"perhaps map maybeMultiply = ${perhaps.map(maybeMultiplyBy2)}")
  println(s"perhaps flatMap maybeMultiply = ${perhaps.flatMap(maybeMultiplyBy2)}")

  println(s"deffo map mutiplyBy2 = ${deffo.map(multiplyBy2)}")
  println(s"deffo map maybeMultiply = ${deffo.map(maybeMultiplyBy2)}")
  println(s"deffo flatMap maybeMultiply = ${deffo.flatMap(maybeMultiplyBy2)}")

}