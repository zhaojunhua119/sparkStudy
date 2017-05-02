package com.lambdazhao.spark
import org.apache.spark._
import org.apache.spark.SparkContext._

object CustomerOrders {
  def main(args:Array[String]){
    val sc=new SparkContext("local[*]","customer orders")
    val texts=sc.textFile("../data/customer-orders.csv")
    val data=texts.map(x=>{
      val splits=x.split(",")
      (splits(0).toInt,splits(1).toInt,splits(2).toDouble)
    })
    data.map(x=>(x._1,x._3)).reduceByKey(_+_).sortBy(_._2, false)
      .collect().foreach(println)
  }
}