package com.foo

class Defect {

  String name

  static hasMany = [solutions: Solution]

  static constraints = {
    name nullable:false
  }

  @Override
  public String toString() {
    this.name
  }
}
