package com.lyw.spark.scala.service

import java.util

import com.lyw.spark.entity.LoginUserEntity


trait LoginAction{

  case class LoginUserEntity();

  //封装每一行为数据对象
  def packageEntity(line:String):LoginUserEntity;

  //解析文档
  def document(file_path:String) :Array[LoginUserEntity];

}