package com.evolutionnext.finagle.study

import com.twitter.util.Future
import org.scalatest.FunSuite

class FinagleFutureTest extends FunSuite {
   test("Trying out the Finagle Future") {
     val f:Future[Int] = Future {
       Thread.sleep(4000); 4 + 10
     }
     f.onSuccess(x => println("The value returned at %d".format(x)))
     f.onFailure(t => println("Exception occured: %s".format(t.getMessage)))
   }

  test("Trying out the Finagle Future Composition") {
    val f1 = Future {
      Thread sleep 4000; 100
    }
    val f2 = Future {
      Thread sleep 2000; 90
    }
    val result = f1 flatMap (x => f2 map (y => x + y))
    result foreach (x => printf("Result gained is %d", x))
  }
}
