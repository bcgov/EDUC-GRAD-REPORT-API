FROM maven:3-jdk-11 as build
WORKDIR /workspace/app

COPY api/grad/target/*.jar .
RUN mkdir -p target/dependency && cd target/dependency && jar -xf ../../*.jar

FROM openjdk:11-jdk
RUN useradd -ms /bin/bash spring && mkdir -p /logs && chown -R spring:spring /logs && chmod 755 /logs
USER spring
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Duser.name=EDUC_GRAD_REPORT_API","-Djava.security.egd=file:/dev/./urandom","-cp","app:app/lib/*","ca.bc.gov.educ.api.report.ReportApiApplication"]
