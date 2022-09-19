FROM node:14 as builder
WORKDIR /app
COPY package.json ./
RUN npm install
COPY . .
RUN npm run build-lib
RUN npm run build

FROM bitnami/nginx
COPY --from=builder /app/dist/corporate-portal/index.html /app
COPY --from=builder /app/dist/* /app/bmonlinebusiness

COPY nginx.conf /opt/bitnami/nginx/conf/server_blocks/web-app.conf