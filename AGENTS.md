# Repository Guide

## Project overview

- This is a Java 8 Maven application built with Spring Boot 2.0, Spring MVC,
  Thymeleaf, and MyBatis.
- Java sources live under `src/main/java/com/wxthxy/zj`. Keep the existing
  controller/service/DAO layering when adding application behavior.
- MyBatis mapper XML files live under `src/main/resources/mybatis/mapper` and
  should stay consistent with their DAO interfaces.
- Server-rendered pages are in `src/main/resources/templates`; application
  JavaScript and styles are in `src/main/resources/static`.
- The database bootstrap script is `sql/zj.sql`.

## Development conventions

- Preserve Java 8 compatibility; do not introduce APIs or syntax from newer
  Java releases.
- Follow the package and naming patterns in the surrounding code. Prefer small,
  focused changes over broad modernization of this legacy application.
- When changing a DAO method, update its mapper XML and all callers together.
- Keep credentials and environment-specific values out of new source files.
  Runtime database settings can be supplied through the environment variables
  already referenced by `application.yml`.
- Do not edit vendored Bootstrap, jQuery, or source-map files unless the task
  specifically requires updating those assets.

## Validation

- Run `mvn test` for Java changes.
- Tests load the Spring application context and may require the MySQL schema
  from `sql/zj.sql`; document the limitation if a database is unavailable.
- For template, CSS, or JavaScript changes, also start the application and
  manually verify the affected page when the required database is available.
- Do not commit generated Maven output such as `target/`.

## Running locally

- The application listens on port `8088` by default.
- `start.sh` demonstrates the Docker-based MySQL and application setup, but it
  contains interactive setup notes; review the commands before running it.
