
comp={'1':{'M': [1, 1, 0, 0, 0, 0], '!M': [1, 1, 0, 0, 0, 1], '-M': [1, 1, 0, 0, 1, 1], 
         'M+1': [1, 1, 0, 1, 1, 1], 'M-1': [1, 1, 0, 0, 1, 0], 'D+M': [0, 0, 0, 0, 1, 0], 
         'D-M': [0, 1, 0, 0, 1, 1], 'M-D': [0, 0, 0, 1, 1, 1], 'D&M': [0, 0, 0, 0, 0, 0], 
         'D|M': [0, 1, 0, 1, 0, 1]},
      '0':{'0': [1, 0, 1, 0, 1, 0], '1': [1, 1, 1, 1, 1, 1], '-1': [1, 1, 1, 0, 1, 0], 
            'D': [0, 0, 1, 1, 0, 0], 'A': [1, 1, 0, 0, 0, 0], '!D': [0, 0, 1, 1, 0, 1],
            '!A': [1, 1, 0, 0, 0, 1], '-D': [0, 0, 1, 1, 1, 1], '-A': [1, 1, 0, 0, 1, 1],
            'D+1': [0, 1, 1, 1, 1, 1], 'A+1': [1, 1, 0, 1, 1, 1], 'D-1': [0, 0, 1, 1, 1, 0],
            'A-1': [1, 1, 0, 0, 1, 0], 'D+A': [0, 0, 0, 0, 1, 0], 'D-A': [0, 1, 0, 0, 1, 1],
            'A-D': [0, 0, 0, 1, 1, 1], 'D&A': [0, 0, 0, 0, 0, 0], 'D|A': [0, 1, 0, 1, 0, 1]}}
dest = {0:[0,0,0],'M':[0,0,1],'D':[0,1,0],'MD':[0,1,1],'A':[1,0,0],'AM':[1,0,1],
        'AD':[1,1,0],'AMD':[1,1,1]}
jump = {0:[0,0,0],'JGT':[0,0,1],'JEQ':[0,1,1],
        'JGE':[0,1,1],'JLT':[1,0,0],'JNE':[1,0,0],
        'JLE':[1,1,0],'JMP':[1,1,1]} 
def fstore(x):
    a = x.replace(",","")
    instructions = " "
    for i in a[1:-1]:
        instructions += i
    print(instructions)
def store(x,y,z,p): 
    if y == 'd':
        for i,j in zip(range(10,13),range(3)):
            p[i] = x[j]
        return p
    elif y == 'c':
        p[3] = z
        for i,j in zip(range(4,9),range(6)):
            p[i] = x[j]
        return p
    elif y == 'j':
        for i,j in zip(range(13,17),range(3)):
            p[i] = x[j]
        return p
def instruction(des,com,jum,num,code):
    x=des
    y=com
    num = num
    code = code
    z=jum
    inuse = []
    def binary (num,code):
        global inuse
        if num == 0:
            return inuse
        value = dest[x]
        inuse = store(value,'d',0,code)
        value = jump[z]
        inuse = store(value,'j',0,code)
        a = lambda a: 0 if y in comp['0'] else 1
        if a(y) == 0:
                value = comp['0'][y]
                inuse = store(value,'c',a(y),code)
        else:   
                value = comp['1'][y]
                inuse = store(value,'c',a(y),code)   
        return binary(num-1,inuse)
    return(str(binary(num,code)))   
     
with open("white.asm") as rawfile:
                variables = []
                file = rawfile.readlines()
                binary_code = [1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0]
                for l in file :
                    if l[0] =='@' or l[0] == '(':# when doing stand alone code call A instruction from here 
                          continue 
                    else:
                        b = l.replace('='," ")
                        c = b.replace(';'," ")
                        d = c.split(" ")
                        if len(d) == 3:
                            fstore(instruction(d[0],d[1],d[2],3,binary_code))
                            
                        elif len(d) == 2:
                            if '=' in l:
                                fstore(instruction(d[0],d[1][0:-1],0,1,binary_code))
                                
                            else:
                                fstore(instruction(0,d[0],d[1][0:-1],1,binary_code))
                                
                        elif len(d) == 1:
                            fstore(instruction(0,d[0],0,1,binary_code))
                            
