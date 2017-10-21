package example.test

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import spock.lang.Specification

class HttpSessionSpec extends Specification {
    static final BASE_URL = 'http://localhost:8080'

    def client = HttpClients.createDefault()

    def 'session should be kept within timeout'() {
        when:
        def first = fooIncrement()

        then:
        first.getFirstHeader('set-cookie').value =~ /^SESSION=/
        first.entity.content.text == '1'

        when:
        sleep(100)
        def second = fooIncrement()

        then:
        second.getFirstHeader('set-cookie') == null
        second.entity.content.text == '2'
    }

    def 'session should be expired on timed out'() {
        when:
        def first = fooIncrement()

        then:
        first.getFirstHeader('set-cookie').value =~ /^SESSION=/
        first.entity.content.text == '1'

        when:
        sleep(3000)
        def second = fooIncrement()

        then:
        second.getFirstHeader('set-cookie').value =~ /^SESSION=/
        second.entity.content.text == '1'
    }

    private def fooIncrement() {
        client.execute(new HttpGet("$BASE_URL/foo/increment"))
    }
}
