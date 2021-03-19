# Customer Service
echo 'Customer Service - Build started'
cd ./customer-service && sh gradlew clean bootBuildImage && cd ..
echo 'Customer Service - Build completed'

# Review Service
echo 'Review Service - Build started'
cd ./review-service && sh gradlew clean bootBuildImage && cd ..
echo 'Review Service - Build completed'

# Apollo Gateway
echo 'Apollo Gateway - Build started'
docker build -t rbiedrawa/apollo-gateway:1.0.0 ./gateway
echo 'Apollo Gateway - Build completed'