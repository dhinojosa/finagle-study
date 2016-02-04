package com.evolutionnext.finagle.study

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Method, Response, Request}
import com.twitter.util.Await
import org.scalatest.FunSuite

/**
 *
 * @author Daniel Hinojosa
 * @since 9/24/15 11:33 PM
 *        url: <a href="http://www.evolutionnext.com">http://www.evolutionnext.com</a>
 *        email: <a href="mailto:dhinojosa@evolutionnext.com">dhinojosa@evolutionnext.com</a>
 *        tel: 505.363.5832
 */
class ClientTest extends FunSuite {
  test("Running a client") {
    val client: Service[Request, Response] = Http.newService("www.scala-lang.org:80")
    val request = Request(Method.Get, "/")
    request.host = "www.scala-lang.org"
    val response = client(request)
    response.onSuccess{res => println(res)}
    Await.ready(response)
  }
}
