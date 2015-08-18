package com.foo

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Solution)
class SolutionSpec extends Specification {

  def setup() {
  }

  def cleanup() {
  }

  void 'Name is required'() {

    given:
      def obj = new Solution()

    when:
      obj.validate()

    then:
      obj.errors
      obj.errors['name']
  }
}
