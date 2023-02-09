// Program: Sum1ToN (R0 represents N)
// Computes R1 = 1 + 2 + 3 + ... + R0
// Usage: put a value >= 1 in R0
// i = 1
@i
M=1
// sum = 0
@sum
M=0
(LOOP)
// if (i > R0) goto STOP
@i
D=M
@R0
D=D-M
@STOP
D;JGT
// sum = sum + i
@sum
D=M
@i
D=D+M
@sum
M=D
// i = i + 1
@i
M=M+1
// goto LOOP
@LOOP
0;JMP
