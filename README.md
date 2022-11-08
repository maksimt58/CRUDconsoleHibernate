# CRUD Console

## Описание
Реализация консольного CRUD приложение, которое имеет следующие сущности:

Writer  
Post  
Label  
PostStatus  

В качестве хранилища данных используется база данных PostgreSQL. 

Пользователь в консоли имеет возможность создания, получения, редактирования и удаления данных.

Слои:  
model - POJO классы  
repository - классы, реализующие доступ к текстовым файлам  
service - классы, для обработки запросов контроллера и вызова методов БД  
controller - обработка запросов от пользователя  
view - все данные, необходимые для работы с консолью  

## Инструкция по развертыванию
1. Заходим в репозиторий на GitHub по ссылке https://github.com/maksimt58/CRUDconsoleHibernate.git
2. В верхнем правом углу нажимаем зеленую кнопку Code
3. Затем в выпадающем меню выбираем Download ZIP
4. Распаковываем архив в директорию с проектами
5. Открываем распакованный проект с помощью IntelliJ IDEA
6. Идем по пути src -> main -> java -> com.examples.crud -> CrudConsoleSolution
7. Запускаем класс CrudConsoleSolution
8. Работаем