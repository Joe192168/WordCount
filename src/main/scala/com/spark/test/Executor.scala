package com.spark.test

import java.io.InputStream
import java.net.{ServerSocket, Socket}

object Executor {

  def main(args: Array[String]): Unit = {

    //启动服务器，接受数据
    val server: ServerSocket = new ServerSocket(9999)
    println("服务器启动，等待接受数据")

    //等待客户端的链接
    val client: Socket = server.accept()

    val inputStream: InputStream = client.getInputStream

    val i: Int = inputStream.read()

    println("接收到客户端的数据："+i)

    inputStream.close()
    client.close()
    server.close()

  }

}
