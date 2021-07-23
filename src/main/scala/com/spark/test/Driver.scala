package com.spark.test

import java.io.OutputStream
import java.net.Socket

object Driver {

  def main(args: Array[String]): Unit = {
    //链接服务器
    val client: Socket = new Socket("localhost",9999)

    val outputStream: OutputStream = client.getOutputStream

    outputStream.write(66)

    outputStream.close()

    client.close()
  }

}
