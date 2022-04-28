.text

j main

sleep:
	li $v0, 32 # to sleep
	li $a0, 1000 # sleep duration in ms
	syscall
	jr $ra

sleep0.4:
	li $v0, 32 # to sleep
	li $a0, 400 # sleep duration in ms
	syscall
	jr $ra

end:
	li $v0, 10
	syscall
	

c:

li $v0, 31 # to produce sound
li $a0, 60 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

d:

li $v0, 31 # to produce sound
li $a0, 62 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

e:

li $v0, 31 # to produce sound
li $a0, 64 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

f:

li $v0, 31 # to produce sound
li $a0, 65 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

g:

li $v0, 31 # to produce sound
li $a0, 68 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

g_long:

li $v0, 31 # to produce sound
li $a0, 67 #pitch (0-127)
li $a1, 1000 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

a:

li $v0, 31 # to produce sound
li $a0, 69 #pitch (0-127)
li $a1, 1000 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

bf:

li $v0, 31 # to produce sound
li $a0, 70 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

bi:

li $v0, 31 # to produce sound
li $a0, 71 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

c_hi:

li $v0, 31 # to produce sound
li $a0, 72 #pitch (0-127)
li $a1, 400 #duration in milliseconds
li $a2, 28 #instrument (0-127)
li $a3, 100 # volume (0-127)
syscall
jr $ra

main:
#playing the john cena theme
#just jump and link to each note/gap label
jal g
jal sleep0.4
jal g
jal sleep0.4
jal a
jal sleep0.4
jal f
jal sleep0.4
jal g_long
jal sleep

jal c_hi
jal sleep0.4
jal bf
jal sleep0.4
jal f
jal sleep0.4
jal g_long
jal sleep

j end
