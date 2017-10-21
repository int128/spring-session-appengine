package example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class FixtureController {
    private final Foo foo;

    @GetMapping("/sid")
    @ResponseBody
    String sid(HttpSession session) {
        return session.getId();
    }

    @GetMapping("/foo")
    @ResponseBody
    String index() {
        return String.valueOf(foo.getCount());
    }

    @GetMapping("/foo/increment")
    @ResponseBody
    String increment() {
        return String.valueOf(foo.increment());
    }
}
