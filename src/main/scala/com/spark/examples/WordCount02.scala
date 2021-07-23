package com.spark.examples

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount02 {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("WordCount")
    //Spark框架
    //1、建立和Spark框架连接 比如：JDBC：Connection
    val sc = new SparkContext(sparkConf)
    //2、执行业务
    //2.1 读取文件，获取一行一行的数据
    val lines: RDD[String] = sc.textFile("datas")

    //2.2 将一行数据进行拆分，形成一个一个单词（分词）
    //flatMap(扁平化)：将整体拆分成个体的操作
    val words: RDD[String] = lines.flatMap(_.split(" "))

    val wordToOne: RDD[(String, Int)] = words.map(
      word => (word, 1)
    )

    //2.3 将数据单词进行分组，便于统计
    val wordGroup: RDD[(String, Iterable[(String, Int)])] = wordToOne.groupBy(t => t._1)

    //2.4 对分组后的数据进行转换
    var wordCount = wordGroup.map {
      case (word, list) => {
        list.reduce(
          (t1, t2) => {
            (t1._1, t1._2 + t2._2)
          }
        )
      }
    }

    //2.5 将转换结果采集到控制台打印出来
    val arrays: Array[(String, Int)] = wordCount.collect()
    arrays.foreach(println)
    //3、关闭连接
    sc.stop()


  }


}
