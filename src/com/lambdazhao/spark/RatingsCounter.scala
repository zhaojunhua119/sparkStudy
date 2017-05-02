package com.lambdazhao.spark
import org.apache.log4j._
import org.apache.spark._
import org.apache.spark.SparkContext._

object MyRatingsCounter {
  def main(args:Array[String]){
    Logger.getLogger("scala").setLevel(Level.ERROR);
    val sc=new SparkContext("local[*]","test context");
    val lines=sc.textFile("../ml-100k/u.data", 4);
    lines.map(x=>Integer.parseInt(x.split("\t")(2))).countByValue().toSeq.sortBy(_._1)(Ordering.Int.reverse).foreach(println);
    
  }
}