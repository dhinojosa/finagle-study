package com.evolutionnext.finagle.study.futures

import com.twitter.util.Future
import org.scalatest.FunSuite

class FuturesTest extends FunSuite {

  test("Running with a future") {
    val f = Future {
      Thread.sleep(1000)
      5 + 10
    }
    Thread.sleep(3000)
    f foreach println
    Thread.sleep(4000)
  }

  test("Finagle Futures with Success") {
    val f: Future[Int] = Future {
      Thread sleep 4000
      4 + 10
    }
    f onSuccess (x => println("The value returned at %d" format x))
    f onFailure (t => println("Exception occured: %s" format t.getMessage))
    Thread sleep 5000
  }

  test("Finagle Futures with Failure") {
    val f: Future[Int] = Future {
      Thread sleep 4000
      throw new RuntimeException("Something bad happened")
    }
    f onSuccess (x => println("The value returned at %d" format x))
    f onFailure (t => println("Exception occured: %s" format t.getMessage))
    Thread sleep 5000
  }

  test("Finagle Future Composition") {
    val f1 = Future {
      Thread sleep 4000
      100
    }
    val f2 = Future {
      Thread sleep 2000
      90
    }
    val result = f1 flatMap (x => f2 map (y => x + y))
    result foreach (x => printf("Result gained is %d", x))
  }

  test("Running a promise") {
    import com.twitter.util.Promise

    val promise: Promise[Int] = Promise[Int]()
    val future = promise.foreach(println)
    Thread.sleep(3000)
    promise.setValue(50)
    Thread.sleep(6000)
  }

  test("Rescuing from exception") {
    val f = Future {
      Thread sleep 4000
      throw new IllegalArgumentException("Something is wrong")
      400
    }

    f onSuccess (x => println("The value returned at future is %d" format x))
    val rescueFuture = f rescue {
      case e: IllegalArgumentException =>
        Future {
          10
        };
      case e: IllegalStateException =>
        Future {
          19
        };
    }

    rescueFuture onSuccess (x =>
      println("The value returned at rescue future is %d" format x))
  }
}
