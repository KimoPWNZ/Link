import java.time.LocalDateTime;
import java.util.UUID;

public class Link {
    private String longUrl;
    private String shortUrl;
    private UUID userId;
    private int clickLimit;
    private int clickCount;
    private LocalDateTime expirationTime;

    public Link(String longUrl, UUID userId, int clickLimit, LocalDateTime expirationTime) {
        this.longUrl = longUrl;
        this.shortUrl = generateShortUrl(longUrl);
        this.userId = userId;
        this.clickLimit = clickLimit;
        this.clickCount = 0;
        this.expirationTime = expirationTime;
    }

    private String generateShortUrl(String longUrl) {
        return "https://tinyurl.com/" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String getLongUrl() {
        return longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getClickLimit() {
        return clickLimit;
    }

    public int getClickCount() {
        return clickCount;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void incrementClickCount() {
        this.clickCount++;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationTime);
    }

    public boolean isLimitReached() {
        return clickCount >= clickLimit;
    }
}