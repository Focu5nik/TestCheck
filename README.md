1. Убедитесь что установлен JDK 21, или усатновите его.
2. Склонируйте проект и переключитесь на ветку feature/entry-core.
3. Откройте консоль cmd Windows.
4. Убедитесь, что имена в пути не содержат пробелов и спец символов.
5. Перейдите в корневую папку проекта (внутри которой ./src).
6. Соберите пути к файлам для компиляции командой "dir /s /b src\*.java > sources.txt".
7. Скомпелируйте файлы классов в байткод коммандой "javac @sources.txt".
8. Запустите программу (аргументы опциональны) пример: "java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java 3-1 2-5 5-1 discountCard=1111 balanceDebitCard=100".
