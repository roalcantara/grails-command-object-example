package com.foo

class Ticket {

    Customer customer
    Product product
    Defect defect
    Solution solution
    String comments
    Billing billing

    static constraints = {

      //can be set only on creating
      customer nullable:false,
             updateable:false

      //can be set only on creating
      product nullable:false,
            updateable:false

      //can be set either on creating or updating
      defect nullable:true

      //can be set only on updating
      solution nullable:true,
             insertable:false

      //can be set only by users with certain role
      comments nullable:true

      //cannot be set in the Ticket's form whatsoever
      //yet, it can be set either on creating or updating in other proccesses
      billing nullable:true
    }
}
