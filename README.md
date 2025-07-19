# 🚀 Job4j Design Patterns & Architecture Project
**Проект для глубокого изучения ключевых концепций Java-разработки**  
![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk)
![Maven](https://img.shields.io/badge/Build-Maven-blue?logo=apache-maven)
![GitHub](https://img.shields.io/github/license/T8671/job4j_design)
Проект содержит практические реализации паттернов проектирования, алгоритмов и архитектурных решений, изученных в ходе курса Job4j.
## 🔍 Содержание проекта
- Реализации 23 паттернов проектирования (GoF)
- Примеры SOLID-принципов
- Архитектурные решения для enterprise-приложений
- Тестовые задания и алгоритмические решения
- Демонстрация работы GC и управления памятью
## 📚 Программа курса
### 1. 🧩 Структуры данных и алгоритмы
```java
// Пример реализации бинарного дерева
public class TreeNode<T> {
    private T value;
    private TreeNode<T> left;
    private TreeNode<T> right;
}
```
- Анализ сложности алгоритмов (Big O)
- Коллекции: списки, стеки, очереди, хэш-таблицы
- Деревья и графы
- Алгоритмы сортировки и поиска
### 2. 💾 Ввод-вывод
```java
// Чтение файла с помощью NIO
Path path = Paths.get("data.txt");
List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
```
- Работа с файлами (NIO.2)
- Сериализация/десериализация
- Сетевые сокеты
- Буферизация и каналы
### 3. 🗃 SQL, JDBC
```sql
-- Пример оптимизированного запроса
SELECT e.name, d.name 
FROM employees e
JOIN departments d ON e.dep_id = d.id
WHERE e.salary > 100000;
```
- Реляционные базы данных (PostgreSQL)
- Транзакции и ACID
- Connection pooling (HikariCP)
- ORM vs чистый JDBC
### 4. 🧹 Garbage Collection
| GC Алгоритм | Use Case          | Плюсы               |
|-------------|-------------------|---------------------|
| G1          | Большие heap      | Предсказуемые паузы |
| ZGC         | Low-latency       | Паузы < 1ms         |
| Shenandoah  | Balanced workload | Параллельная сборка |
- Типы ссылок (Strong, Soft, Weak, Phantom)
- Профилирование памяти (VisualVM)
- Оптимизация работы с памятью
### 5. 🏛 Чистая архитектура
```
  Presentation → Business → Persistence
        ↑                ↓
       DTOs           Domain Model
```
- Принципы CLEAN и Hexagonal Architecture
- DDD (Domain-Driven Design)
- Микросервисы vs Монолит
- Event Sourcing и CQRS
### 6. 🧠 Алгоритмы на собеседовании
```java
// Решение задачи Two Sum
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[] { map.get(complement), i };
        }
        map.put(nums[i], i);
    }
    return new int[0];
}
```
- Решение задач с LeetCode/HackerRank
- Оптимизация кода под ограничения
- Системный подход к whiteboard-интервью
- Анализ временной и пространственной сложности

---
