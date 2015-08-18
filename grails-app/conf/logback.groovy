import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = "%level %logger - %msg%n"
  }
}

root ERROR, ['STDOUT']

if(Environment.current == Environment.DEVELOPMENT) {
  def targetDir = BuildSettings.TARGET_DIR
  if(targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
      file = "${targetDir}/stacktrace.log"
      append = true
      encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
      }
    }
    logger "StackTrace", ERROR, ['FULL_STACKTRACE'], false

    def configure = ['grails.app.conf',
                     'grails.app.init',
                     'grails.app.controllers.com.foo',
                     'grails.app.services.com.foo',
                     'grails.app.domain.com.foo']
    configure.each {
      logger it, DEBUG, ['STDOUT'], false
    }
  }
}
