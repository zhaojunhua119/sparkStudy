package com.lambdazhao.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._

object FriendByAge {
  def parse(text: String):(String,Int)={
    val split=text.split(",");
    (split(1).charAt(0).toString(),split(3).toInt)
  }
//  def main(args: Array[String] ):Unit ={
//    Logger.getLogger("org").setLevel(Level.ERROR);
//    val sc=new SparkContext("local[1]","FriendByAge");
//    val texts=sc.textFile("../data/fakefriends.csv");
//    texts.map(parse).groupBy(_._1)
//      .map(x=>{
//        val value=x._2.reduce((a1,a2)=>(a1._1,( a1._2._1+a2._2._1,a1._2._2+a2._2._2)));
//        val all=(value._2._1,value._2._2);
//        (x._1,all)
//      }).sortBy(_._1).foreach(println)
//      
//  }
  def main(args: Array[String] ):Unit ={
    Logger.getLogger("org").setLevel(Level.ERROR);
    val sc=new SparkContext("local[*]","FriendByAge");
    val texts=sc.textFile("../data/fakefriends.csv");
    val totalsByAge=texts.map(parse).mapValues((_,1)).reduceByKey((x,y)=>(x._1+y._1,x._2+y._2));
    totalsByAge.mapValues(x=>x._1/x._2).collect().sortBy(_._2)(Ordering[Int].reverse).foreach(println);
    
  }
}