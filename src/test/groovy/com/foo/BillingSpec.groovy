package com.foo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Billing)
class BillingSpec extends Specification {

  def setup() {
  }

  def cleanup() {
  }

  void 'Amount is required'() {

    given:
      def obj = new Billing()

    when:
      obj.validate()

    then:
      obj.errors
      obj.errors['amount']
  }
}
