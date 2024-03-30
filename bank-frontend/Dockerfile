FROM node:20.11.0 as angular
LABEL maintainer="Jakex01"
ENV PORT 4200
EXPOSE 4200

WORKDIR /app
COPY package*.json ./

RUN npm install -g @angular/cli && npm install

COPY . .

RUN ng build --configuration production

FROM nginx:alpine

COPY --from=angular /app/dist/bank-frontend /usr/share/nginx/html

CMD ["nginx", "-g", "daemon off;"]
