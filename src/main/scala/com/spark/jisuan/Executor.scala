package com.spark.jisuan

import java.io.{InputStream, ObjectInputStream}
import java.net.{ServerSocket, Socket}

object Executor {

  def main(args: Array[String]): Unit = {

    //启动服务器，接受数据
    val server: ServerSocket = new ServerSocket(9999)
    println("服务器启动，等待接受数据")

    //等待客户端的链接
    val client: Socket = server.accept()

    val inputStream: InputStream = client.getInputStream

    val objectStream: ObjectInputStream = new ObjectInputStream(inputStream)
    val task: Task = objectStream.readObject().asInstanceOf[Task]
    val list: List[Int] = task.compute()

    println("计算节点的结果为："+list)

    objectStream.close()
    client.close()
    server.close()

  }

}
