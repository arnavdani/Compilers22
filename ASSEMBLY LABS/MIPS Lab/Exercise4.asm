#Arnav Dani
#4/25/22
#Exercise 4 - adding and multiplying numbers

.data
msg1: .asciiz "Enter two numbers to add\n"
msg2: .asciiz "\nMultiplying the same two numbers\n"
.text
.globl e4
e4:

#addition
la $a0, msg1 #loads message
li $v0, 4    #displays message
syscall

li $v0, 5   #reads first num and moves to $t0
syscall
move $t0, $v0

li $v0, 5   #reads 2nd num and moves to $t1
syscall
move $t1, $v0

addu $a0, $t0, $t1 #adds the numbers, moves the sum to $a0, and prints
li $v0, 1
syscall

#multiplication
la $a0, msg2	#loads and displays message
li $v0, 4
syscall


mult $t0, $t1 	#multiplys the numbers and moves the product to $a0
mflo $a0

li $v0, 1	#prints $a0 value
syscall

li $v0, 10      #ends program
syscall
