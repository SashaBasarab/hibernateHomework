package interfaces;

import javax.persistence.Entity;
import javax.persistence.Table;

@FunctionalInterface
public interface PeoplesActionWhenMatchBegins {

    void beginActivityDuringTheMatch();

}
