Инструкция пользователя для сервиса сокращения ссылок
Введение
Сервис сокращения ссылок позволяет пользователям создавать короткие ссылки на длинные URL, управлять ими и отслеживать их статистику. Пользователи могут создавать уникальные короткие ссылки, устанавливать лимиты переходов и срок действия каждой ссылки.

Установка и запуск в IntelliJ IDEA
Получите архив с кодом проекта и сохраните его на вашем компьютере.

Импорт проекта в IntelliJ IDEA
Откройте IntelliJ IDEA и выберите Import Project.
Укажите путь к папке с загруженным проектом и нажмите OK.
Выберите Import project from external model.
Следуйте инструкциям на экране, чтобы завершить импорт.
Настройте конфигурационный файл: В файле config.properties вы можете задать максимальное время жизни ссылок и лимит переходов по умолчанию. При необходимости отредактируйте параметры:
properties
maxLinkLifespan=24 #Максимальное время жизни короткой ссылки в часах.
defaultClickLimit=100 #Лимит переходов по умолчанию.
Запустите программу: Используйте команду для запуска вашего Java приложения.

Использование сервиса
При запуске программы пользователь увидит меню с доступными функциями. Ниже описаны функции, которые можно использовать.

Меню действий
Сокращение URL

Выберите опцию 1.
Введите длинный URL, который вы хотите сократить.
Введите лимит переходов. Если вы хотите использовать значение по умолчанию, введите 0.
Введите время жизни ссылки в часах. Если хотите использовать значение по умолчанию, введите 0.
Программа сгенерирует короткий URL и отобразит его.
Переход по короткой ссылке

Выберите опцию 2.
Введите короткую ссылку.
Программа проверит, доступна ли ссылка.
Если она доступна, программа откроет оригинальный URL в браузере.
Если ссылка недоступна (лимит переходов достигнут или срок действия истек), будет выведено соответствующее сообщение.
Удаление ссылки

Выберите опцию 3.
Введите короткую ссылку, которую хотите удалить.
Программа проверит, являетесь ли вы создателем ссылки.
Если вы есть пользователем, который создал ссылку, она будет удалена.
Если вы не являетесь создателем, будет выдано сообщение о том, что удаление недоступно.
Выход

Выберите опцию 4, чтобы завершить программу.

Примечания
Все ссылки уникальны: Каждый пользователь получает уникальные короткие ссылки даже на один и тот же длинный URL.
Ограничение по времени жизни: Срок действия каждой ссылки ограничен значением, указанным пользователем или максимальным значением, заданным в конфигурации.
Автоматическое удаление: Ссылки автоматически удаляются, если достигнут лимит переходов или истекло время жизни.
Уведомления: Пользователи получают уведомления, если ссылка становится недоступной.
Советы по использованию
Для удобства храните сгенерированные короткие ссылки в отдельном документе, чтобы иметь к ним доступ в будущем.
Убедитесь, что настройки в config.properties соответствуют вашим требованиям, чтобы избежать случаев, когда ссылки становятся недоступными слишком быстро.