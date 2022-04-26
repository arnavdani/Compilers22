#Arnav Dani
#4/25/22
#Exercise 5 - determining whether an inputted number is either even or odd

.data
prompt: .asciiz "Enter a number\n"
evenmsg: .asciiz "Number is even"
oddmsg: .asciiz "Number is odd"
.text
.globl e5
e5:
la $a0, prompt	#loads and displays message
li $v0, 4
syscall

li $v0, 5	#reads user input and stores value in $t0
syscall
move $t0, $v0

li $t1, 2	#divides number by 2 and stores remainder in $a1
div $t0, $t1
mfhi $a1

beq $a1, 0, even #if remainder after dividing by r is 0, even - if 1, odd

la $a0, oddmsg 	#displays odd message
li $v0, 4
syscall

j end	#jumps to end which houses termination sequence

even: la $a0, evenmsg	#uses label for even for jump from conditional
li $v0, 4		#prints even message
syscall

end:			#terminates the program
li $v0, 10
syscall
