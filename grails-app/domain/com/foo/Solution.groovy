package com.foo

class Solution {

  String name

  static constraints = {
    name nullable:false
  }

  @Override
  public String toString() {
    this.name
  }
}
