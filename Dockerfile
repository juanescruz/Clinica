FROM ubuntu:latest
LABEL authors="alejandroarias"

ENTRYPOINT ["top", "-b"]