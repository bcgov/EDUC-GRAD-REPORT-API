FROM artifacts.developer.gov.bc.ca/docker-remote/maven:3.8.7-openjdk-18 as build
WORKDIR /workspace/app
COPY api/pom.xml .
COPY api/src src
COPY api/lib lib
RUN mvn package -DskipTests
RUN mv target/*-client.jar target/educ-grad-report-api-client.jar.bkp && mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM artifacts.developer.gov.bc.ca/docker-remote/openjdk:18.0.2.1-jdk-oracle
RUN useradd -ms /bin/bash spring && mkdir -p /logs && chown -R spring:spring /logs && chmod 755 /logs
USER spring
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-Duser.name=EDUC_GRAD_REPORT_API","-Xms1300m","-Xmx1300m","-noverify","-XX:TieredStopAtLevel=1","-XX:+UseParallelGC","-XX:MinHeapFreeRatio=20","-XX:MaxHeapFreeRatio=40","-XX:GCTimeRatio=4","-XX:AdaptiveSizePolicyWeight=90","-XX:MaxMetaspaceSize=300m","-XX:ParallelGCThreads=1","-Djava.util.concurrent.ForkJoinPool.common.parallelism=1","-XX:CICompilerCount=2","-XX:+ExitOnOutOfMemoryError","-Djava.security.egd=file:/dev/./urandom","-Dspring.backgroundpreinitializer.ignore=true","-cp","app:app/lib/*","ca.bc.gov.educ.grad.report.api.ReportApiApplication"]
