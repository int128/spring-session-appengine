package example;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@SessionScope
@Component
public class Foo implements Serializable {
    @Getter @Setter
    private int count;

    public int increment() {
        return ++count;
    }
}
