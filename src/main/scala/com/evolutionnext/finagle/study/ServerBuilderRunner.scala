package com.evolutionnext.finagle.study

import com.twitter.finagle.{ListeningServer, Service}
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.io.Buf.ByteArray
import com.twitter.io.Reader
import com.twitter.util.{Await, Future}

object ServerBuilderRunner extends App {
  import com.twitter.finagle.Http

  val service = new Service[Request, Response]() {
    override def apply(request: Request): Future[Response] =
      Future { Response.apply(request.version,
        Status.Ok, Reader.fromBuf(new ByteArray("Hello".getBytes, 0, 5)))}
  }

  Await.ready(Http.server.serve("localhost:10350", service))
}
