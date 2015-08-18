package com.foo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Defect)
class DefectSpec extends Specification {

  def setup() {
  }

  def cleanup() {
  }

  void 'Name is required'() {

    given:
      def obj = new Defect()

    when:
      obj.validate()

    then:
      obj.errors
      obj.errors['name']
  }
}
