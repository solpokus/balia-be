# balia-be
This project is for BALIA

## Getting started

## Development

Before you can build this project, you must install docker.
Run step configuration :

```bash
	docker build --tag=balia-be:latest .
	## docker build --target build -t balia-be:latest .
	docker run -p 8082:8080 balia-be:latest
```