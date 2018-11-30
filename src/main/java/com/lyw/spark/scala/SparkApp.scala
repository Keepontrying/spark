package com.lyw.spark.scala

import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source
import scala.tools.nsc.io.JFile

class SparkApp{

  def main(args: Array[String]) {
    myFunc("myApp")

  }


  def myFunc(appName: String){
    val sparkConf = new SparkConf().setAppName(appName).setMaster("spark://172.24.5.64:8180")
    val sparkContext = new SparkContext(sparkConf)
    val textFile = sparkContext.textFile("D://export//abc.html")
    val aCount = textFile.filter(line => line.contains("a")).count()
    print(aCount)
  }

  def analyText(filePath:String): String ={

    val stringBuilder = new StringBuilder()
    val file = Source.fromFile(new JFile(filePath))
    for(line <- file.getLines()){
      stringBuilder.append(line+"\n")
    }
    return stringBuilder.toString()
  }
}