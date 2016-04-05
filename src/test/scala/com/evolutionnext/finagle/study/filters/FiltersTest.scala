package com.evolutionnext.finagle.study.filters

import com.twitter.finagle.Http
import com.twitter.finagle.http.{Method, Request, Response}
import com.twitter.util.Future
import org.scalatest.FunSuite

class FiltersTest extends FunSuite {

  test("Running a request of a private resource") {
    val httpService = Http.newService("localhost:10350")

    val future: Future[Response] = httpService.apply(
      Request(Method.Get, "/private/panama.html"))

    future onSuccess { response: Response =>
      println("received response status: " + response.status +
        " with content:" + response.getContentString())
    }

    future onFailure { throwable: Throwable =>
      println("received failure: " + throwable.getMessage)
    }
    Thread sleep 10000
  }

  test("Running a request of a public resource") {
    val httpService = Http.newService("localhost:10350")

    val future: Future[Response] = httpService.apply(
      Request(Method.Get, "/public/open_knowledge.html"))

    future onSuccess { response: Response =>
      println("received response status: " + response.status +
        " with content:" + response.getContentString())
    }

    future onFailure { throwable: Throwable =>
      println("received failure: " + throwable.getMessage)
    }
    Thread sleep 10000
  }
}