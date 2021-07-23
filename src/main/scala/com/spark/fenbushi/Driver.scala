package com.spark.fenbushi

import java.io.{ObjectOutputStream, OutputStream}
import java.net.Socket

object Driver {

  def main(args: Array[String]): Unit = {
    //链接服务器
    val client1: Socket = new Socket("localhost",8888)
    val client2: Socket = new Socket("localhost",9999)

    val datas: Datas = new Datas()

    val outputStream1: OutputStream = client1.getOutputStream
    val objStream1: ObjectOutputStream = new ObjectOutputStream(outputStream1)

    val subTask1: SubTask = new SubTask()
    //分配两个数据给task
    subTask1.datas = datas.datas.take(2)
    subTask1.logic = datas.logic

    objStream1.writeObject(subTask1)
    objStream1.flush()
    objStream1.close()
    client1.close()

    val outputStream2: OutputStream = client2.getOutputStream
    val objStream2: ObjectOutputStream = new ObjectOutputStream(outputStream2)

    val subTask2: SubTask = new SubTask()
    //分配两个数据给task
    subTask2.datas = datas.datas.takeRight(2)
    subTask2.logic = datas.logic

    objStream2.writeObject(subTask2)
    objStream2.flush()
    objStream2.close()
    client2.close()
  }

}
