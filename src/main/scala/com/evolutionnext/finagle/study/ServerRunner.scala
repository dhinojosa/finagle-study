package com.evolutionnext.finagle.study

import com.twitter.finagle.{Http, Service, http}
import com.twitter.util.{Await, Future}

/**
  *
  * @author Daniel Hinojosa
  * @since 2/4/16 1:42 PM
  *        url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
  *        email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
  *        tel: 505.363.5832
  */
object ServerRunner extends App {
  val service = new Service[http.Request, http.Response] {
    def apply(req: http.Request): Future[http.Response] =
      Future.value(
        http.Response(req.version, http.Status.Ok)
      )
  }

  val server = Http.serve(":10350", service)
  Await.ready(server)
}
