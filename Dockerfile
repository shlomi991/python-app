FROM python:3.7

ADD . /app/

EXPOSE 5000

CMD ["python", "/app/main.py"]
