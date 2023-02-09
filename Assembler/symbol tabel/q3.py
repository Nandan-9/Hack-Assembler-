# This program will create a symbol table from the already created table

symbol_table={'R0':'0','R1':'1','R2':'2','R3':'3','R4':'4','R5':'5',
'R6':'6','R7':'7','R8':'8','R9':'9','R10':'10','R11':'11',
'R12':'12','R13':'13','R14':'14','R15':'15','SP':'0',
'LCL':'1','ARG':'2','THIS':'3','THAT':'4','SCREEN':'16384',
'KBD':'24576'}
variable = {}
#the fucntion line_index gets the line number of the preceding line of the label 
def line_index(x,data):
    count = 0
    for i in data : 
        if (i[0]) == '(':
            if x == i[1:-2]:
                variable[x]=count
                return True
        else :
            count += 1 
# store value of variables and symbols 
def storing(x):
    variable[x]=symbol_table[x]

# openig white.asm file , creating symbolstable.txt file 
with open("white.asm") as rawfile:
    with open("symboltable.txt","w") as b:
        file = rawfile.readlines()

# assinging 'bit_count'= 15 for assigning the non defined variables there value 
        bit_count = 15
        for l in file :
            if l[0]==('@'):
                
                if l[1:-1] in symbol_table.keys():
                    pass
                # checking is the value in symbol_table
                    
                elif line_index(l[1:-1],file) == True :
                    continue
                # assigning the labels there line number 
                
                elif l[1:-1].isdigit() :
                    k = "{}".format(l[1:-1])
                # for dealing with the constant  values in A instruction
                
                elif l[1:-1] not in variable.keys():
                    bit_count += 1
                    variable[l[1:-1]]=bit_count
                #For assigning the variables there value , starting from 16(default value)
                
                else:
                    pass
                # else pass 


# writing on to the file 
        b.write("Symbols \t Values\n")
        for i,j in symbol_table.items():
            b.write("{:<15} {:<15}\n".format(i,j))
        for i,j in variable.items():
            if isinstance(j,int):
                j=str(j)
            b.write("{:<15} {:<15}\n".format(i,j))