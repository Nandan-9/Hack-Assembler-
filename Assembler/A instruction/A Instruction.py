symbol_table={'R0':'0','R1':'1','R2':'2','R3':'3','R4':'4','R5':'5',
'R6':'6','R7':'7','R8':'8','R9':'9','R10':'10','R11':'11',
'R12':'12','R13':'13','R14':'14','R15':'15','SP':'0',
'LCL':'1','ARG':'2','THIS':'3','THAT':'4','SCREEN':'16384',
'KBD':'24576'}

value_table = []
variables={}

#The folowing block  gives the line number of the corresponding  labels
def line_index(x,data):
    count = 0
    for i in data : 
        if (i[0]) == '(':
            if x == i[1:-2]:
                value_table.append(count)
                return True
        else :
            count += 1 

with open("white.asm") as rawfile:
    with open("ainstr.asm","w") as b:

        file = rawfile.readlines()
         # Initializing bt-cound= 15 for variables
        bit_count = 15

        for l in file :

            if l[0]==('@'):

                # For checking pre-defined sysmbols
                if l[1:-1] in symbol_table.keys():
                        x = l[1:-1]
                        value_table.append(symbol_table[x])

                #  The line number of the corresponding  labels
                elif line_index(l[1:-1],file) == True :
                    continue
                
                # For @constant a instrcutions
                elif l[1:-1].isdigit() :
                    k = "{}".format(l[1:-1])
                    value_table.append(k)
                
                # For Variables 
                elif l[1:-1] not in variables.keys():
                    bit_count += 1
                    value_table.append(bit_count)
                    variables[l[1:-1]]=bit_count
                
                else:
                    value_table.append(variables[l[1:-1]])

       # wrinting on to file
        for i in value_table:
            nullcode = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
            x = int(i)
            a = str(bin(x).replace("0b",""))
            for i in range(-1,-(len(a)+1),-1):
                nullcode[i]=a[i]
            instruction = ""
            for i in (nullcode):
                instruction += str(i)
            print(instruction)
            b.write(instruction+'\n')