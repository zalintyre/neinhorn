FROM ubuntu:20.04

ARG NEINHORN_VERSION

WORKDIR /work

RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work
COPY --chown=1001:root neinhorn-${NEINHORN_VERSION}-runner /work/application

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
