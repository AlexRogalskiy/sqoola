curl "http://localhost:8080/quotes-reactive-paged?page=0&size=50"

curl -H "Accept: text/event-stream" "http://localhost:8080/quotes-reactive"
