package geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTests {

    @Test
    void testGeoServiceByIp() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        assertEquals(Country.RUSSIA, geoService.byIp("172.0.32.11").getCountry());
        assertEquals("Moscow", geoService.byIp("172.0.32.11").getCity());

        assertEquals(Country.USA, geoService.byIp("96.44.183.149").getCountry());
        assertEquals("New York", geoService.byIp("96.44.183.149").getCity());
    }
}
