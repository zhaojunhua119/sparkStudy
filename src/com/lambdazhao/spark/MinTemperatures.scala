package com.lambdazhao.spark
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import scala.math.min

object MinTemperatures {
  def parse(text: String)={
    val fields = text.split(",")
    val stationID = fields(0)
    val entryType = fields(2)
    val temperature = fields(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
    (stationID, entryType, temperature)
  }
  def main(args:Array[String]){
    Logger.getLogger("org").setLevel(Level.ERROR);
    val sc=new SparkContext("local[*]","MinTemperatures");
    val texts=sc.textFile("../data/1800.csv")
    val re=texts.map(parse).filter(_._2=="TMIN").map(x=>(x._1,x._3))
      .reduceByKey((a,b)=>min(a,b)).collect();
    re.foreach(println);
  }
}