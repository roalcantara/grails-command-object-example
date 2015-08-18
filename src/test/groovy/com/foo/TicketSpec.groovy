package com.foo

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Ticket)
class TicketSpec extends Specification {

  def setup() {
  }

  def cleanup() {
  }

  @Unroll("Ticket.'#field' with '#val' is [#error]")
  void 'Ticket is valid'() {
    given:
      def obj = new Ticket("$field": val)
    when:
      obj.validate()
    then:
      if("$error" == 'OK') {
        assert !obj.errors["$field"]
      } else {
        assert obj.hasErrors()
        assert obj.errors["$field"]
        assert obj.errors["$field"].code == "$error"
      }
    where:
      error                  | field        | val
      'nullable'             | 'customer'   | null
      'nullable'             | 'product'    | null
      'OK'                   | 'defect'     | null
      'OK'                   | 'solution'   | null
      'OK'                   | 'comments'   | null
      'OK'                   | 'billing'    | null
  }
}
