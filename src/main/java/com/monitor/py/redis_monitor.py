#!/usr/bin/python
#coding=utf-8 
import redis
r = redis.Redis(host='localhost', port=22121)

# ------------------------------------------- 
#info = r.info() 
#for key in info: 
#  print "%s: %s" % (key, info[key])

r.set("foo","foo success")
print(r.get("foo"))

print("delete foo:"+str(r.delete("foo")))



r.hset("dic_name","a1","aa")
r.hset("dic_name","a1","aa")
print(r.hget("dic_name","a1"))
print(r.hgetall("dic_name"))
print(r.hlen("dic_name"))
print(r.hkeys("dic_name"))





r.lpush("list_name",2)
r.lpush("list_name",3,4,5)
print(r.llen("list_name"))
print(r.lindex("list_name",1))



print(r.type("list_name"))


#print '\ndbsize: %s' % r.dbsize()

#print "ping %s" % r.ping()