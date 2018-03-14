Grails app blog

create the local mysql docker container using this command

docker run -d --name blog-db -e MYSQL_ROOT_PASSWORD=root+1 -e MYSQL_DATABASE=blog -e MYSQL_USER=blog-app -e MYSQL_PASSWORD=blog+1 -p 3306:3306 -d mysql:latest