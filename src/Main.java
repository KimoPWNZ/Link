import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Config config;
        try {
            config = new Config("config.properties");
        } catch (IOException e) {
            System.err.println("Ошибка загрузки конфигурационного файла: " + e.getMessage());
            return;
        }

        UrlShortenerService urlShortenerService = new UrlShortenerService(config);
        User user = new User();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Сократить URL");
            System.out.println("2. Перейти по короткой ссылке");
            System.out.println("3. Удалить ссылку");
            System.out.println("4. Выход");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите длинный URL: ");
                    String longUrl = scanner.nextLine();
                    System.out.print("Введите лимит переходов (0 для значения по умолчанию): ");
                    int clickLimit = scanner.nextInt();
                    if (clickLimit == 0) clickLimit = config.getDefaultClickLimit();
                    System.out.print("Введите время жизни ссылки в часах (0 для значения по умолчанию): ");
                    int lifespan = scanner.nextInt();
                    if (lifespan == 0) lifespan = config.getMaxLinkLifespan();
                    scanner.nextLine();

                    String shortUrl = urlShortenerService.shortenUrl(longUrl, user.getId(), clickLimit, lifespan);
                    System.out.println("Сокращенная ссылка: " + shortUrl);
                    break;

                case 2:
                    System.out.print("Введите короткую ссылку: ");
                    String inputShortUrl = scanner.nextLine();
                    Link existingLink = urlShortenerService.getLink(inputShortUrl);
                    if (existingLink != null && urlShortenerService.isLinkAvailable(existingLink)) {
                        urlShortenerService.incrementClickCount(inputShortUrl);
                        try {
                            Desktop.getDesktop().browse(new URI(existingLink.getLongUrl()));
                        } catch (URISyntaxException | IOException e) {
                            System.err.println("Ошибка перехода по ссылке: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Ссылка недоступна.");
                    }
                    break;

                case 3:
                    System.out.print("Введите короткую ссылку для удаления: ");
                    String shortUrlToDelete = scanner.nextLine();
                    Link linkToDelete = urlShortenerService.getLink(shortUrlToDelete);
                    if (linkToDelete != null && linkToDelete.getUserId().equals(user.getId())) {
                        urlShortenerService.getLink(shortUrlToDelete);
                        System.out.println("Ссылка удалена.");
                    } else {
                        System.out.println("Удаление недоступно. Вы не являетесь создателем ссылки или ссылка не найдена.");
                    }
                    break;

                case 4:
                    scanner.close();
                    return;

                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }
}