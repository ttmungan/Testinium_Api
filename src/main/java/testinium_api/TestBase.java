package testinium_api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TestBase {
    protected RequestSpecification spec01;
    protected RequestSpecification spec02;

    @Before
    public void setUp01() {
        spec01 = new RequestSpecBuilder().
                setBaseUri("http://ergast.com/api/f1").
                build();
    }

    @Before
    public void setUp2() {
        spec02 = new RequestSpecBuilder().
                setBaseUri("http://ergast.com/api/f1/2008/5.json").
                build();
    }


}
