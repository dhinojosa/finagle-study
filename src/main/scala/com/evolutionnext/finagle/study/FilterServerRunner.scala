package com.evolutionnext.finagle.study

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Http, Service, SimpleFilter, http}
import com.twitter.io.{Reader, Writer}
import com.twitter.util.{Await, Future}


object FilterServerRunner extends App {
  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] =
      Future.value(
        http.Response(req.version, http.Status.Ok)
      )
  }

  val filter = new SimpleFilter[http.Request, http.Response] {
    override def apply
    (request: Request, service: Service[Request, Response]): Future[Response] = {
      println("Processing request:" + request.uri)
      if (request.uri.contains("private")) {
        Future.value(http.Response(request.version, http.Status.BadRequest))
      } else {
        service.apply(request)
      }
    }
  }

  val combined = filter.andThen(service)
  val server = Http.serve(":10350", combined)
  Await.ready(server)
}
