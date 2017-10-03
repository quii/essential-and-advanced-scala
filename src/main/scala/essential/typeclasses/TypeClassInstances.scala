package essential.typeclasses

object OrderingApp extends App {

  //implement the type class for the type
  implicit val absOrdering = Ordering.fromLessThan[Int](Math.abs(_) < Math.abs(_))

  //this sorted takes an instance of a typeclass
  assert(List(-4, -1, 0, 2, 3).sorted(absOrdering) == List(0, -1, 2, 3, -4))

  //this one gets it implicitly (but has a default one, urgh)
  assert(List(-4, -1, 0, 2, 3).sorted == List(0, -1, 2, 3, -4))

}