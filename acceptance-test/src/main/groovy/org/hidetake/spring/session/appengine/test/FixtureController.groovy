package org.hidetake.spring.session.appengine.test

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.SessionAttributes

@RestController
@SessionAttributes('exampleModel')
class FixtureController {
    @ModelAttribute('exampleModel')
    ExampleModel exampleModel() {
        new ExampleModel()
    }

    @GetMapping('/value')
    int get(ExampleModel exampleModel) {
        exampleModel.value
    }

    @GetMapping('/increment')
    int increment(ExampleModel exampleModel) {
        exampleModel.value = exampleModel.value + 1
    }
}
