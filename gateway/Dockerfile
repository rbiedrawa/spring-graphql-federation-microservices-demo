FROM node:15.12.0-alpine3.13
EXPOSE 4000

COPY . /gateway
WORKDIR gateway

RUN npm install --only=production

ENTRYPOINT [ "npm" ]
CMD [ "start" ]