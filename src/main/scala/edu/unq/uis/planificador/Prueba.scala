package edu.unq.uis.planificador

class Chain[A, B](f: A => Either[A, B]) {
  def +>(next: => (A => Either[A, B])): A => Either[A, B] =
    f(_).left.flatMap(next) // call next handler if not yet processed
}

object Chain {
  implicit def func2Chain[A, B](f: A => Either[A, B]) = new Chain[A, B](f)
}

case class CoinCount(coinValue: Int, coinCount: Int)
case class CoinState(currentAmount: Int, acc: List[CoinCount]) // coints state transfer object

object Main {
  def main(args: Array[String]) = {
    import Chain._  // import implicit conversion function

    // function for setup coin value in coins calculate function
    val func =
      (coin: Int) =>
        (obj: CoinState) =>
          if(obj.currentAmount % coin == 0) Right(CoinCount(coin, obj.currentAmount / coin) :: obj.acc)
          else Left(CoinState(obj.currentAmount % coin, CoinCount(coin, obj.currentAmount / coin) :: obj.acc))

    val chainFunc = func(5) +> func(3) +> func(2) // define Chain of Responsibility function

    println {
      chainFunc(CoinState(12, Nil))  // handle request by chain
    }
  }
}