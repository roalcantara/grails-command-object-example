package com.foo

class Billing {

  Double amount

  static constraints = {
    amount nullable:false
  }

  @Override
  public String toString() {
    this.amount as String
  }
}
