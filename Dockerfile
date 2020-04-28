###
# Copyright © 2020  Atos Spain SA. All rights reserved.
# This file is part of SEAL API Gateway Client (SEAL APIGW-cl).
# SEAL APIGW-cl is free software: you can redistribute it and/or modify it under the terms of EUPL 1.2.
# THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
# INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, 
# IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
# DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
# See README file for the full disclaimer information and LICENSE file for full license information in the project root.
###

FROM openjdk:8-jdk-alpine
MAINTAINER Atos
VOLUME /tmp

ADD ./target/cl-0.0.1.jar seal-apigwcl.jar
RUN sh -c 'touch /seal-apigwcl.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /seal-apigwcl.jar" ]

EXPOSE 8053

