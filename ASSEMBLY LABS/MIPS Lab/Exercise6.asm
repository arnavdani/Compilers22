#Arnav Dani
#4/25/22
#Exercise 6 - implement a program that prints numbers in a range 
#		seperated by steps

.data
pLow: .asciiz "Enter Low Value\n"
pHigh: .asciiz "Enter High Value\n"
pStep: .asciiz "Enter Step Amount\n"
errmsg: .asciiz "\nLow greater than high"
nline: .asciiz "\n"
.text
.globl e6
e6:
#first step is to read values
la $a0, pLow	#asking for low value
li $v0, 4
syscall

li $v0, 5	#stores low in $t0
syscall
move $t0, $v0

la $a0, pHigh	#asking for high value
li $v0, 4
syscall

li $v0, 5	#stores high in $t1
syscall
move $t1, $v0

la $a0, pStep	#asking for step value
li $v0, 4
syscall

li $v0, 5	#stores step in $t2
syscall
move $t2, $v0

bgt $t0, $t1, error

jal newline 	#leaving a line gap for visual clarity

move $a0, $t0	#assuming no error, low is guaranteed to be a part of the answer
li $v0, 1	# so it should be printed
syscall 
jal newline

blt $t0, $t1, loop

loop:
addu $t3, $t0, $t2	#puts low + step into t3
bgt $t3, $t1, end	#if t3 > high, end program without printing t3
move $t0, $t3		#else, set t0 to t3 and print the current value of t0

move $a0, $t0		#prints the current value of t0 by loading into a0 and syscall
li $v0, 1		
syscall

jal newline		#adds a new line between the numbers

j loop			#repeats the loop until the sum is > high

error:			#error thrown when low > high
la $a0, errmsg
li $v0, 4
syscall
j end

end:			#terminates the program
li $v0, 10
syscall

newline:
la $a0, nline		#going to a new line
li $v0, 4
syscall
jr $ra