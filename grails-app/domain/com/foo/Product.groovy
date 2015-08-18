package com.foo

class Product {

  String name

  static constraints = {
    name nullable:false
  }

  @Override
  public String toString() {
    this.name
  }
}
