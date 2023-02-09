                                                    # The check() is used to extact  Labels 
def check(z):
    if z[0] == '@':
        if z[1].isupper():
            return True
        elif z[1] == 'R'or z[1].islower():
            return True
    elif z[0] == '(':
        return True
with open("white.asm") as file:                     # opeing a white.asm
    with open('symbolsandvariables.asm', 'w') as b: #creating the above file 
        a = file.readlines()                        #reading lines 
        for i in a:
            if check(i) :                           #calling the fuction check
                    b.write(i)                      #writing on to the file 
                    
                    
                    
                    
                    