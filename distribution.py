import math
import sys
import threading
import time
import resource

resource.setrlimit(resource.RLIMIT_STACK, (resource.RLIM_INFINITY, resource.RLIM_INFINITY))
threading.stack_size(1024000*100)

sys.setrecursionlimit(2**24)


n=1
thr=8
try:

    for i in range(1,len(sys.argv),2):
        if(sys.argv[i]=='-n'):
            n=int(sys.argv[i+1])
        elif(sys.argv[i]=='-t'):
            thr=int(sys.argv[i+1])
        else:
            raise("invalid arguments")
except:
    print("invalid arguments")
    sys.exit()


def rangeFact(n,m=1):
    if(n<2):
        return 1
    if(n<=m):
        return n
    return n*rangeFact(n-1,m)

def rangeFactIter(n,m=1):
    res=1
    if(m<2):
        m=1
    for i in range(m,n+1,1):
        res=res*i
    return res

def distribution(n,threads):
    res=[]
    for i in range(threads,-1,-1):
        res.append(math.ceil(n*(i/threads)))
    return res


def run(n,m=1,res=[]):
    res.append(rangeFact(n,m))
    


t1=time.clock()
res=[]
threads=[]
params=distribution(n,thr)


for i in range(0,len(params)-1,1):
    threads.append(threading.Thread(target=run,args=(params[i],(params[i+1]+1),res,)))
    threads[i].start()

for i in range(0,len(params)-1,1):
    threads[i].join()

bashRes=1
for i in range(0,len(params)-1,1):
    bashRes=bashRes*res[i]
t2=time.clock()

print(t2-t1)


t1=time.clock()
run(n)
t2=time.clock()
print(str(t2-t1) + " standard")
