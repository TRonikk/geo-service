package sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MessageSenderImplTests {

    private GeoService geoService;
    private LocalizationService localizationService;
    private MessageSender messageSender;

    @BeforeEach
    void setUp() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void testMessageSenderForRussianIp() {
        String russianIp = "172.0.32.11";
        when(geoService.byIp(russianIp)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);

        assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    void testMessageSenderForAmericanIp() {
        String americanIp = "96.44.183.149";
        when(geoService.byIp(americanIp)).thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));
        when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, americanIp);

        assertEquals("Welcome", messageSender.send(headers));
    }
}
