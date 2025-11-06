# WP Lab 1 - Full Fixed
Run:
```
mvn spring-boot:run
```
Open:
- Login: http://localhost:8080/login  (user/pass: `student` / `student`)
- After login you get redirected to /listChefs.
Protected routes: /listChefs, /dish, /chefDetails (guarded by AuthFilter).
