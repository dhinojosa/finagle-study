package com.evolutionnext.finagle.study.client

import com.twitter.finagle.Http
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.http.{Method, Request, Response, Version}
import com.twitter.finagle.transport.Transport
import com.twitter.util.Future
import org.scalatest.FunSuite

class ClientTest extends FunSuite {
  test("Running a client with getContentString") {
    val httpService = Http.newService("www.google.com:80")

    val future: Future[Response] = httpService.apply(
      Request(Method.Get, "/"))

    future onSuccess { response: Response =>
      println("received response " + response.getContentString())
    }
    future onFailure { throwable: Throwable =>
      println("received failure " + throwable.getMessage)
    }

    Thread sleep 10000
  }

  test("Creating a client with ClientBuilder") {
    import com.twitter.finagle.http.{Http => httpCodec}

    val service = ClientBuilder()
      .codec(new httpCodec())
      .hosts("google.com:80")
      .hostConnectionLimit(3)
      .build()

    val future: Future[Response] = service(Request(Version.Http11,
      Method.Get, "/"))

    future onSuccess { response: Response =>
      println("received response " + response.getContentString())
    }

    future onFailure { throwable: Throwable =>
      println("received failure " + throwable.getMessage)
    }

    Thread sleep 10000
  }

  test("Creating a client with the new Finagle 6 API") {

    val service = Http.client
      .configured(Transport.Options(noDelay = false, reuseAddr = false))
      .newService("google.com:80")

    val future: Future[Response] = service(Request(Version.Http11,
      Method.Get, "/"))

    future onSuccess { response: Response =>
      println("received response " + response.getContentString())
    }

    future onFailure { throwable: Throwable =>
      println("received failure " + throwable.getMessage)
    }

    Thread sleep 10000
  }

  test("Running a client with custom server") {
    val httpService = Http.newService("localhost:10350")

    val future: Future[Response] = httpService.apply(
      Request(Method.Get, "/"))

    future onSuccess { response: Response =>
      println("received response " + response.getContentString())
    }
    future onFailure { throwable: Throwable =>
      println("received failure " + throwable.getMessage)
    }
    Thread sleep 10000
  }
}
