import com.foo.Customer
import com.foo.Product
import com.foo.Defect
import com.foo.Solution

class BootStrap {

  def init = { servletContext ->

    log.debug "Creating CUSTOMERS, PRODUCTS, DEFECTS & SOLUTIONS.."
    (1..2).each {
      def customerInstance = Customer.findOrSaveWhere name:"${Customer.class.simpleName.toUpperCase()} $it"
      log.debug "CUSTOMER: '$customerInstance' CREATE OR SAVE: [OK]!"

      def productInstance = Product.findOrSaveWhere name:"${Product.class.simpleName.toUpperCase()} $it"
      log.debug "PRODUCT: '$productInstance' CREATE OR SAVE: [OK]!"

      def defectInstance = Defect.findOrSaveWhere name:"${Defect.class.simpleName.toUpperCase()} $it"
      log.debug "DEFECT: '$defectInstance' CREATE OR SAVE: [OK]!"

      def solutionInstance = Solution.findOrSaveWhere name:"${Solution.class.simpleName.toUpperCase()} $it"
      log.debug "SOLUTION: '$solutionInstance' CREATE OR SAVE: [OK]!"
    }
  }
  def destroy = {
  }
}
