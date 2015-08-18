package com.foo

class Customer {

  String name

  static constraints = {
    name nullable:false
  }

  @Override
  public String toString() {
    this.name
  }
}
