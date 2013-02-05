import static ch.qos.logback.classic.Level.*
import static ch.qos.logback.core.spi.FilterReply.DENY
import static ch.qos.logback.core.spi.FilterReply.NEUTRAL
import static ch.qos.logback.core.spi.FilterReply.ACCEPT
import ch.qos.logback.classic.boolex.GEventEvaluator
import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.filter.EvaluatorFilter
import ch.qos.logback.core.FileAppender
import ch.qos.logback.classic.filter.LevelFilter

def patternExpression = "%date{ISO8601} [%5level] %class - %msg%n"

appender("FILE", FileAppender) {
  file = "errors.log"
  filter(LevelFilter) {
    level = ERROR
    onMatch = ACCEPT
    onMismatch = DENY
  }
  //append = true
  encoder(PatternLayoutEncoder) {
    pattern = patternExpression
  }
}

appender("STDERR", ConsoleAppender) {
    filter(EvaluatorFilter) {
      evaluator(GEventEvaluator) {
        expression = 'e.level.toInt() >= WARN.toInt()'
      }
      onMatch = NEUTRAL
      onMismatch = DENY
    }
    encoder(PatternLayoutEncoder) {
      pattern = patternExpression
    }
    target = "System.err"
  }

appender("STDOUT", ConsoleAppender) {
    filter(EvaluatorFilter) {
      evaluator(GEventEvaluator) {
        expression = 'e.level.toInt() < WARN.toInt()'
      }
      onMismatch = DENY
      onMatch = NEUTRAL
    }
    encoder(PatternLayoutEncoder) {
      pattern = patternExpression
    }
    target = "System.out"
}

logger("org.hibernate.type", WARN)
logger("org.hibernate", WARN)
logger("org.springframework", WARN)
//logger("com.boky.SubjectParser", TRACE)

root(INFO,["STDERR","STDOUT","FILE"])