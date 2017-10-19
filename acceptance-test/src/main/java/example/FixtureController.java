package example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FixtureController {
    private final Foo foo;

    @GetMapping("/foo")
    @ResponseBody
    int index() {
        return foo.getCount();
    }

    @GetMapping("/foo/increment")
    @ResponseBody
    int increment() {
        return foo.increment();
    }
}
