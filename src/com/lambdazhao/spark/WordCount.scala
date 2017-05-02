package com.lambdazhao.spark
import org.apache.spark._;
import org.apache.spark.SparkContext._;
import org.apache.log4j._;


object WordCount {
  def main(args: Array[String]){
    //Logger.getLogger("org").setLevel(Level.ERROR);
    val sc=new SparkContext("local[*]","wc");
    sc.textFile("../data/book.txt").flatMap(_.split("\\W+")).map(_.toUpperCase())
    .map((_,1)).reduceByKey(_+_).map(x=>(x._2,x._1)).sortBy(_._2).foreach(println)
  }
}