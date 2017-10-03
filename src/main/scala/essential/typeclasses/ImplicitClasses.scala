package essential.typeclasses

object IntImplicits {

  implicit class OhYeah(n: Int) {
    def yeah = for {_ <- 0 until n} println("Oh yeah!")
  }

  implicit class Times(n: Int){
    def times(f: Int => Unit) = for {x <- 0 until n} f(x)
  }

}

object Ex1 extends App{
  import IntImplicits._

  2.yeah
  3.times(i => println(s"Look - it's the number $i"))
}