#Arnav Dani
#4/25/22
#Guessing Game - computer generates a random number and user guesses
#		program gives feedback until user gets correct answer

.data
low: .asciiz "The guess is too low\n"
high: .asciiz "The guess is too high\n"
corr: .asciiz "The guess is correct!"
guess: .asciiz "Enter a guess\n"

.text
.globl main

randomNum: 
li $a1, 100  #sets range to 0-100
li $v0, 42  #generates the random number.
move $t0, $v0 #moves random number to t0
syscall

main:
jal guessNum 	#using label and jump returns to retrieve a guess
bgt $t0, $t1, greater	#if the number is greater than the guess, say so using greater label
blt $t0, $t1, lessthan	#if the number is less than the guess, say so using less than label
j equal			#if the number is equal, jump to equal to print so and end program

guessNum:	#takes in user guesses
la $a0, guess
li $v0, 4	#prints guess message
syscall

li $v0, 5
syscall		#read guess and moves it to t1
move $t1, $v0

jr $ra		#jumps back o line 23

greater:
la $a0, low
li $v0, 4	#if the number is greater than the guess, says so
syscall

j main		#go back to the start of main to guess again and repeat

lessthan:
la $a0, high
li $v0, 4	#if the number is less than the guess, says so
syscall

j main		#go back to the start of main to guess again and repeat

equal:
la $a0, corr
li $v0, 4	#if the two numbers are equal, say so and terminate the program
syscall

li $v0, 10
syscall



