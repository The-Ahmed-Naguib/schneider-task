FROM node:14

WORKDIR /src

RUN npm install -f -g @angular/cli@10
ADD package.json package.json
RUN npm install -f
ADD . /src


ENTRYPOINT ng serve --host 0.0.0.0 --port 8080 --disableHostCheck=true --baseHref=/vacation-portal/ --servePath=/vacation-portal/ --deployUrl=/vacation-portal/ --publicHost=http://0.0.0.0/vacation-portal/
