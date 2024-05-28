package com.example.SteamClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SteamCloneApplication {
	public static void main(String[] args) {
		SpringApplication.run(SteamCloneApplication.class, args);
	}
}


/*
Принципал - это пользователь, вошедший в систему в данный момент.
Извлекается через SecurityContext, который привязан к текущему потоку и, как таковой, он также привязан к текущему запросу и его сеансу.
SecurityContextHolder.getContext() внутренне получает текущую реализацию SecurityContext через ThreadLocal переменную.
Поскольку запрос привязан к одному потоку, вы получите контекст текущего запроса.
Для упрощения можно сказать, что SecurityContext находится в сеансе и содержит пользователя / принципала и роли / полномочия.

@ElementCollection отличается от @OneToMany: целевые объекты не могут быть запрошены, сохранены или объединены независимо от их родительского объекта.
Поскольку они сильно зависят от родительского объекта. В случае @OneToMany дочерняя сущность может быть независимо запрошена, сохранена или объединена.

@PrePersist используют, чтобы отметить метод, который должен быть выполнен до сохранения объекта (т. Е. До его сохранения в базе данных).

@PathVariable можно использовать для обработки переменных шаблона в сопоставлении URI запроса и установки их в качестве параметров метода.

@RequestParam используют для извлечения параметров запроса, параметров формы и даже файлов из запроса.

 */