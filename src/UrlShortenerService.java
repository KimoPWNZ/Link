import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UrlShortenerService {
    private final Map<String, Link> linkMap = new HashMap<>();
    private final Config config;

    public UrlShortenerService(Config config) {
        this.config = config;
    }

    public String shortenUrl(String longUrl, UUID userId, int clickLimit, int lifespanInHours) {
        lifespanInHours = Math.min(lifespanInHours, config.getMaxLinkLifespan());
        int effectiveClickLimit = Math.max(clickLimit, config.getDefaultClickLimit());
        LocalDateTime expirationTime = LocalDateTime.now().plusHours(lifespanInHours);

        Link link = new Link(longUrl, userId, effectiveClickLimit, expirationTime);
        linkMap.put(link.getShortUrl(), link);
        return link.getShortUrl();
    }

    public Link getLink(String shortUrl) {
        return linkMap.get(shortUrl);
    }

    public void cleanExpiredLinks() {
        linkMap.values().removeIf(Link::isExpired);
    }

    public void incrementClickCount(String shortUrl) {
        Link link = getLink(shortUrl);
        if (link != null) {
            link.incrementClickCount();
        }
    }

    public boolean isLinkAvailable(Link link) {
        return !link.isLimitReached() && !link.isExpired();
    }
}