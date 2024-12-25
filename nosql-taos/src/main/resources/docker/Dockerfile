FROM reg.int.it2000.com.cn/library/adoptopenjdk/openjdk8:latest

# setting time zone
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# Add the service itself
ARG JAR_FILE

COPY ${JAR_FILE} /usr/local/myapp.jar

COPY ./libtaos.so.3.3.4.8  /usr/lib/libtaos.so

ENV JAVA_OPTS=$JAVA_OPTS

ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /usr/local/myapp.jar"]