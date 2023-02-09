# opening the file in read mode
with open('MII.asm', 'r') as a:
    with open('white.asm', 'w+') as b:
        # creating a file named as white.asm
        i=0
        m=a.readlines() 
        #reading lines 
        n=len(m) 
        while i<len(m):        
            l=m[i]
            l=l.replace(' ','')
            # replacing white spaces in line 
            if  l[0]=='\n' or l[0]=='/':
                #removing empty line and comments 
                pass
            else:
                if '/' in l:
                #removing inline comments 
                    x=l.index('/')
                    l1=''
                    for j in range(x):
                        l1=l1+l[j]
                    l=l1+'\n'
                    
                if i==len(m)-1:
                #removing end characters 
                    l.strip()
                b.write(l)
                #writng on to file  
            i+=1
            
            
            