#!/usr/bin/python
# -*-coding:UTF-8 -*-
import requests
username = 'guest'
password = 'guest'
url = 'http://10.168.31.86:15672/api/queues/%2fx/message_refer0'
result = requests.get(url, auth=(username, password))  
print (result.json())