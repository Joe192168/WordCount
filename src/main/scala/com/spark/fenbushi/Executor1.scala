package com.spark.fenbushi

import java.io.{InputStream, ObjectInputStream}
import java.net.{ServerSocket, Socket}

object Executor1 {

  def main(args: Array[String]): Unit = {

    //启动服务器，接受数据
    val server: ServerSocket = new ServerSocket(9999)
    println("服务器启动，等待接受数据")

    //等待客户端的链接
    val client: Socket = server.accept()

    val inputStream: InputStream = client.getInputStream

    val objectStream: ObjectInputStream = new ObjectInputStream(inputStream)
    val subTask: SubTask = objectStream.readObject().asInstanceOf[SubTask]
    val list: List[Int] = subTask.compute()

    println("计算节点的结果为："+list)

    objectStream.close()
    client.close()
    server.close()

  }

}
