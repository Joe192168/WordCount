package com.spark.jisuan

import java.io.{ObjectOutputStream, OutputStream}
import java.net.Socket

object Driver {

  def main(args: Array[String]): Unit = {
    //链接服务器
    val client: Socket = new Socket("localhost",9999)

    val outputStream: OutputStream = client.getOutputStream
    val objStream: ObjectOutputStream = new ObjectOutputStream(outputStream)

    val task: Task = new Task()
    objStream.writeObject(task)

    objStream.close()
    client.close()
  }

}
