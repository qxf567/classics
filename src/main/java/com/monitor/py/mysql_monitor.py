#!/usr/bin/python
# -*- coding: UTF-8 -*-
import MySQLdb
db = MySQLdb.connect("localhost","root","123456","treasure_online" )
cursor = db.cursor()
cursor.execute("SELECT * from epurse")
data = cursor.fetchone()
print data
db.close()