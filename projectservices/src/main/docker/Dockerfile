FROM openjdk:8-jre-slim

# 环境变量
ENV WORK_PATH /home/project/bccm
ENV APP_NAME @project.build.finalName@.@project.packaging@
ENV APP_VERSION @project.version@

EXPOSE 13600

#USER
#USER user:group

#VOLUME
VOLUME ["/home/project", "/tmp/data"]

#ADD

#COPY
COPY $APP_NAME $WORK_PATH/

#LABEL
#STOPSIGNAL
#ARG
#ONBUILD

# WORKDIR
WORKDIR $WORK_PATH

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone

# ENTRYPOINT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"]

CMD ["-jar", "-Xmx1024m", "@project.build.finalName@.@project.packaging@"]