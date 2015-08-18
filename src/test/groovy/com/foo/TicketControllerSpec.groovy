package com.foo

import grails.test.mixin.*
import spock.lang.*

@Mock(Ticket)
@TestFor(TicketController)
class TicketControllerSpec extends Specification {

  def populateValidParams(params) {
    assert params != null
      // TODO: Populate valid properties like...
      params['customer.id'] = Customer.first().id
      params['product.id'] = Product.last().id
  }

  void "Test the index action returns the correct model"() {
    when:"The index action is executed"
      controller.index()

    then:"The model is correct"
      !model.ticketList
      model.ticketCount == 0
  }

  void "Test the create action returns the correct model"() {
    when:"The create action is executed"
      controller.create()

    then:"The model is correctly created"
      model.ticket!= null
  }

  void "Test the save action correctly persists an instance"() {
    when:"The save action is executed with an invalid instance"
      request.contentType = FORM_CONTENT_TYPE
      request.method = 'POST'
      def ticket = new Ticket()
      ticket.validate()
      controller.save(ticket)

    then:"The create view is rendered again with the correct model"
      model.ticket!= null
      view == 'create'

    when:"The save action is executed with a valid instance"
      response.reset()
      populateValidParams(params)
      ticket = new Ticket(params)

      controller.save(ticket)

    then:"A redirect is issued to the show action"
      response.redirectedUrl == '/ticket/show/1'
      controller.flash.message != null
      Ticket.count() == 1
  }

  void "Test that the show action returns the correct model"() {
    when:"The show action is executed with a null domain"
      controller.show(null)

    then:"A 404 error is returned"
      response.status == 404

    when:"A domain instance is passed to the show action"
      populateValidParams(params)
      def ticket = new Ticket(params)
      controller.show(ticket)

    then:"A model is populated containing the domain instance"
      model.ticket == ticket
  }

  void "Test that the edit action returns the correct model"() {
    when:"The edit action is executed with a null domain"
      controller.edit(null)

    then:"A 404 error is returned"
      response.status == 404

    when:"A domain instance is passed to the edit action"
      populateValidParams(params)
      def ticket = new Ticket(params)
      controller.edit(ticket)

    then:"A model is populated containing the domain instance"
      model.ticket == ticket
  }

  void "Test the update action performs an update on a valid domain instance"() {
    when:"Update is called for a domain instance that doesn't exist"
      request.contentType = FORM_CONTENT_TYPE
      request.method = 'PUT'
      controller.update(null)

    then:"A 404 error is returned"
      response.redirectedUrl == '/ticket/index'
      flash.message != null

    when:"An invalid domain instance is passed to the update action"
      response.reset()
      def ticket = new Ticket()
      ticket.validate()
      controller.update(ticket)

    then:"The edit view is rendered again with the invalid instance"
      view == 'edit'
      model.ticket == ticket

    when:"A valid domain instance is passed to the update action"
      response.reset()
      populateValidParams(params)
      ticket = new Ticket(params).save(flush: true)
      controller.update(ticket)

    then:"A redirect is issued to the show action"
      ticket != null
      response.redirectedUrl == "/ticket/show/$ticket.id"
      flash.message != null
  }

  void "Test that the delete action deletes an instance if it exists"() {
    when:"The delete action is called for a null instance"
      request.contentType = FORM_CONTENT_TYPE
      request.method = 'DELETE'
      controller.delete(null)

    then:"A 404 is returned"
      response.redirectedUrl == '/ticket/index'
      flash.message != null

    when:"A domain instance is created"
      response.reset()
      populateValidParams(params)
      def ticket = new Ticket(params).save(flush: true)

    then:"It exists"
      Ticket.count() == 1

    when:"The domain instance is passed to the delete action"
      controller.delete(ticket)

    then:"The instance is deleted"
      Ticket.count() == 0
      response.redirectedUrl == '/ticket/index'
      flash.message != null
  }
}
