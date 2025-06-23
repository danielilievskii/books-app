FROM node:20-alpine AS build
WORKDIR /app

COPY package.json package-lock.json ./
RUN npm install --frozen-lockfile

COPY . .

ARG VITE_BACKEND_HOST
ENV VITE_BACKEND_HOST=${VITE_BACKEND_HOST}

ARG VITE_BACKEND_PORT
ENV VITE_BACKEND_PORT=${VITE_BACKEND_PORT}

ARG VITE_BACKEND_BASE_PATH
ENV VITE_BACKEND_BASE_PATH=${VITE_BACKEND_BASE_PATH}

RUN npm run build

FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
