package example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class FixtureController {
    private final Foo foo;

    @GetMapping("/")
    @ResponseBody
    int index() {
        return foo.getCount();
    }

    @PostMapping("/")
    @ResponseBody
    int update() {
        return foo.increment();
    }
}
